package com.github.espylapiza.compiler_mxstar.front_end;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import com.github.espylapiza.compiler_mxstar.parser.Mx_starBaseVisitor;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser.ConstantContext;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser.StatementContext;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectBool;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectFunction;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectPointer;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Class;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Domain;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Func;
import com.github.espylapiza.compiler_mxstar.pizza_ir.FuncAddr;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstAlloc;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstBr;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstCall;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstJump;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstMember;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstOffset;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstRet;
import com.github.espylapiza.compiler_mxstar.pizza_ir.InstStore;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectInt;
import com.github.espylapiza.compiler_mxstar.pizza_ir.DomainLoop;
import com.github.espylapiza.compiler_mxstar.pizza_ir.NullComparable;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectNull;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Object;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ParamList;
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIR;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Pointer;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ProgramFragment;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Scope;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ScopeType;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ObjectString;
import com.github.espylapiza.compiler_mxstar.pizza_ir.ParamInstanceList;
import com.github.espylapiza.compiler_mxstar.pizza_ir.Type;
import com.github.espylapiza.compiler_mxstar.pizza_ir.TypeArray;
import com.github.espylapiza.compiler_mxstar.pizza_ir.TypeBool;
import com.github.espylapiza.compiler_mxstar.pizza_ir.TypeCustomClass;
import com.github.espylapiza.compiler_mxstar.pizza_ir.TypeInt;
import com.github.espylapiza.compiler_mxstar.pizza_ir.TypeNull;
import com.github.espylapiza.compiler_mxstar.pizza_ir.TypeString;
import com.github.espylapiza.compiler_mxstar.pizza_ir.TypeVoid;

import org.antlr.v4.runtime.tree.ParseTree;

enum VisitState {
    TYPE_DECLARATION, DECLARATION, SEMANTIC_ANALYSIS
}

class Mx_starParseTreeVisitor extends Mx_starBaseVisitor<ProgramFragment> {
    private final static Logger LOGGER = Logger.getLogger(Mx_starParseTreeVisitor.class.getName());

    private PizzaIR ir;

    private VisitState state;
    private ScopeManager manager = new ScopeManager();
    private DomainTrace trace = new DomainTrace();

    private Func mainFunc;
    private final Func initFunc;
    private final Class arrayClass;

    Mx_starParseTreeVisitor(PizzaIR ir) {
        this.ir = ir;
        initFunc = getFuncByAddr(FuncAddr.create("__init__"));
        arrayClass = getClassByName("__array__");
    }

    @Override
    public ProgramFragment visitProgram(Mx_starParser.ProgramContext ctx) {
        LOGGER.info("TYPE_DECLARATION");
        state = VisitState.TYPE_DECLARATION;
        ctx.programSection().forEach(ch -> ch.accept(this));

        LOGGER.info("DECLARATION");
        state = VisitState.DECLARATION;
        ctx.programSection().forEach(ch -> ch.accept(this));

        mainFunc = getFuncByAddr(FuncAddr.createFuncAddr("main"));
        if (mainFunc == null || !(mainFunc.getRtype() instanceof TypeInt) || mainFunc.getParams().count() != 0) {
            assert false;
        }

        manager.enter(initFunc);
        manager.pushScope(manager.newScope(ScopeType.FUNC));

        LOGGER.info("SEMANTIC_ANALYSIS");
        state = VisitState.SEMANTIC_ANALYSIS;
        ctx.programSection().forEach(ch -> ch.accept(this));

        manager.popScope();
        manager.exit();

        return null;
    }

    ////////////////////////////// Program Section //////////////////////////////

    @Override
    public ProgramFragment visitProgramVariableDeclarationStatement(
            Mx_starParser.ProgramVariableDeclarationStatementContext ctx) {
        switch (state) {
        case TYPE_DECLARATION:
            break;
        case DECLARATION:
            break;
        case SEMANTIC_ANALYSIS:
            visit(ctx.variableDeclarationStatement());
            break;
        }
        return null;
    }

    @Override
    public ProgramFragment visitProgramVariableDefinitionStatement(
            Mx_starParser.ProgramVariableDefinitionStatementContext ctx) {
        switch (state) {
        case TYPE_DECLARATION:
            break;
        case DECLARATION:
            break;
        case SEMANTIC_ANALYSIS:
            visit(ctx.variableDefinitionStatement());
            break;
        }
        return null;
    }

