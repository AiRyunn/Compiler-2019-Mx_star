package com.github.espylapiza.compiler_mxstar.front_end;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.Stack;
import java.util.logging.Logger;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Class;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Domain;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncExtra;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncAddr;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncBuiltin;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncExtern;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Inst;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstBaseJump;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstJump;
import com.github.espylapiza.compiler_mxstar.pizza_ir.DomainLoop;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Func;
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIR;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Scope;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ScopeType;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Type;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Object;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ParamList;
import org.antlr.v4.runtime.tree.ParseTree;

public class PizzaIRBuilder {
    private final static Logger LOGGER = Logger.getLogger(PizzaIRBuilder.class.getName());

    private PizzaIR ir = new PizzaIR();

    /**
     * Build PizzaIR from MxStar Parser.
     * @param parser
     */
    public void fromMxStarParser(ParseTree parser) {
        LOGGER.info("register built-in classes and functions");
        registerBuiltinFunc();

        LOGGER.info("visit parse tree");
        Mx_starParseTreeVisitor visitor = new Mx_starParseTreeVisitor(ir);
        parser.accept(visitor);
    }

    /**
     * @return ir
     */
    public PizzaIR getIR() {
        return ir;
    }

    private void registerBuiltinFunc() {
        Arrays.asList(
                new FuncExtra(FuncAddr.createGlobalFuncAddr("_init"), "_init", ir.typeTable.get("void"),
                        new ParamList()),
                new FuncExtern(FuncAddr.createFuncAddr("print"), "print", ir.typeTable.get("void"),
                        new ParamList(new Object(null, "str", ir.typeTable.get("string")))),
                new FuncExtern(FuncAddr.createFuncAddr("println"), "println", ir.typeTable.get("void"),
                        new ParamList(new Object(null, "str", ir.typeTable.get("string")))),
                new FuncExtern(FuncAddr.createFuncAddr("getInt"), "getInt", ir.typeTable.get("int"), new ParamList()),
                new FuncExtern(FuncAddr.createFuncAddr("getString"), "getString", ir.typeTable.get("string"),
                        new ParamList()),
                new FuncExtern(FuncAddr.createFuncAddr("toString"), "toString", ir.typeTable.get("string"),
                        new ParamList(new Object(null, "num", ir.typeTable.get("int")))),
                new FuncBuiltin(FuncAddr.createFuncAddr("addrEq"), "addrEq", ir.typeTable.get("bool"),
                        new ParamList(new Object(null, "lhs", null), new Object(null, "rhs", null))),
                new FuncBuiltin(FuncAddr.createFuncAddr("addrNe"), "addrEq", ir.typeTable.get("bool"),
                        new ParamList(new Object(null, "lhs", null), new Object(null, "rhs", null))))
                .forEach(func -> {
                    ir.funcList.addFunc(func);
                });
    }
}

class DomainTrace {
    private class VarAndDepth {
        final Object variable;
        final Integer depth;

        VarAndDepth(Object variable, Integer depth) {
            this.variable = variable;
            this.depth = depth;
        }
    }

    private final static Logger LOGGER = Logger.getLogger(DomainTrace.class.getName());

    private Class currentClass;
    private FuncExtra currentFunc;

    private Integer depth = 0;
    private Stack<VarAndDepth> varStack = new Stack<VarAndDepth>();
    private Stack<Domain> doms = new Stack<Domain>();

    boolean isGlobal() {
        return currentClass == null;
    }

    void addVar(Object variable) {
        if (variable.name == null) {
            assert false;
        }
        LOGGER.fine("allocate variable: " + variable.type + " " + variable.name);
        varStack.add(new VarAndDepth(variable, depth));
    }

    Object getVar(String name) {
        for (ListIterator<VarAndDepth> it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            VarAndDepth pre = it.previous();
            if (pre.variable.name != null && pre.variable.name.equals(name)) {
                return pre.variable;
            }
        }
        return null;
    }

    Class getCurrentClass() {
        return currentClass;
    }

    FuncExtra getCurrentFunc() {
        return currentFunc;
    }

    void enter(Domain dom) {
        depth++;
        if (dom instanceof Class) {
            currentClass = (Class) dom;
        } else if (dom instanceof FuncExtra) {
            currentFunc = (FuncExtra) dom;
        }
        doms.add(dom);
    }

