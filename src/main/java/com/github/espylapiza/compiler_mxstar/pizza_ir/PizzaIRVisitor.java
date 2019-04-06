package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.*;

import java.util.logging.Logger;

import com.github.espylapiza.compiler_mxstar.parser.Mx_starBaseVisitor;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser.*;

import org.antlr.v4.runtime.tree.ParseTree;

enum VisitState {
    TYPE_DECLARATION, DECLARATION, SEMANTIC_ANALYSIS
}

class PizzaIRVisitor extends Mx_starBaseVisitor<ProgramFragment> {
    private final static Logger LOGGER = Logger.getLogger(PizzaIRVisitor.class.getName());

    PizzaIR ir;

    private VisitState state;

    private Trace trace = new Trace();

    private int counterVar = 0;

    PizzaIRVisitor(PizzaIR ir) {
        this.ir = ir;
    }

    @Override
    public ProgramFragment visitProgram(Mx_starParser.ProgramContext ctx) {
        state = VisitState.TYPE_DECLARATION;
        ctx.programSection().forEach(ch -> ch.accept(this));

        state = VisitState.DECLARATION;
        ctx.programSection().forEach(ch -> ch.accept(this));

        Func mainFunc = ir.funcList.get("main");
        if (mainFunc == null || !(mainFunc.getRtype() instanceof IntType) || mainFunc.getParams().count() != 0) {
            assert false;
        }

        ir.code.enterFunc(ir.funcList.get("__init__"));

        state = VisitState.SEMANTIC_ANALYSIS;
        ctx.programSection().forEach(ch -> ch.accept(this));

        ir.code.packScope();
        ir.code.exitFunc();

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
        String className = ctx.classDefinitionStatement().Identifier().getText();

        switch (state) {
        case TYPE_DECLARATION:
            Class class1 = new Class(className);
            ir.classList.add(class1);
            ir.typeTable.add(new CustomClassType(className, class1));
            break;
        case DECLARATION:
        case SEMANTIC_ANALYSIS:
            trace.enter(ir.classList.get(className));
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
            String typeName = ctx.variableDeclarationStatement().variableDeclaration().type().getText();
            trace.getCurrentClass().addVariable(name, getTypeByName(typeName));
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
            String name = func.name;

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
            String name = func.name;

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
        String identifier = ctx.Identifier().getText();
        Type rtype = getTypeByName("void");
        Func func = new Func(owner, identifier, rtype);

        LOGGER.fine("enter construction function: " + identifier);
        trace.enter(func);

        ParamList params = (ParamList) visit(ctx.paramListDefinition());
        func.addParams(params);

        if (state == VisitState.SEMANTIC_ANALYSIS) {
            ir.code.enterFunc(func);

            if (ctx.statements() != null) {
                visit(ctx.statements());
            }

            // ir.code.packScope();
            ir.code.exitFunc();
        }

        trace.exit();
        LOGGER.fine("exit construction function: " + identifier);

        return func;
    }

    @Override
    public ProgramFragment visitFunctionDefinitionStatement(Mx_starParser.FunctionDefinitionStatementContext ctx) {
        Class owner = trace.getCurrentClass();
        String name = ctx.Identifier().getText();
        Type rtype = getTypeByName(ctx.type().getText());
        Func func = new Func(owner, name, rtype);

        LOGGER.fine("enter function: " + name);
        trace.enter(func);

        ParamList params = (ParamList) visit(ctx.paramListDefinition());
        func.addParams(params);

        if (state == VisitState.SEMANTIC_ANALYSIS) {
            ir.code.enterFunc(func);
            ir.code.newlabel();

            if (ctx.statements() != null) {
                visit(ctx.statements());
            }

            // ir.code.packScope();
            ir.code.exitFunc();
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
        ParamList params = new ParamList();
        ctx.object().forEach(member -> {
            Object variable = (Object) visit(member);
            params.add(variable.type);
        });
        return params;
    }

    ////////////////////////////// Statement //////////////////////////////

    @Override
    public ProgramFragment visitStatements(Mx_starParser.StatementsContext ctx) {
        ctx.statement().forEach(ch -> visit(ch));
        return null;
    }

    // @Override
    // public Node visitStmtEmptyStatement(Mx_starParser.StmtEmptyStatementContext ctx) {
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
        if (!trace.inFunc()) {
            assert false;
        }

        if (ctx.object() != null) {
            Object variable = (Object) visit(ctx.object());
            Type objType = variable.type;

            Type rtype = trace.getRtype();
            if (objType instanceof NullType) {
                if (!(rtype instanceof NullComparable)) {
                    assert false;
                    return null;
                }
            } else {
                if (!objType.equals(rtype)) {
                    assert false;
                    return null;
                }
            }
        } else {
            if (!(trace.getRtype() instanceof VoidType)) {
                assert false;
                return null;
            }
        }
        return null;
    }

    @Override
    public ProgramFragment visitJumpBreak(Mx_starParser.JumpBreakContext ctx) {
        if (!trace.inLoop()) {
            assert false;
        }
        return null;
    }

    @Override
    public ProgramFragment visitJumpContinue(Mx_starParser.JumpContinueContext ctx) {
        if (!trace.inLoop()) {
            assert false;
        }
        return null;
    }

    @Override
    public ProgramFragment visitWhileLoop(Mx_starParser.WhileLoopContext ctx) {
        trace.enter(new LoopDomain());

        Object variable = (Object) visit(ctx.object());

        if (!(variable.type instanceof BoolType)) {
            assert false;
        }

        visit(ctx.statement());

        trace.exit();

        return null;
    }

    @Override
    public ProgramFragment visitForLoop(Mx_starParser.ForLoopContext ctx) {
        trace.enter(new LoopDomain());

        if (ctx.forCondition().forCondition1() != null) {
            visit(ctx.forCondition().forCondition1());
        }

        if (ctx.forCondition().forCondition2() != null) {
            Object node = (Object) visit(ctx.forCondition().forCondition2().object());
            if (!(node.type instanceof BoolType)) {
                assert false;
            }
        }

        if (ctx.forCondition().forCondition3() != null) {
            visit(ctx.forCondition().forCondition3());
        }

        visit(ctx.statement());

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
        Object node = (Object) visit(ctx.object());

        if (!(node.type instanceof BoolType)) {
            assert false;
        }

        ctx.statement().forEach(ch -> {
            trace.enter(new Domain());
            // ir.code.packScope();

            visit(ch);

            trace.exit();
        });

        // ir.code.packScope();
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
        String name = ctx.Identifier().getText(), typeName = ctx.type().getText();

        Type type = getTypeByName(typeName);

        if (type == null) {
            assert false;
            return null;
        }

        allocateVariable(name, type, false);

        return new Object(null, name, type, null);
    }

    @Override
    public ProgramFragment visitVariableDefinition(Mx_starParser.VariableDefinitionContext ctx) {
        Object node = (Object) visit(ctx.object());

        String name = ctx.Identifier().getText(), typeName = ctx.type().getText();

        Type type = getTypeByName(typeName);
        if (type == null) {
            assert false;
            return null;
        }

        if (node.type instanceof NullType) {
            if (!(type instanceof NullComparable)) {
                assert false;
                return null;
            }
        } else {
            if (!type.equals(node.type)) {
                assert false;
                return null;
            }
        }

        ObjectID id = allocateVariable(name, type, true);
        ir.code.addInstruction(new InstAssignment(id, node.id));

        return new Object(null, name, type, null);
    }

    @Override
    public ProgramFragment visitVariableAssignment(Mx_starParser.VariableAssignmentContext ctx) {
        Object node = (Object) visit(ctx.object());

        Object lvaluenode = (Object) visit(ctx.lvalue());

        Type type = lvaluenode.type;

        if (node.type instanceof NullType) {
            if (!(type instanceof NullComparable)) {
                assert false;
                return null;
            }
        } else {
            if (!type.equals(node.type)) {
                assert false;
                return null;
            }
        }

        ObjectID id = allocateVariable(lvaluenode.name, type, true);
        ir.code.addInstruction(new InstAssignment(id, node.id));

        return null;
    }

    ////////////////////////////// Object //////////////////////////////

    @Override
    public ProgramFragment visitIdentifierLvalue(Mx_starParser.IdentifierLvalueContext ctx) {
        String name = ctx.Identifier().getText();
        Type type = null;

        Object variable = trace.getVar(name);

        if (variable != null && variable.owner != null && variable.owner.equals(trace.getCurrentClass())) {
            // local variable
            type = variable.type;
            return new Object(null, name, type, null);
        }

        if (!trace.isGlobal()) {
            Class class1 = trace.getCurrentClass();
            if (class1.hasVariable(name)) {
                // member variable
                type = class1.getVarType(name);
                return new Object(class1, name, type, null);
            }
            if (class1.hasMethod(name)) {
                assert false;
                return null;
            }
        }

        if (variable != null) {
            // global variable
            type = variable.type;
            return new Object(null, name, type, null);
        }

        assert false;
        return null;
    }

    @Override
    public ProgramFragment visitMemberLvalue(Mx_starParser.MemberLvalueContext ctx) {
        // Type identifier_type;
        Class identifier_class;

        if (ctx.This() != null) {
            if (trace.isGlobal()) {
                assert false;
                return null;
            }
            identifier_class = trace.getCurrentClass();
            // identifier_type = getTypeByName(dom.getClassAddr());
        } else {
            Object node = (Object) visit(ctx.lvalue());
            identifier_class = node.type.getTypeClass();
            // identifier_type = node.type;
        }

        String name = ctx.Identifier().getText();

        if (!identifier_class.hasVariable(name)) {
            assert false;
            return null;
        }

        Type type = identifier_class.getVarType(name);

        return new Object(null, null, type, null);
    }

    @Override
    public ProgramFragment visitSubscriptLvalue(Mx_starParser.SubscriptLvalueContext ctx) {
        Object arrayNode = (Object) visit(ctx.array);

        if (!(arrayNode.type instanceof ArrayType)) {
            assert false;
            return null;
        }
        ArrayType arrayType = (ArrayType) arrayNode.type;
        Type type = arrayType.getSubType();

        Object subNode = (Object) visit(ctx.subscript);

        if (!(subNode.type instanceof IntType)) {
            assert false;
            return null;
        }

        return new Object(null, null, type, null);
    }

    @Override
    public ProgramFragment visitThisObject(Mx_starParser.ThisObjectContext ctx) {
        if (trace.isGlobal()) {
            assert false;
            return null;
        }
        Type type = getTypeByName(trace.getClassAddr());
        // TODO
        return new Object(null, null, type, null);
    }

    @Override
    public ProgramFragment visitIdentifierObject(Mx_starParser.IdentifierObjectContext ctx) {
        String name = ctx.Identifier().getText();
        Type type = null;

        Object variable = trace.getVar(name);

        if (variable != null && variable.owner != null && variable.owner.equals(trace.getCurrentClass())) {
            // local variable
            return new Object(null, name, variable.type, variable.id);
        }

        if (!trace.isGlobal()) {
            Class class1 = trace.getCurrentClass();
            if (class1.hasVariable(name)) {
                System.out.println(name);
                // member variable
                return new Object(class1, name, class1.getVarType(name), null);
            }
            if (class1.hasMethod(name)) {
                // member method
                return new Object(class1, name, getTypeByName("__method__"), null);
            }
        }

        if (variable != null) {
            // global variable
            type = variable.type;
            return new Object(null, name, type, variable.id);
        }

        Func func = ir.funcList.get(name);
        if (func != null) {
            // global function
            return new Object(null, name, getTypeByName("__func__"), null);
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

                Object node = (Object) visit(ch);

                if (!(node.type instanceof IntType)) {
                    assert false;
                    return null;
                }
            }
        }

        Type type = getTypeByName(ctx.type().getText());

        for (int i = 0; i < cntLeftBracket; i++) {
            type = new ArrayType(type, ir.classList.get("__array__"));
        }

        // TODO
        ObjectID id = allocateVariable(null, type, false);
        // ir.code.addInstruction(Instruction.newCall(id, func.getAddr(), new Vector<Integer>(Arrays.asList(objId))));

        return new Object(null, null, type, id);
    }