    @Override
    public ProgramFragment visitProgramClassDefinitionStatement(
            Mx_starParser.ProgramClassDefinitionStatementContext ctx) {
        String name = ctx.classDefinitionStatement().Identifier().getText();

        switch (state) {
        case TYPE_DECLARATION:
            Class class1 = new Class(name);
            ir.classList.add(class1);
            ir.typeTable.add(new TypeCustomClass(name, class1));
            break;
        case DECLARATION:
        case SEMANTIC_ANALYSIS:
            trace.enter(getClassByName(name));
            ctx.classDefinitionStatement().classMember().forEach(ch -> ch.accept(this));
            trace.exit();
            break;
        }

        return null;
    }

    @Override
    public ProgramFragment visitProgramFunctionDefinitionStatement(
            Mx_starParser.ProgramFunctionDefinitionStatementContext ctx) {
        switch (state) {
        case TYPE_DECLARATION:
            break;
        case DECLARATION:
            Func func = (Func) visit(ctx.functionDefinitionStatement());
            ir.funcList.addFunc(func);
            break;
        case SEMANTIC_ANALYSIS:
            visit(ctx.functionDefinitionStatement());
            break;
        }
        return null;
    }

    ////////////////////////////// Class //////////////////////////////

    @Override
    public ProgramFragment visitClassVariableDeclarationStatement(
            Mx_starParser.ClassVariableDeclarationStatementContext ctx) {
        switch (state) {
        case TYPE_DECLARATION:
            break;
        case DECLARATION:
            String name = ctx.variableDeclarationStatement().variableDeclaration().Identifier().getText();
            Type type = getTypeByName(ctx.variableDeclarationStatement().variableDeclaration().type().getText());
            trace.getCurrentClass().addVariable(name, type);
            break;
        case SEMANTIC_ANALYSIS:
            visit(ctx.variableDeclarationStatement());
            break;
        }
        return null;
    }

    @Override
    public ProgramFragment visitClassConstructionFunctionStatement(
            Mx_starParser.ClassConstructionFunctionStatementContext ctx) {
        Func func = (Func) visit(ctx.constructionFunctionStatement());

        if (state == VisitState.DECLARATION) {
            String name = func.getName();

            if (!trace.getCurrentClass().getName().equals(name)) {
                assert false;
            }

            trace.getCurrentClass().addMethod(func);
            ir.funcList.addFunc(func);
        }
        return null;
    }

    @Override
    public ProgramFragment visitClassFunctionDefinitionStatement(
            Mx_starParser.ClassFunctionDefinitionStatementContext ctx) {
        Func func = (Func) visit(ctx.functionDefinitionStatement());

        if (state == VisitState.DECLARATION) {
            String name = func.getName();

            if (trace.getCurrentClass().getName().equals(name)) {
                assert false;
            }

            trace.getCurrentClass().addMethod(func);
            ir.funcList.addFunc(func);
        }
        return null;
    }

    ////////////////////////////// Function //////////////////////////////

    @Override
    public ProgramFragment visitConstructionFunctionStatement(Mx_starParser.ConstructionFunctionStatementContext ctx) {
        Class owner = trace.getCurrentClass();
        String name = ctx.Identifier().getText();
        Type rtype = getTypeByName("void");
        FuncAddr addr = FuncAddr.createMethodAddr(owner, name);

        Func func;
        if (state == VisitState.DECLARATION) {
            func = new Func(addr, name, rtype);
        } else {
            func = (Func) getFuncByAddr(addr);
        }

        LOGGER.fine("enter construction function: " + name);
        trace.enter(func);

        if (state == VisitState.SEMANTIC_ANALYSIS) {
            defineVar(allocateVariable(
                    new ObjectPointer(func, "this", getTypeByName(trace.getCurrentClass().getName()))));
        }

        ParamList params = (ParamList) visit(ctx.paramListDefinition());

        if (state == VisitState.DECLARATION) {
            func.setParams(params);
        } else {
            manager.enter(func);
            manager.pushScope(manager.newScope(ScopeType.FUNC));

            if (ctx.statements() != null) {
                visit(ctx.statements());
            }

            manager.popScope();
            manager.exit();
        }

        trace.exit();
        LOGGER.fine("exit construction function: " + name);

        return func;
    }