    void exit() {
        while (!varStack.isEmpty() && varStack.lastElement().depth.equals(depth)) {
            LOGGER.fine("remove variable: " + varStack.lastElement().variable.type + " "
                    + varStack.lastElement().variable.name);
            varStack.pop();
        }
        depth--;

        Domain dom = doms.pop();
        if (dom instanceof Class) {
            currentClass = null;
        } else if (dom instanceof FuncExtra) {
            currentFunc = null;
        }
    }

    boolean inLoop() {
        for (Domain dom : doms) {
            if (dom instanceof DomainLoop) {
                return true;
            }
        }
        return false;
    }

    Type getRtype() {
        for (Domain dom : doms) {
            if (dom instanceof Func)
                return ((Func) dom).getRtype();
        }
        assert false;
        return null;
    }

    boolean canAllocate(String name) {
        for (ListIterator<VarAndDepth> it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            VarAndDepth pre = it.previous();
            if (pre.depth < depth) {
                break;
            }
            if (pre.variable.name.equals(name)) {
                return false;
            }
        }
        return true;
    }
}

class ScopeManager {
    private final static Logger LOGGER = Logger.getLogger(ScopeManager.class.getName());

    private Stack<FuncBuilder> funcStack = new Stack<FuncBuilder>();

    void enter(FuncExtra func) {
        LOGGER.fine("enterFunc: " + func.getAddr());
        funcStack.add(new FuncBuilder(func));
    }

    void exit() {
        LOGGER.fine("exitFunc");
        funcStack.pop();
    }

    void addInstruction(Inst inst) {
        funcStack.lastElement().addInstruction(inst);
    }

    Inst lastInstruction() {
        return funcStack.lastElement().lastInstruction();
    }

    void jumpBreak() {
        funcStack.lastElement().jumpBreak();
    }

    void jumpContinue() {
        funcStack.lastElement().jumpContinue();
    }

    Scope newScope(ScopeType type) {
        return funcStack.lastElement().newScope(type);
    }

    void pushScope(Scope scp) {
        funcStack.lastElement().pushScope(scp);
    }

    void popScope() {
        funcStack.lastElement().popScope();
    }
}

class FuncBuilder {
    private class ScopeWithStatus {
        final Scope scope;
        boolean dead;

        ScopeWithStatus(Scope scope, boolean dead) {
            this.scope = scope;
            this.dead = dead;
        }
    }

    FuncExtra func;
    private Stack<ScopeWithStatus> scpStack = new Stack<ScopeWithStatus>();
    private int counter = 0;

    FuncBuilder(FuncExtra func) {
        this.func = func;
    }

    public Inst lastInstruction() {
        return scpStack.lastElement().scope.lastInstruction();
    }

    void addInstruction(Inst inst) {
        if (scpStack.lastElement().dead) {
            return;
        }
        scpStack.lastElement().scope.addInstruction(inst);
        if (inst instanceof InstBaseJump) {
            scpStack.lastElement().dead = true;
        }
    }

    void jumpBreak() {
        if (scpStack.lastElement().dead) {
            return;
        }
        for (ListIterator<ScopeWithStatus> it = scpStack.listIterator(scpStack.size()); it.hasPrevious();) {
            ScopeWithStatus pre = it.previous();
            if (pre.scope.getType() == ScopeType.ENDLOOP) {
                scpStack.lastElement().scope.addInstruction(new InstJump(pre.scope));
                scpStack.lastElement().dead = true;
            }
        }
    }

    void jumpContinue() {
        if (scpStack.lastElement().dead) {
            return;
        }
        for (ListIterator<ScopeWithStatus> it = scpStack.listIterator(scpStack.size()); it.hasPrevious();) {
            ScopeWithStatus pre = it.previous();
            if (pre.scope.getType() == ScopeType.LOOPTAIL) {
                scpStack.lastElement().scope.addInstruction(new InstJump(pre.scope));
                scpStack.lastElement().dead = true;
            }
        }
    }

    Scope newScope(ScopeType type) {
        if (type == ScopeType.FUNC) {
            return new Scope(type, func.getAddr().toString());
        } else {
            return new Scope(type, func.getAddr() + "." + type.toString() + "_" + (counter++));
        }
    }

    void pushScope(Scope scp) {
        boolean dead = false;
        if (!scpStack.empty()) {
            dead = scpStack.lastElement().dead;
        }
        scpStack.add(new ScopeWithStatus(scp, dead));
    }

    void popScope() {
        ScopeWithStatus top = scpStack.pop();
        if (!top.dead && !scpStack.empty()) {
            top.scope.addInstruction(new InstJump(scpStack.lastElement().scope));
        }
        func.getScps().add(top.scope);
    }
}