    @Override
    public ProgramFragment visitConstantObject(Mx_starParser.ConstantObjectContext ctx) {
        Type type = null;
        ObjectID id;

        ConstantContext constant = ctx.constant();
        if (constant instanceof Mx_starParser.NullContext) {
            type = getTypeByName("null");
            id = allocateVariable(null, type, false);
            ir.code.addInstruction(new InstNull(id));
        } else if (constant instanceof Mx_starParser.LogicalConstantContext) {
            type = getTypeByName("bool");
            id = allocateVariable(null, type, false);
            boolean value = constant.getText().equals("true");
            ir.code.addInstruction(new InstBool(id, value));
        } else if (constant instanceof Mx_starParser.IntegerConstantContext) {
            type = getTypeByName("int");
            id = allocateVariable(null, type, false);
            Integer value = Integer.parseInt(constant.getText());
            ir.code.addInstruction(new InstInt(id, value));
        } else if (constant instanceof Mx_starParser.StringLiteralContext) {
            type = getTypeByName("string");
            id = allocateVariable(null, type, false);
            String value = constant.getText();
            ir.code.addInstruction(new InstString(id, value));
        } else {
            assert false;
            return null;
        }

        return new Object(null, null, type, id);
    }

    @Override
    public ProgramFragment visitLvalueObject(Mx_starParser.LvalueObjectContext ctx) {
        Object node = (Object) visit(ctx.lvalue());
        ObjectID id = allocateVariable(node.name, node.type, false);
        return new Object(null, null, node.type, id);
    }