    @Override
    public ProgramFragment visitFunctionDefinitionStatement(Mx_starParser.FunctionDefinitionStatementContext ctx) {
        Class owner = trace.getCurrentClass();
        String name = ctx.Identifier().getText();
        Type rtype = getTypeByName(ctx.type().getText());
        FuncAddr addr = FuncAddr.createMethodAddr(owner, name);

        Func func;
        if (state == VisitState.DECLARATION) {
            func = new Func(addr, name, rtype);
        } else {
            func = (Func) getFuncByAddr(addr);
        }

        LOGGER.fine("enter function: " + name);
        trace.enter(func);

        if (state == VisitState.SEMANTIC_ANALYSIS && owner != null) {
            defineVar(allocateVariable(
                    new ObjectPointer(func, "this", getTypeByName(trace.getCurrentClass().getName()))));
        }

        ParamList params = (ParamList) visit(ctx.paramListDefinition());

        if (state == VisitState.DECLARATION) {
            func.setParams(params);
        } else {
            manager.enter(func);
            manager.pushScope(manager.newScope(ScopeType.FUNC));

            if (ctx.statements() != null) {
                visit(ctx.statements());
            }

            manager.popScope();
            manager.exit();
        }

        trace.exit();
        LOGGER.fine("exit function: " + name);
        return func;
    }

    @Override
    public ProgramFragment visitParamListDefinition(Mx_starParser.ParamListDefinitionContext ctx) {
        ParamList params = new ParamList();
        ctx.variableDeclaration().forEach(member -> {
            Object variable = (Object) visit(member);
            params.add(variable.type);
        });
        return params;
    }

    @Override
    public ProgramFragment visitParamList(Mx_starParser.ParamListContext ctx) {
        ParamInstanceList params = new ParamInstanceList();
        ctx.object().forEach(member -> {
            Object variable = (Object) visit(member);
            params.add(variable);
        });
        return params;
    }

    ////////////////////////////// Statement //////////////////////////////
    // SEMANTIC

    @Override
    public ProgramFragment visitStatements(Mx_starParser.StatementsContext ctx) {
        ctx.statement().forEach(ch -> visit(ch));
        return null;
    }

    // @Override
    // public ProgramFragment visitStmtEmptyStatement(Mx_starParser.StmtEmptyStatementContext ctx) {
    //     return null;
    // }

    @Override
    public ProgramFragment visitStmtVariableDeclarationStatement(
            Mx_starParser.StmtVariableDeclarationStatementContext ctx) {
        return visit(ctx.variableDeclarationStatement());
    }

    @Override
    public ProgramFragment visitStmtVariableDefinitionStatement(
            Mx_starParser.StmtVariableDefinitionStatementContext ctx) {
        return visit(ctx.variableDefinitionStatement());
    }

    @Override
    public ProgramFragment visitVariableAssignmentStatement(Mx_starParser.VariableAssignmentStatementContext ctx) {
        return visit(ctx.variableAssignment());
    }

    @Override
    public ProgramFragment visitStmtVariableAssignmentStatement(
            Mx_starParser.StmtVariableAssignmentStatementContext ctx) {
        return visit(ctx.variableAssignmentStatement());
    }

    @Override
    public ProgramFragment visitStmtObjectStatement(Mx_starParser.StmtObjectStatementContext ctx) {
        return visit(ctx.objectStatement());
    }

    @Override
    public ProgramFragment visitStmtLoopStatement(Mx_starParser.StmtLoopStatementContext ctx) {
        return visit(ctx.loopStatement());
    }

    @Override
    public ProgramFragment visitStmtConditionStatement(Mx_starParser.StmtConditionStatementContext ctx) {
        return visit(ctx.conditionStatement());
    }

    @Override
    public ProgramFragment visitStmtJumpStatement(Mx_starParser.StmtJumpStatementContext ctx) {
        return visit(ctx.jumpStatement());
    }

    @Override
    public ProgramFragment visitStmtCompoundStatement(Mx_starParser.StmtCompoundStatementContext ctx) {
        return visit(ctx.compoundStatement());
    }

    @Override
    public ProgramFragment visitJumpReturn(Mx_starParser.JumpReturnContext ctx) {
        if (ctx.object() == null) {
            // return
            if (!(trace.getRtype() instanceof TypeVoid)) {
                assert false;
                return null;
            }
            manager.addInstruction(new InstRet());
        } else {
            Object obj = (Object) visit(ctx.object());

            Type rtype = trace.getRtype();
            if (obj.type instanceof TypeNull) {
                // return null
                if (!(rtype instanceof NullComparable)) {
                    assert false;
                    return null;
                }
                manager.addInstruction(new InstRet(obj));
            } else {
                // return object
                if (!obj.type.equals(rtype)) {
                    assert false;
                    return null;
                }
                manager.addInstruction(new InstRet(obj));
            }
        }
        return null;
    }

