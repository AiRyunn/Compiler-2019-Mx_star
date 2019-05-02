package com.github.espylapiza.compiler_mxstar.front_end;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.Stack;
import java.util.logging.Logger;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Class;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Domain;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Func;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncAddr;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Inst;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstBaseJump;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstJump;
import com.github.espylapiza.compiler_mxstar.pizza_ir.DomainLoop;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ParamList;
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIR;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Scope;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ScopeType;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Type;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Object;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectID;
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
                new Func(FuncAddr.createGlobalFuncAddr("__init__"), "__init__",
                        ir.typeTable.get("void"), new ParamList()),
                new Func(FuncAddr.createGlobalFuncAddr("printf"), "printf",
                        ir.typeTable.get("void"),
                        new ParamList(Arrays.asList(ir.typeTable.get("string"),
                                ir.typeTable.get("string")))),
                new Func(FuncAddr.createGlobalFuncAddr("itoa"), "itoa", ir.typeTable.get("string"),
                        new ParamList(ir.typeTable.get("int"))),
                new Func(FuncAddr.createFuncAddr("print"), "print", ir.typeTable.get("void"),
                        new ParamList(ir.typeTable.get("string"))),
                new Func(FuncAddr.createFuncAddr("println"), "println", ir.typeTable.get("void"),
                        new ParamList(ir.typeTable.get("string"))),
                new Func(FuncAddr.createFuncAddr("getInt"), "getInt", ir.typeTable.get("int"),
                        new ParamList()),
                new Func(FuncAddr.createFuncAddr("getString"), "getString",
                        ir.typeTable.get("string"), new ParamList()),
                new Func(FuncAddr.createFuncAddr("toString"), "toString",
                        ir.typeTable.get("string"), new ParamList(ir.typeTable.get("int"))))
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
    private Func currentFunc;

    private Integer depth = 0;
    private Stack<VarAndDepth> varStack = new Stack<VarAndDepth>();
    private Stack<Domain> doms = new Stack<Domain>();

    boolean isGlobal() {
        return currentClass == null;
    }

    void addVar(Object variable) {
        LOGGER.fine("allocate variable: " + variable.type + " " + variable.name);
        varStack.add(new VarAndDepth(variable, depth));
    }

    Object getVar(String name) {
        for (ListIterator<VarAndDepth> it = varStack.listIterator(varStack.size()); it
                .hasPrevious();) {
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

    Func getCurrentFunc() {
        return currentFunc;
    }

    void enter(Domain dom) {
        depth++;
        if (dom instanceof Class) {
            currentClass = (Class) dom;
        } else if (dom instanceof Func) {
            currentFunc = (Func) dom;
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
        } else if (dom instanceof Func) {
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
        for (ListIterator<VarAndDepth> it = varStack.listIterator(varStack.size()); it
                .hasPrevious();) {
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

    void enter(Func func) {
        LOGGER.fine("enterFunc: " + func.getAddr());
        funcStack.add(new FuncBuilder(func));
    }

    void exit() {
        LOGGER.fine("exitFunc");
        funcStack.pop();
    }

    void addInstruction(Inst inst) {
        LOGGER.fine("addInstruction: " + inst.toString());
        funcStack.lastElement().addInstruction(inst);
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

    Func func;
    private Stack<ScopeWithStatus> scpStack = new Stack<ScopeWithStatus>();
    private int counter = 0;

    FuncBuilder(Func func) {
        this.func = func;
    }

    public void addInstruction(Inst inst) {
        if (scpStack.lastElement().dead) {
            return;
        }
        scpStack.lastElement().scope.addInstruction(inst);
        if (inst instanceof InstBaseJump) {
            scpStack.lastElement().dead = true;
        }
    }

    public void jumpBreak() {
        if (scpStack.lastElement().dead) {
            return;
        }
        for (ListIterator<ScopeWithStatus> it = scpStack.listIterator(scpStack.size()); it
                .hasPrevious();) {
            ScopeWithStatus pre = it.previous();
            if (pre.scope.getType() == ScopeType.ENDLOOP) {
                scpStack.lastElement().scope.addInstruction(new InstJump(pre.scope));
                scpStack.lastElement().dead = true;
            }
        }
    }

    public void jumpContinue() {
        if (scpStack.lastElement().dead) {
            return;
        }
        scpStack.lastElement().scope.addInstruction(new InstJump(scpStack.lastElement().scope));
        scpStack.lastElement().dead = true;
    }

    public Scope newScope(ScopeType type) {
        return new Scope(type, func.getAddr() + "." + type.toString() + "_" + (counter++));
    }

    public void pushScope(Scope scp) {
        boolean dead = false;
        if (!scpStack.empty()) {
            dead = scpStack.lastElement().dead;
        }
        scpStack.add(new ScopeWithStatus(scp, dead));
    }

    public void popScope() {
        ScopeWithStatus top = scpStack.pop();
        if (!top.dead && !scpStack.empty()) {
            top.scope.addInstruction(new InstJump(scpStack.lastElement().scope));
        }
        func.getScps().add(top.scope);
    }

    public Object allocate(Object obj) {
        obj.setID(new ObjectID(func.getVarList().count()));
        func.getVarList().add(obj);
        return obj;
    }
}