    @Override
    public ProgramFragment visitMemberObject(Mx_starParser.MemberObjectContext ctx) {
        String name = null;
        Class owner = null;
        String member = ctx.Identifier().getText();
        Type type;

        Object node = (Object) visit(ctx.object());

        Class class1 = getTypeByName(node.type.getName()).getTypeClass();

        System.out.println(ctx.getText());
        System.out.println(class1.getName());

        if (class1.hasVariable(member)) {
            type = class1.getVarType(member);

            // TODO
            ObjectID id = allocateVariable(null, type, false);

            return new Object(owner, null, type, id);
        }

        if (class1.hasMethod(member)) {
            name = member;
            type = getTypeByName("__method__");
            if (node.type instanceof ArrayType) {
                owner = ir.classList.get("__array__");
            } else {
                owner = node.type.getTypeClass();
            }

            return new Object(owner, name, type, null);
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
        Object node = (Object) visit(ctx.object());

        if (!(node.type instanceof FuncType) && !(node.type instanceof MethodType)) {
            assert false;
            return null;
        }

        String addr = node.name;
        if (node.owner != null) {
            addr = node.owner.getName() + "." + addr;
        }

        Func func = ir.funcList.get(addr);

        ParamList params = (ParamList) visit(ctx.paramList());

        if (!func.getParams().match(params)) {
            assert false;
            return null;
        }

        Type rtype = func.getRtype();

        if (rtype instanceof VoidType) {
            return null;
        }

        ObjectID id = allocateVariable(null, rtype, false);
        // ir.code.addInstruction(new InstLoad(id, arrayNode.id, subNode.id));
        return new Object(null, null, rtype, id);
    }

    @Override
    public ProgramFragment visitSubscriptObject(Mx_starParser.SubscriptObjectContext ctx) {
        Object arrayNode = (Object) visit(ctx.array);

        Type type = arrayNode.type;
        if (!(type instanceof ArrayType)) {
            assert false;
            return null;
        } else {
            type = ((ArrayType) type).getSubType();
        }

        Object subNode = (Object) visit(ctx.subscript);
        if (!(subNode.type instanceof IntType)) {
            assert false;
            return null;
        }

        ObjectID id = allocateVariable(null, type, false);
        ir.code.addInstruction(new InstLoad(id, arrayNode.id, subNode.id));

        return new Object(null, null, type, id);
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

        Type objType = null;
        ObjectID objId = null;

        if (ctx.lvalue() != null) {
            Object node = (Object) visit(ctx.lvalue());
            objType = node.type;
            // TODO
            // objId = node.va
        } else {
            Object node = (Object) visit(ctx.object());
            objType = node.type;
            objId = node.id;
        }

        Func func = objType.getTypeClass().getMethod(method);

        if (func == null) {
            assert false;
            return null;
        }

        ParamList params = new ParamList();

        if (!func.getParams().match(params)) {
            assert false;
            return null;
        }

        Type type = func.getRtype();

        ObjectID id = allocateVariable(null, type, false);
        ir.code.addInstruction(new InstCall(id, func.getAddr(), new Vector<ObjectID>(Arrays.asList(objId))));

        return new Object(null, null, type, id);
    }

    @Override
    public ProgramFragment visitBinaryOperatorObject(Mx_starParser.BinaryOperatorObjectContext ctx) {
        Class owner = null;
        String name = null;
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
            if (typel instanceof NullType || typer instanceof NullType) {
                if (!(typel instanceof NullComparable && typer instanceof NullComparable)) {
                    assert false;
                }
                type = getTypeByName("bool");
                ObjectID id = allocateVariable(null, type, false);
                // ir.code.addInstruction(new InstCall(id, type, new Vector<ObjectID>(Arrays.asList(lhs.id, rhs.id))));
                return new Object(owner, name, type, id);
            }
            break;
        case "!=":
            method = "__ne__";
            if (typel instanceof NullType || typer instanceof NullType) {
                if (!(typel instanceof NullComparable && typer instanceof NullComparable)) {
                    assert false;
                }
                type = getTypeByName("bool");
                ObjectID id = allocateVariable(null, type, false);
                // ir.code.addInstruction(new InstCall(id, type, new Vector<ObjectID>(Arrays.asList(lhs.id, rhs.id))));
                return new Object(owner, name, type, id);
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

        ParamList params = new ParamList(rhs.type);

        if (!func.getParams().match(params)) {
            assert false;
            return null;
        }

        type = func.getRtype();

        // TODO a && b
        ObjectID id = allocateVariable(null, type, false);
        ir.code.addInstruction(new InstCall(id, func.getAddr(), new Vector<ObjectID>(Arrays.asList(lhs.id, rhs.id))));

        return new Object(owner, name, type, id);
    }

    private ObjectID allocateVariable(String name, Type type, boolean shadowing) {
        System.out.println(type);
        if (state != VisitState.SEMANTIC_ANALYSIS) {
            return null;
        }
        if (!shadowing && name != null && !trace.canAllocate(name)) {
            assert false;
            return null;
        }
        if (type instanceof VoidType) {
            assert false;
            return null;
        }

        counterVar++;
        ObjectID id = new ObjectID(counterVar);
        Object variable = new Object(trace.getCurrentClass(), name, type, id);
        ir.varList.add(variable);
        if (trace.isGlobal() || trace.inFunc()) {
            trace.addVar(variable);
        }
        return id;
    }

    private Type getTypeByName(String typeName) {
        if (typeName.endsWith("[]")) {
            return new ArrayType(getTypeByName(typeName.substring(0, typeName.length() - 2)),
                    ir.classList.get("__array__"));
        } else {
            Type type = ir.typeTable.get(typeName);
            if (type == null) {
                assert false;
            }
            return type;
        }
    }
}