    @Override
    public ProgramFragment visitJumpBreak(Mx_starParser.JumpBreakContext ctx) {
        if (!trace.inLoop()) {
            assert false;
        }
        manager.jumpBreak();
        return null;
    }

    @Override
    public ProgramFragment visitJumpContinue(Mx_starParser.JumpContinueContext ctx) {
        if (!trace.inLoop()) {
            assert false;
        }
        manager.jumpContinue();
        return null;
    }

    @Override
    public ProgramFragment visitWhileLoop(Mx_starParser.WhileLoopContext ctx) {
        trace.enter(new DomainLoop());

        Scope scpLoop, scpLoopBody, scpEndLoop;

        scpLoop = manager.newScope(ScopeType.LOOP);
        scpLoopBody = manager.newScope(ScopeType.LOOPBODY);
        scpEndLoop = manager.newScope(ScopeType.ENDLOOP);

        manager.addInstruction(new InstJump(scpLoop));
        manager.popScope();
        manager.pushScope(scpEndLoop);
        manager.pushScope(scpLoop);

        Object obj = (Object) visit(ctx.object());

        if (!(obj.type instanceof TypeBool)) {
            assert false;
        }

        manager.addInstruction(new InstBr(obj, scpLoop, scpEndLoop));

        manager.popScope();
        manager.pushScope(scpLoopBody);

        visit(ctx.statement());

        manager.addInstruction(new InstJump(scpLoop));
        manager.popScope();

        trace.exit();

        return null;
    }

    @Override
    public ProgramFragment visitForLoop(Mx_starParser.ForLoopContext ctx) {
        trace.enter(new DomainLoop());

        if (ctx.forCondition().forCondition1() != null) {
            visit(ctx.forCondition().forCondition1());
        }

        Scope scpLoop, scpLoopBody, scpEndLoop;

        scpLoop = manager.newScope(ScopeType.LOOP);
        scpLoopBody = manager.newScope(ScopeType.LOOPBODY);
        scpEndLoop = manager.newScope(ScopeType.ENDLOOP);

        manager.addInstruction(new InstJump(scpLoop));
        manager.popScope();
        manager.pushScope(scpEndLoop);
        manager.pushScope(scpLoop);

        if (ctx.forCondition().forCondition2() != null) {
            Object obj = (Object) visit(ctx.forCondition().forCondition2().object());
            if (!(obj.type instanceof TypeBool)) {
                assert false;
            }
            manager.addInstruction(new InstBr(obj, scpLoopBody, scpEndLoop));
        } else {
            manager.addInstruction(new InstJump(scpLoopBody));
        }

        manager.popScope();
        manager.pushScope(scpLoopBody);

        visit(ctx.statement());

        if (ctx.forCondition().forCondition3() != null) {
            visit(ctx.forCondition().forCondition3());
        }

        manager.addInstruction(new InstJump(scpLoop));
        manager.popScope();

        trace.exit();

        return null;
    }

    @Override
    public ProgramFragment visitForCdt1VariableDeclaration(Mx_starParser.ForCdt1VariableDeclarationContext ctx) {
        return visit(ctx.variableDeclaration());
    }

    @Override
    public ProgramFragment visitForCdt1VariableDefinition(Mx_starParser.ForCdt1VariableDefinitionContext ctx) {
        return visit(ctx.variableDefinition());
    }

    @Override
    public ProgramFragment visitForCdt1VariableAssignment(Mx_starParser.ForCdt1VariableAssignmentContext ctx) {
        return visit(ctx.variableAssignment());
    }

    @Override
    public ProgramFragment visitForCdt1Object(Mx_starParser.ForCdt1ObjectContext ctx) {
        return visit(ctx.object());
    }

    @Override
    public ProgramFragment visitForCdt3VariableAssignment(Mx_starParser.ForCdt3VariableAssignmentContext ctx) {
        return visit(ctx.variableAssignment());
    }

    @Override
    public ProgramFragment visitForCdt3Object(Mx_starParser.ForCdt3ObjectContext ctx) {
        return visit(ctx.object());
    }

    @Override
    public ProgramFragment visitConditionStatement(Mx_starParser.ConditionStatementContext ctx) {
        Object obj = (Object) visit(ctx.object());

        if (!(obj.type instanceof TypeBool)) {
            assert false;
        }

        Scope scpIfTrue, scpIfFalse, scpEndIf;

        scpIfTrue = manager.newScope(ScopeType.IF);
        scpEndIf = manager.newScope(ScopeType.ENDIF);

        if (ctx.else_stmt != null) {
            scpIfFalse = manager.newScope(ScopeType.ELSE);
        } else {
            scpIfFalse = scpEndIf;
        }

        manager.addInstruction(new InstBr(obj, scpIfTrue, scpIfFalse));
        manager.popScope();

        manager.pushScope(scpEndIf);

        for (StatementContext stmt : ctx.statement()) {
            trace.enter(new Domain());
            if (stmt == ctx.if_stmt) {
                manager.pushScope(scpIfTrue);
            } else {
                manager.pushScope(scpIfFalse);
            }
            visit(stmt);
            manager.popScope();
            trace.exit();
        }

        return null;
    }

    @Override
    public ProgramFragment visitObjectStatement(Mx_starParser.ObjectStatementContext ctx) {
        visit(ctx.object());
        return null;
    }

    @Override
    public ProgramFragment visitVariableDeclarationStatement(Mx_starParser.VariableDeclarationStatementContext ctx) {
        visit(ctx.variableDeclaration());
        return null;
    }

    @Override
    public ProgramFragment visitVariableDefinitionStatement(Mx_starParser.VariableDefinitionStatementContext ctx) {
        visit(ctx.variableDefinition());
        return null;
    }

    public ProgramFragment visitCompoundStatement(Mx_starParser.CompoundStatementContext ctx) {
        if (ctx.statements() != null) {
            trace.enter(new Domain());

            visit(ctx.statements());

            trace.exit();
        }
        return null;
    }

    @Override
    public ProgramFragment visitVariableDeclaration(Mx_starParser.VariableDeclarationContext ctx) {
        String name = ctx.Identifier().getText();
        Type type = getTypeByName(ctx.type().getText());

        if (type == null || type instanceof TypeVoid) {
            assert false;
            return null;
        }

        Object obj = allocateVariable(new ObjectPointer(trace.getCurrentFunc(), name, type));
        defineVar(obj);

        return obj;
    }

    @Override
    public ProgramFragment visitVariableDefinition(Mx_starParser.VariableDefinitionContext ctx) {
        Object src = (Object) visit(ctx.object());

        String name = ctx.Identifier().getText();
        Type type = getTypeByName(ctx.type().getText());

        if (type == null || type instanceof TypeVoid) {
            assert false;
            return null;
        }

        if (src.type instanceof TypeNull) {
            if (!(type instanceof NullComparable)) {
                assert false;
                return null;
            }
        } else {
            if (!type.equals(src.type)) {
                assert false;
                return null;
            }
        }

        Object dst = allocateVariable(new ObjectPointer(trace.getCurrentFunc(), name, type));
        defineVar(dst);

        manager.addInstruction(new InstStore((ObjectPointer) dst, src));

        return null;
    }

    @Override
    public ProgramFragment visitVariableAssignment(Mx_starParser.VariableAssignmentContext ctx) {
        Object src = (Object) visit(ctx.object());
        Object dst = (Object) visit(ctx.lvalue());

        Type type = dst.type;

        if (src.type instanceof TypeNull) {
            if (!(type instanceof NullComparable)) {
                assert false;
                return null;
            }
        } else {
            if (!type.equals(src.type)) {
                assert false;
                return null;
            }
        }

        manager.addInstruction(new InstStore((ObjectPointer) dst, src));

        return null;
    }

    ////////////////////////////// Object //////////////////////////////

    @Override
    public ProgramFragment visitThisLvalue(Mx_starParser.ThisLvalueContext ctx) {
        if (trace.isGlobal()) {
            assert false;
            return null;
        }
        return trace.getVar("this");
    }

    @Override
    public ProgramFragment visitThisObject(Mx_starParser.ThisObjectContext ctx) {
        if (trace.isGlobal()) {
            assert false;
            return null;
        }
        return trace.getVar("this");
    }

    @Override
    public ProgramFragment visitIdentifierLvalue(Mx_starParser.IdentifierLvalueContext ctx) {
        String name = ctx.Identifier().getText();

        ObjectPointer src = (ObjectPointer) trace.getVar(name);
        Func func = trace.getCurrentFunc();

        if (src != null && src.belong == func) {
            // local variable
            return src;
        }

        if (!trace.isGlobal()) {
            Class class1 = trace.getCurrentClass();
            if (class1.hasVariable(name)) {
                // member variable
                ObjectPointer dst = new ObjectPointer(func, null, class1.getVarType(name));
                manager.addInstruction(new InstMember(dst, src, name));
                return dst;
            }
            if (class1.hasMethod(name)) {
                assert false;
                return null;
            }
        }

        if (src != null) {
            // global variable
            return src;
        }

        assert false;
        return null;
    }

    @Override
    public ProgramFragment visitIdentifierObject(Mx_starParser.IdentifierObjectContext ctx) {
        String name = ctx.Identifier().getText();

        Object src = trace.getVar(name);
        Func currentFunc = trace.getCurrentFunc();

        if (src != null && src.belong == currentFunc) {
            // local variable
            return src;
        }

        if (!trace.isGlobal()) {
            Class class1 = trace.getCurrentClass();
            if (class1.hasVariable(name)) {
                // member variable
                return new ObjectPointer(currentFunc, name, class1.getVarType(name));
            }
            if (class1.hasMethod(name)) {
                // member method
                return new ObjectFunction(class1.getMethod(name), currentFunc, name, getTypeByName("__method__"));
            }
        }

        if (src != null) {
            // global variable
            return src;
        }

        Func func = getFuncByAddr(FuncAddr.createFuncAddr(name));
        if (func != null) {
            // global function
            return new ObjectFunction(func, currentFunc, name, getTypeByName("__func__"));
        }

        assert false;
        return null;
    }

    @Override
    public ProgramFragment visitNewObject(Mx_starParser.NewObjectContext ctx) {
        int cntLeftBracket = 0;
        int cntBracket = 0;

        for (ParseTree ch : ctx.children) {
            if (ch.getText().equals("[")) {
                cntLeftBracket++;
                cntBracket++;
            } else if (ch instanceof Mx_starParser.ObjectContext) {
                cntBracket--;

                if (cntBracket > 0) {
                    assert false;
                    return null;
                }

                Object obj = (Object) visit(ch);

                if (!(obj.type instanceof TypeInt)) {
                    assert false;
                    return null;
                }
            }
        }

        Type type = getTypeByName(ctx.type().getText());

        if (type instanceof TypeVoid) {
            assert false;
            return null;
        }

        for (int i = 0; i < cntLeftBracket; i++) {
            type = new TypeArray(type, arrayClass);
        }

        // TODO: alloc size, syntactic sugar new: Object[size][size]
        Object dst = allocateVariable(new ObjectPointer(trace.getCurrentFunc(), null, type));
        manager.addInstruction(new InstAlloc(dst, null));

        return dst;
    }

    @Override
    public ProgramFragment visitConstantObject(Mx_starParser.ConstantObjectContext ctx) {
        Object obj;

        ConstantContext constant = ctx.constant();
        if (constant instanceof Mx_starParser.NullContext) {
            obj = new ObjectNull(trace.getCurrentFunc(), null, (TypeNull) getTypeByName("null"));
        } else if (constant instanceof Mx_starParser.LogicalConstantContext) {
            Boolean value = constant.getText().equals("true");
            obj = new ObjectBool(trace.getCurrentFunc(), null, (TypeBool) getTypeByName("bool"), value);
        } else if (constant instanceof Mx_starParser.IntegerConstantContext) {
            Integer value = Integer.parseInt(constant.getText());
            obj = new ObjectInt(trace.getCurrentFunc(), null, (TypeInt) getTypeByName("int"), value);
        } else if (constant instanceof Mx_starParser.StringLiteralContext) {
            String value = constant.getText();
            obj = new ObjectString(trace.getCurrentFunc(), null, (TypeString) getTypeByName("string"), value);
        } else {
            assert false;
            return null;
        }
        return obj;
    }

    @Override
    public ProgramFragment visitLvalueObject(Mx_starParser.LvalueObjectContext ctx) {
        Object obj = (Object) visit(ctx.lvalue());
        Object dst = new Object(trace.getCurrentFunc(), obj.name, obj.type);
        return dst;
    }

    @Override
    public ProgramFragment visitMemberLvalue(Mx_starParser.MemberLvalueContext ctx) {
        ObjectPointer src;

        src = (ObjectPointer) visit(ctx.lvalue());

        String name = ctx.Identifier().getText();

        System.out.println(ctx.getText());
        Type type = src.type.getTypeClass().getVarType(name);

        System.out.println(name);

        if (type == null) {
            assert false;
            return null;
        }

        ObjectPointer dst = (ObjectPointer) allocateVariable(new ObjectPointer(trace.getCurrentFunc(), null, type));
        manager.addInstruction(new InstMember(dst, src, name));

        return dst;
    }

    @Override
    public ProgramFragment visitMemberObject(Mx_starParser.MemberObjectContext ctx) {
        String name = ctx.Identifier().getText();
        Type type;

        Object src = (Object) visit(ctx.object());

        Class class1 = getTypeByName(src.type.getName()).getTypeClass();

        if (class1.hasVariable(name)) {
            type = class1.getVarType(name);

            Object dst = allocateVariable(new ObjectPointer(trace.getCurrentFunc(), null, type));
            manager.addInstruction(new InstMember((ObjectPointer) dst, (ObjectPointer) src, name));
            return dst;
        }

        if (class1.hasMethod(name)) {
            type = getTypeByName("__method__");

            return new ObjectFunction(class1.getMethod(name), trace.getCurrentFunc(), name, type);
        }

        assert false;
        return null;
    }

    @Override
    public ProgramFragment visitBracketObject(Mx_starParser.BracketObjectContext ctx) {
        return (Object) visit(ctx.object());
    }

    @Override
    public ProgramFragment visitFunctionReturnObject(Mx_starParser.FunctionReturnObjectContext ctx) {
        ObjectFunction obj = (ObjectFunction) visit(ctx.object());

        Func func = obj.func;

        ParamInstanceList params = (ParamInstanceList) visit(ctx.paramList());

        if (!params.match(func.getParams())) {
            assert false;
            return null;
        }

        Type rtype = func.getRtype();

        Object dst;

        if (rtype instanceof TypeVoid) {
            dst = null;
        } else if (rtype instanceof Pointer) {
            dst = allocateVariable(new ObjectPointer(trace.getCurrentFunc(), null, rtype));
        } else {
            dst = allocateVariable(new Object(trace.getCurrentFunc(), null, rtype));
        }
        manager.addInstruction(new InstCall(dst, func.getAddr(), params.get()));

        return dst;
    }

    @Override
    public ProgramFragment visitSubscriptLvalue(Mx_starParser.SubscriptLvalueContext ctx) {
        ObjectPointer array = (ObjectPointer) visit(ctx.array);

        Type type = array.type;
        if (!(type instanceof TypeArray)) {
            assert false;
            return null;
        } else {
            type = ((TypeArray) type).getSubType();
        }

        Object sub = (Object) visit(ctx.subscript);
        if (!(sub.type instanceof TypeInt)) {
            assert false;
            return null;
        }

        Object dst = allocateVariable(new ObjectPointer(array.belong, null, type));
        manager.addInstruction(new InstOffset((ObjectPointer) dst, array, sub));

        return dst;
    }

    @Override
    public ProgramFragment visitSubscriptObject(Mx_starParser.SubscriptObjectContext ctx) {
        ObjectPointer array = (ObjectPointer) visit(ctx.array);

        Type type = array.type;
        if (!(type instanceof TypeArray)) {
            assert false;
            return null;
        } else {
            type = ((TypeArray) type).getSubType();
        }

        Object sub = (Object) visit(ctx.subscript);
        if (!(sub.type instanceof TypeInt)) {
            assert false;
            return null;
        }

        Object dst = allocateVariable(new ObjectPointer(array.belong, null, type));
        manager.addInstruction(new InstOffset((ObjectPointer) dst, array, sub));

        return dst;
    }

    @Override
    public ProgramFragment visitUnaryOperatorObject(Mx_starParser.UnaryOperatorObjectContext ctx) {
        String op = ctx.op.getText();
        String method = null;

        switch (op) {
        case "++":
            method = "__preinc__";
            break;
        case "--":
            method = "__predec__";
            break;
        case "+":
            method = "__pos__";
            break;
        case "-":
            method = "__neg__";
            break;
        case "!":
            method = "__lgcnot__";
            break;
        case "~":
            method = "__bitinv__";
            break;
        default:
            assert false;
            return null;
        }

        Object obj;

        if (ctx.lvalue() != null) {
            obj = (Object) visit(ctx.lvalue());
        } else {
            obj = (Object) visit(ctx.object());
        }

        Func func = obj.type.getTypeClass().getMethod(method);

        if (func == null) {
            assert false;
            return null;
        }

        ParamInstanceList params = new ParamInstanceList();

        if (!params.match(func.getParams())) {
            assert false;
            return null;
        }

        Type rtype = func.getRtype();

        Object dst = allocateVariable(new Object(trace.getCurrentFunc(), null, rtype));
        manager.addInstruction(new InstCall(dst, func.getAddr(), new ArrayList<Object>(Arrays.asList(obj))));

        return dst;
    }

    @Override
    public ProgramFragment visitBinaryOperatorObject(Mx_starParser.BinaryOperatorObjectContext ctx) {
        Type type;
        Object lhs = (Object) visit(ctx.object(0));
        Type typel = lhs.type;

        Object rhs = (Object) visit(ctx.object(1));
        Type typer = rhs.type;

        String op = ctx.op.getText();
        String method = "";
        switch (op) {
        case "*":
            method = "__mul__";
            break;
        case "/":
            method = "__div__";
            break;
        case "%":
            method = "__mod__";
            break;
        case "+":
            method = "__add__";
            break;
        case "-":
            method = "__sub__";
            break;
        case "<<":
            method = "__shl__";
            break;
        case ">>":
            method = "__shr__";
            break;
        case "<":
            method = "__lt__";
            break;
        case ">":
            method = "__gt__";
            break;
        case "<=":
            method = "__le__";
            break;
        case ">=":
            method = "__ge__";
            break;
        case "==":
            method = "__eq__";
            if (typel instanceof TypeNull || typer instanceof TypeNull) {
                if (!(typel instanceof NullComparable && typer instanceof NullComparable)) {
                    assert false;
                }
                type = getTypeByName("bool");
                Object dst = allocateVariable(new Object(trace.getCurrentFunc(), null, type));
                return dst;
                // code.addInstruction(new InstCall(id, type, new Vector<ObjectID>(Arrays.asList(lhs.id, rhs.id))));
            }
            break;
        case "!=":
            method = "__ne__";
            if (typel instanceof TypeNull || typer instanceof TypeNull) {
                if (!(typel instanceof NullComparable && typer instanceof NullComparable)) {
                    assert false;
                }
                type = getTypeByName("bool");
                Object dst = allocateVariable(new Object(trace.getCurrentFunc(), null, type));
                // code.addInstruction(new InstCall(id, type, new Vector<ObjectID>(Arrays.asList(lhs.id, rhs.id))));
                return dst;
            }
            break;
        case "&":
            method = "__bitand__";
            break;
        case "|":
            method = "__bitor__";
            break;
        case "^":
            method = "__bitxor__";
            break;
        case "&&":
            method = "__lgcand__";
            break;
        case "||":
            method = "__lgcor__";
            break;
        default:
            assert false;
            return null;
        }

        Func func = lhs.type.getTypeClass().getMethod(method);

        if (func == null) {
            assert false;
            return null;
        }

        ParamInstanceList params = new ParamInstanceList(rhs);

        if (!params.match(func.getParams())) {
            assert false;
            return null;
        }

        type = func.getRtype();

        // TODO a && b
        Object dst = allocateVariable(new Object(trace.getCurrentFunc(), null, type));
        manager.addInstruction(new InstCall(dst, func.getAddr(), new ArrayList<Object>(Arrays.asList(lhs, rhs))));

        return dst;
    }

    private Type getTypeByName(String name) {
        if (name.endsWith("[]")) {
            return new TypeArray(getTypeByName(name.substring(0, name.length() - 2)), arrayClass);
        } else {
            Type type = ir.typeTable.get(name);
            if (type == null) {
                assert false;
            }
            return type;
        }
    }

    private Func getFuncByAddr(FuncAddr addr) {
        return ir.funcList.get(addr);
    }

    private Class getClassByName(String name) {
        return ir.classList.get(name);
    }

    private Object allocateVariable(Object obj) {
        Func func = trace.getCurrentFunc();
        if (func == null) {
            func = (Func) initFunc;
        }
        LOGGER.fine("alloc " + obj.name + ": " + obj.type);
        func.allocateVariable(obj);
        return obj;
    }

    private void defineVar(Object obj) {
        if (state != VisitState.SEMANTIC_ANALYSIS) {
            return;
        }

        if (!trace.canAllocate(obj.name)) {
            assert false;
        }
        trace.addVar(obj);
    }
}