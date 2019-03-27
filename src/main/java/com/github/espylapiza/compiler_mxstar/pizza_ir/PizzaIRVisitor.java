package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.*;

import java.util.logging.Logger;

import com.github.espylapiza.compiler_mxstar.parser.Mx_starBaseVisitor;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser.*;
import com.github.espylapiza.compiler_mxstar.utils.Pair;

import org.antlr.v4.runtime.tree.ParseTree;

enum VisitState {
    TYPE_DECLARATION, MEMBER_DECLARATION, SEMANTIC_ANALYSIS, TRANSLATION_ENTRANCE, TRANSLATION
}

public class PizzaIRVisitor extends Mx_starBaseVisitor<Node> {
    private final static Logger LOGGER = Logger.getLogger(PizzaIRVisitor.class.getName());

    private VisitState state;

    private Domain dom = new Domain();

    TypeList typeList = new TypeList();
    VarList varList = new VarList();
    FuncList funcList = new FuncList();

    private int counterVar = 0;

    public Code code = new Code();

    @Override
    public Node visitProgram(Mx_starParser.ProgramContext ctx) {
        state = VisitState.TYPE_DECLARATION;
        ctx.programSection().forEach(ch -> ch.accept(this));

        state = VisitState.MEMBER_DECLARATION;
        ctx.programSection().forEach(ch -> ch.accept(this));

        Func mainFunc = funcList.getFunc("main");
        if (mainFunc == null || !mainFunc.rtype.equals("int") || mainFunc.params.count() != 0) {
            assert false;
        }

        state = VisitState.SEMANTIC_ANALYSIS;
        ctx.programSection().forEach(ch -> ch.accept(this));

        return null;
    }

    ////////////////////////////// Program Section //////////////////////////////

    @Override
    public Node visitProgramVariableDeclarationStatement(Mx_starParser.ProgramVariableDeclarationStatementContext ctx) {
        switch (state) {
        case TYPE_DECLARATION:
            break;
        case MEMBER_DECLARATION:
            break;
        case SEMANTIC_ANALYSIS:
            VariableDeclarationContext variableDeclaration = ctx.variableDeclarationStatement().variableDeclaration();
            VariableNode node = (VariableNode) visit(variableDeclaration);
            allocateVariable(node.name, node.type);
            break;
        default:
            break;
        }
        return null;
    }

    @Override
    public Node visitProgramVariableDefinitionStatement(Mx_starParser.ProgramVariableDefinitionStatementContext ctx) {
        switch (state) {
        case TYPE_DECLARATION:
            break;
        case MEMBER_DECLARATION:
            break;
        case SEMANTIC_ANALYSIS:
            VariableDefinitionContext variableDefinition = ctx.variableDefinitionStatement().variableDefinition();
            VariableNode node = (VariableNode) visit(variableDefinition);
            allocateVariable(node.name, node.type);
            break;
        default:
            break;
        }
        return null;
    }

    @Override
    public Node visitProgramClassDefinitionStatement(Mx_starParser.ProgramClassDefinitionStatementContext ctx) {
        String classname = ctx.classDefinitionStatement().Identifier().getText();

        dom.enterClass(classname);

        switch (state) {
        case TYPE_DECLARATION:
            typeList.addType(new Type(dom.getClassTrace()));
            break;
        case MEMBER_DECLARATION:
        case SEMANTIC_ANALYSIS:
            ctx.classDefinitionStatement().classMember().forEach(ch -> ch.accept(this));
            break;
        case TRANSLATION_ENTRANCE:
        case TRANSLATION:
        }

        dom.exitClass();

        return null;
    }

    @Override
    public Node visitProgramFunctionDefinitionStatement(Mx_starParser.ProgramFunctionDefinitionStatementContext ctx) {
        if (state == VisitState.TYPE_DECLARATION) {
            return null;
        }

        visit(ctx.functionDefinitionStatement());
        return null;
    }

    ////////////////////////////// Class //////////////////////////////

    @Override
    public Node visitClassVariableDeclarationStatement(Mx_starParser.ClassVariableDeclarationStatementContext ctx) {
        VariableNode node = (VariableNode) visit(ctx.variableDeclarationStatement().variableDeclaration());
        String name = node.name, type = node.type;

        switch (state) {
        case MEMBER_DECLARATION:
            Type t = typeList.getType(dom.getClassTrace());
            t.addMember(name, type);
            break;
        case SEMANTIC_ANALYSIS:
            allocateVariable(name, type);
            break;
        default:
        }

        return null;
    }

    @Override
    public Node visitClassConstructionFunctionStatement(Mx_starParser.ClassConstructionFunctionStatementContext ctx) {
        FunctionNode node = (FunctionNode) visit(ctx.constructionFunctionStatement());

        if (state == VisitState.MEMBER_DECLARATION) {
            String name = node.name;

            if (!name.equals(dom.getCurrentClass())) {
                assert false;
            }

            Type t = typeList.getType(dom.getClassTrace());
            t.addMethod(name, dom.getClassTrace() + "." + name);
        }

        return null;
    }

    @Override
    public Node visitClassFunctionDefinitionStatement(Mx_starParser.ClassFunctionDefinitionStatementContext ctx) {
        FunctionNode node = (FunctionNode) visit(ctx.functionDefinitionStatement());

        if (state == VisitState.MEMBER_DECLARATION) {
            String name = node.name;

            if (name.equals(dom.getCurrentClass())) {
                assert false;
            }

            Type t = typeList.getType(dom.getClassTrace());
            t.addMethod(name, dom.getClassTrace() + "." + name);
        }

        return null;
    }

    ////////////////////////////// Function //////////////////////////////

    @Override
    public Node visitConstructionFunctionStatement(Mx_starParser.ConstructionFunctionStatementContext ctx) {
        String name = ctx.Identifier().getText();
        String trace = dom.getClassTrace();

        dom.enterFunc(trace, name, "void");

        LOGGER.fine("enter construction function: " + name);

        ParamListInstance node = (ParamListInstance) visit(ctx.paramListDefinition());

        if (state == VisitState.MEMBER_DECLARATION) {
            Func func = new Func(trace, name, "void");
            if (!dom.isGlobal()) {
                func.addParam(trace);
            }
            node.params.params.forEach(param -> func.addParam(param.second));

            if (funcList.addFunc(func) == false) {
                assert false;
            }
        } else {
            code.newSection(dom.getAddr());

            for (Pair<String, String> param : node.params.params) {
                allocateVariable(param.first, param.second);
            }

            if (ctx.statements() != null) {
                visit(ctx.statements());
            }

            code.packScope();
            code.packSection();
        }

        LOGGER.fine("exit construction function: " + name);
        dom.exitFunc();
        return new FunctionNode(name, "");
    }

    @Override
    public Node visitFunctionDefinitionStatement(Mx_starParser.FunctionDefinitionStatementContext ctx) {
        String name = ctx.Identifier().getText();
        String rtype = ctx.type().getText();

        String trace = dom.getClassTrace();

        if (!rtype.equals("void") && !typeList.hasType(rtype)) {
            assert false;
        }

        LOGGER.fine("enter function: " + name);

        dom.enterFunc(trace, name, rtype);

        ParamListInstance node = (ParamListInstance) visit(ctx.paramListDefinition());

        if (state == VisitState.MEMBER_DECLARATION) {
            Func func = new Func(trace, name, rtype);
            if (!dom.isGlobal()) {
                func.addParam(trace);
            }
            node.params.params.forEach(param -> func.addParam(param.second));

            if (funcList.addFunc(func) == false) {
                assert false;
            }
        } else {
            code.newSection(dom.getAddr());

            for (Pair<String, String> param : node.params.params) {
                allocateVariable(param.first, param.second);
            }

            if (ctx.statements() != null) {
                visit(ctx.statements());
            }

            code.packScope();
            code.packSection();
        }

        LOGGER.fine("exit function: " + name);
        dom.exitFunc();
        return new FunctionNode(name, rtype);
    }

    @Override
    public Node visitParamListDefinition(Mx_starParser.ParamListDefinitionContext ctx) {
        ParamsInstance params = new ParamsInstance();
        ctx.variableDeclaration().forEach(member -> {
            VariableNode node = (VariableNode) visit(member);
            params.add(node.name, node.type);
        });
        return new ParamListInstance(params);
    }

    @Override
    public Node visitParamList(Mx_starParser.ParamListContext ctx) {
        Params params = new Params();
        ctx.object().forEach(member -> {
            ObjectNode node = (ObjectNode) visit(member);
            params.add(node.type);
        });
        return new ParamList(params);
    }

    ////////////////////////////// Statement //////////////////////////////

    @Override
    public Node visitStatements(Mx_starParser.StatementsContext ctx) {
        ctx.statement().forEach(ch -> visit(ch));
        return null;
    }

    // @Override
    // public Node visitStmtEmptyStatement(Mx_starParser.StmtEmptyStatementContext ctx) {
    //     return null;
    // }

    @Override
    public Node visitStmtVariableDeclarationStatement(Mx_starParser.StmtVariableDeclarationStatementContext ctx) {
        visit(ctx.variableDeclarationStatement());
        return null;
    }

    @Override
    public Node visitStmtVariableDefinitionStatement(Mx_starParser.StmtVariableDefinitionStatementContext ctx) {
        visit(ctx.variableDefinitionStatement());
        return null;
    }

    @Override
    public Node visitVariableAssignmentStatement(Mx_starParser.VariableAssignmentStatementContext ctx) {
        visit(ctx.variableAssignment());
        return null;
    }

    @Override
    public Node visitStmtVariableAssignmentStatement(Mx_starParser.StmtVariableAssignmentStatementContext ctx) {
        visit(ctx.variableAssignmentStatement());
        return null;
    }

    @Override
    public Node visitStmtObjectStatement(Mx_starParser.StmtObjectStatementContext ctx) {
        visit(ctx.objectStatement());
        return null;
    }

    @Override
    public Node visitStmtLoopStatement(Mx_starParser.StmtLoopStatementContext ctx) {
        visit(ctx.loopStatement());
        return null;
    }

    @Override
    public Node visitStmtConditionStatement(Mx_starParser.StmtConditionStatementContext ctx) {
        visit(ctx.conditionStatement());
        return null;
    }

    @Override
    public Node visitStmtJumpStatement(Mx_starParser.StmtJumpStatementContext ctx) {
        visit(ctx.jumpStatement());
        return null;
    }

    @Override
    public Node visitStmtCompoundStatement(Mx_starParser.StmtCompoundStatementContext ctx) {
        visit(ctx.compoundStatement());
        return null;
    }

    @Override
    public Node visitJumpReturn(Mx_starParser.JumpReturnContext ctx) {
        if (!dom.inFunc()) {
            assert false;
        }

        if (ctx.object() != null) {
            ObjectNode node = (ObjectNode) visit(ctx.object());
            String objType = node.type;

            String rtype = dom.getRtype();
            if (objType.equals("null")) {
                if (!rtype.endsWith("[]") && typeList.getType(rtype).isBuiltin()) {
                    assert false;
                    return null;
                }
            } else {
                if (!objType.equals(dom.getRtype())) {
                    assert false;
                    return null;
                }
            }

        } else {
            if (!dom.getRtype().equals("void")) {
                assert false;
                return null;
            }
        }
        return null;
    }

    @Override
    public Node visitJumpBreak(Mx_starParser.JumpBreakContext ctx) {
        if (!dom.inLoop()) {
            assert false;
        }
        return null;
    }

    @Override
    public Node visitJumpContinue(Mx_starParser.JumpContinueContext ctx) {
        if (!dom.inLoop()) {
            assert false;
        }
        return null;
    }

    @Override
    public Node visitWhileLoop(Mx_starParser.WhileLoopContext ctx) {
        dom.enterLoop(-1);

        ObjectNode node = (ObjectNode) visit(ctx.object());

        if (!node.type.equals("bool")) {
            assert false;
        }

        visit(ctx.statement());

        dom.exitLoop();

        return null;
    }

    @Override
    public Node visitForLoop(Mx_starParser.ForLoopContext ctx) {
        dom.enterLoop(-1);

        if (ctx.forCondition().forCondition1() != null) {
            visit(ctx.forCondition().forCondition1());
        }

        if (ctx.forCondition().forCondition2() != null) {
            ObjectNode node = (ObjectNode) visit(ctx.forCondition().forCondition2().object());
            if (!node.type.equals("bool")) {
                assert false;
            }
        }

        if (ctx.forCondition().forCondition3() != null) {
            visit(ctx.forCondition().forCondition3());
        }

        visit(ctx.statement());

        dom.exitLoop();

        return null;
    }

    @Override
    public Node visitForCdt1VariableDeclaration(Mx_starParser.ForCdt1VariableDeclarationContext ctx) {
        VariableNode node = (VariableNode) visit(ctx.variableDeclaration());
        allocateVariable(node.name, node.type);
        return null;
    }

    @Override
    public Node visitForCdt1VariableDefinition(Mx_starParser.ForCdt1VariableDefinitionContext ctx) {
        VariableNode node = (VariableNode) visit(ctx.variableDefinition());
        allocateVariable(node.name, node.type);
        return null;
    }

    @Override
    public Node visitForCdt1VariableAssignment(Mx_starParser.ForCdt1VariableAssignmentContext ctx) {
        visit(ctx.variableAssignment());
        return null;
    }

    @Override
    public Node visitForCdt1Object(Mx_starParser.ForCdt1ObjectContext ctx) {
        visit(ctx.object());
        return null;
    }

    @Override
    public Node visitForCdt3VariableAssignment(Mx_starParser.ForCdt3VariableAssignmentContext ctx) {
        visit(ctx.variableAssignment());
        return null;
    }

    @Override
    public Node visitForCdt3Object(Mx_starParser.ForCdt3ObjectContext ctx) {
        visit(ctx.object());
        return null;
    }

    @Override
    public Node visitConditionStatement(Mx_starParser.ConditionStatementContext ctx) {
        ObjectNode node = (ObjectNode) visit(ctx.object());

        if (!node.type.equals("bool")) {
            assert false;
        }

        ctx.statement().forEach(ch -> {
            dom.enterCondition(-1);
            code.packScope();

            visit(ch);

            dom.exitCondition();
        });

        code.packScope();
        return null;
    }

    @Override
    public Node visitObjectStatement(Mx_starParser.ObjectStatementContext ctx) {
        visit(ctx.object());
        return null;
    }

    @Override
    public Node visitVariableDeclarationStatement(Mx_starParser.VariableDeclarationStatementContext ctx) {
        VariableNode node = (VariableNode) visit(ctx.variableDeclaration());
        allocateVariable(node.name, node.type);
        return null;
    }

    @Override
    public Node visitVariableDefinitionStatement(Mx_starParser.VariableDefinitionStatementContext ctx) {
        VariableNode node = (VariableNode) visit(ctx.variableDefinition());
        allocateVariable(node.name, node.type);
        return null;
    }

    public Node visitCompoundStatement(Mx_starParser.CompoundStatementContext ctx) {
        if (ctx.statements() != null) {
            dom.enterCondition(-1);

            visit(ctx.statements());

            dom.exitCondition();
        }
        return null;
    }

    @Override
    public Node visitVariableDeclaration(Mx_starParser.VariableDeclarationContext ctx) {
        String name = ctx.Identifier().getText(), type = ctx.type().getText();

        if (!typeList.hasType(type)) {
            assert false;
            return null;
        }

        return new VariableNode(name, type);
    }

    @Override
    public Node visitVariableDefinition(Mx_starParser.VariableDefinitionContext ctx) {
        ObjectNode node = (ObjectNode) visit(ctx.object());

        String name = ctx.Identifier().getText();
        String type = ctx.type().getText();

        if (!typeList.hasType(type)) {
            assert false;
            return null;
        }

        if (node.type.equals("null")) {
            if (!type.endsWith("[]") && typeList.getType(type).isBuiltin()) {
                assert false;
                return null;
            }
        } else {
            if (!type.equals(node.type)) {
                assert false;
                return null;
            }
        }

        return new VariableNode(name, type);
    }

    @Override
    public Node visitVariableAssignment(Mx_starParser.VariableAssignmentContext ctx) {
        ObjectNode node = (ObjectNode) visit(ctx.object());

        VariableNode lvaluenode = (VariableNode) visit(ctx.lvalue());

        String type = lvaluenode.type;

        if (node.type.equals("null")) {
            if (!type.endsWith("[]") && typeList.getType(type).isBuiltin()) {
                assert false;
                return null;
            }
        } else {
            if (!type.equals(node.type)) {
                assert false;
                return null;
            }
        }

        return null;
    }

    @Override
    public Node visitIdentifierLvalue(Mx_starParser.IdentifierLvalueContext ctx) {
        String name = ctx.Identifier().getText(), type = null;

        if (!dom.hasVar(name)) {
            assert false;
            return null;
        }

        type = dom.getVarType(name);

        return new VariableNode(name, type);
    }

    @Override
    public Node visitMemberLvalue(Mx_starParser.MemberLvalueContext ctx) {
        String identifier_typename;

        if (ctx.This() != null) {
            if (dom.isGlobal()) {
                assert false;
                return null;
            }
            identifier_typename = dom.getClassTrace();
        } else {
            VariableNode node = (VariableNode) visit(ctx.lvalue());
            identifier_typename = node.type;
        }

        String name = ctx.Identifier().getText(), type = null;

        Type identifier_type = typeList.getType(identifier_typename);

        if (!identifier_type.hasMember(name)) {
            assert false;
            return null;
        }

        type = identifier_type.getVarType(name);

        return new VariableNode(name, type);
    }

    @Override
    public Node visitSubscriptLvalue(Mx_starParser.SubscriptLvalueContext ctx) {
        VariableNode arrayNode = (VariableNode) visit(ctx.lvalue());

        String name = arrayNode.name, type = arrayNode.type;

        if (!type.endsWith("[]")) {
            assert false;
            return null;
        } else {
            type = type.substring(0, type.length() - 2);
        }

        ObjectNode subNode = (ObjectNode) visit(ctx.object());

        if (!subNode.type.equals("int")) {
            assert false;
            return null;
        }

        return new VariableNode(name, type);
    }

    @Override
    public Node visitThisObject(Mx_starParser.ThisObjectContext ctx) {
        if (dom.isGlobal()) {
            assert false;
            return null;
        }
        String type = dom.getClassTrace();
        return new ObjectNode(null, type, null);
    }

    @Override
    public Node visitIdentifierObject(Mx_starParser.IdentifierObjectContext ctx) {
        String name = ctx.Identifier().getText(), type = null;

        Variable variable = dom.getVar(name);

        if (variable != null && variable.owner != null && variable.owner.equals(dom.getAddr())) {
            // local variable
            type = variable.type;
            return new ObjectNode(name, type, null);
        }

        if (!dom.isGlobal()) {
            String trace = dom.getClassTrace();
            if (funcList.getFunc(trace + "." + name) != null) {
                // method
                String owner = trace;
                type = "__method__";
                return new ObjectNode(name, type, owner);
            }
        }

        if (variable != null) {
            // global variable
            type = variable.type;
            return new ObjectNode(name, type, null);
        }

        Func func = funcList.getFunc(name);
        if (func != null) {
            // global function
            type = "__func__";
            return new ObjectNode(name, type, null);
        }

        assert false;
        return null;
    }

    @Override
    public Node visitConstantObject(Mx_starParser.ConstantObjectContext ctx) {
        String type = null;

        ConstantContext constant = ctx.constant();
        if (constant instanceof Mx_starParser.NullContext) {
            type = "null";
        } else if (constant instanceof Mx_starParser.LogicalConstantContext) {
            type = "bool";
        } else if (constant instanceof Mx_starParser.IntegerConstantContext) {
            type = "int";
        } else if (constant instanceof Mx_starParser.StringLiteralContext) {
            type = "string";
        } else {
            assert false;
            return null;
        }

        return new ObjectNode(null, type, null);
    }

    @Override
    public Node visitLvalueObject(Mx_starParser.LvalueObjectContext ctx) {
        VariableNode node = (VariableNode) visit(ctx.lvalue());
        return new ObjectNode(null, node.type, null);
    }

    @Override
    public Node visitMemberObject(Mx_starParser.MemberObjectContext ctx) {
        String name = null, type = null, owner = null;
        String member = ctx.Identifier().getText();

        ObjectNode node = (ObjectNode) visit(ctx.object());

        Type identifier_type = typeList.getType(node.type);

        if (identifier_type.hasMember(member)) {
            type = identifier_type.getVarType(member);
            return new ObjectNode(name, type, owner);
        }

        if (identifier_type.hasMethod(member)) {
            name = member;
            type = "__method__";
            if (node.type.endsWith("[]")) {
                owner = "__array__";
            } else {
                owner = node.type;
            }
            return new ObjectNode(name, type, owner);
        }

        assert false;
        return null;
    }

    @Override
    public Node visitBracketObject(Mx_starParser.BracketObjectContext ctx) {
        ObjectNode node = (ObjectNode) visit(ctx.object());
        return new ObjectNode(null, node.type, null);
    }

    @Override
    public Node visitFunctionReturnObject(Mx_starParser.FunctionReturnObjectContext ctx) {
        ObjectNode node = (ObjectNode) visit(ctx.object());

        if (!node.type.equals("__func__") && !node.type.equals("__method__")) {
            assert (false);
            return null;
        }

        String addr = node.name;
        if (node.owner != null) {
            addr = node.owner + "." + addr;
        }

        Func func = funcList.getFunc(addr);

        ParamList paramNode = (ParamList) visit(ctx.paramList());

        Params params = new Params();
        if (node.type.equals("__method__")) {
            params.add(func.params.params.get(0));
        }
        paramNode.params.params.forEach(param -> params.add(param));

        if (!func.params.match(params)) {
            assert false;
            return null;
        }

        String type = func.rtype;
        return new ObjectNode(null, type, null);
    }

    @Override
    public Node visitSubscriptObject(Mx_starParser.SubscriptObjectContext ctx) {
        ObjectNode arrayNode = (ObjectNode) visit(ctx.object(0));

        String type = arrayNode.type;
        if (!type.endsWith("[]")) {
            assert (false);
            return null;
        } else {
            type = type.substring(0, type.length() - 2);
        }

        ObjectNode subNode = (ObjectNode) visit(ctx.object(1));
        if (!subNode.type.equals("int")) {
            assert (false);
            return null;
        }

        return new ObjectNode(null, type, null);
    }

    @Override
    public Node visitUnaryOperatorObject(Mx_starParser.UnaryOperatorObjectContext ctx) {
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

        String objType = null;

        if (ctx.lvalue() != null) {
            VariableNode node = (VariableNode) visit(ctx.lvalue());
            objType = node.type;
        } else {
            ObjectNode node = (ObjectNode) visit(ctx.object());
            objType = node.type;
        }

        String methodName = typeList.getType(objType).getMethod(method);

        if (methodName == null) {
            assert false;
            return null;
        }

        Func func = funcList.getFunc(methodName);

        Params params = new Params();
        params.add(objType);

        if (!func.params.match(params)) {
            assert false;
            return null;
        }

        String type = func.rtype;

        return new ObjectNode(null, type, null);
    }

    @Override
    public Node visitNewObject(Mx_starParser.NewObjectContext ctx) {
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

                ObjectNode node = (ObjectNode) visit(ch);

                if (!node.type.equals("int")) {
                    assert false;
                    return null;
                }
            }
        }

        String type = ctx.type().getText();

        if (!typeList.hasType(type)) {
            assert false;
            return null;
        }

        for (int i = 0; i < cntLeftBracket; i++) {
            type += "[]";
        }

        return new ObjectNode(null, type, null);
    }

    @Override
    public Node visitBinaryOperatorObject(Mx_starParser.BinaryOperatorObjectContext ctx) {
        String name = null, type = null, owner = null;
        ObjectNode node0 = (ObjectNode) visit(ctx.object(0));
        String type0 = node0.type;

        ObjectNode node1 = (ObjectNode) visit(ctx.object(1));
        String type1 = node1.type;

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
            if (type0.equals("null") || type1.equals("null")) {
                if (!type0.endsWith("[]") && !type1.endsWith("[]")) {
                    if (type0 != "null" && typeList.getType(type0).isBuiltin()
                            || type1 != "null" && typeList.getType(type1).isBuiltin()) {
                        assert false;
                    }
                }
                type = "bool";
                return new ObjectNode(name, type, owner);
            }
            break;
        case "!=":
            method = "__ne__";
            if (type0.equals("null") || type1.equals("null")) {
                if (!type0.endsWith("[]") && !type1.endsWith("[]")) {
                    if (type0 != "null" && typeList.getType(type0).isBuiltin()
                            || type1 != "null" && typeList.getType(type1).isBuiltin()) {
                        assert false;
                    }
                }
                type = "bool";
                return new ObjectNode(name, type, owner);
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

        String methodName = typeList.getType(node0.type).getMethod(method);

        if (methodName == null) {
            assert false;
            return null;
        }

        Func func = funcList.getFunc(methodName);

        Params params = new Params();
        params.add(node0.type);
        params.add(node1.type);

        if (!func.params.match(params)) {
            assert false;
            return null;
        }

        type = func.rtype;
        return new ObjectNode(name, type, owner);
    }

    void allocateVariable(String name, String type) {
        if (!dom.canAllocate(name)) {
            assert false;
            return;
        }

        counterVar++;
        Variable variable = new Variable(counterVar, name, type, dom.getAddr());
        varList.add(variable);
        dom.addVar(variable);
    }

    void registerBuiltinType() {
        Type t_bool = new Type("bool", true);
        Arrays.asList("__lgcand__", "__lgcor__", "__eq__", "__ne__").forEach(method -> {
            String addr = t_bool.name + "." + method;
            t_bool.addMethod(method, addr);
            Func func = new Func(t_bool.name, method, t_bool.name);
            func.addParam(t_bool.name);
            func.addParam(t_bool.name);
            funcList.addFunc(func);
        });
        Arrays.asList("__lgcnot__").forEach(method -> {
            String addr = t_bool.name + "." + method;
            t_bool.addMethod(method, addr);
            Func func = new Func(t_bool.name, method, t_bool.name);
            func.addParam(t_bool.name);
            funcList.addFunc(func);
        });
        typeList.addType(t_bool);

        Type t_int = new Type("int", true);
        Arrays.asList("__add__", "__sub__", "__mul__", "__div__", "__mod__", "__shl__", "__shr__", "__bitand__",
                "__bitxor__", "__bitor__").forEach(method -> {
                    String addr = t_int.name + "." + method;
                    t_int.addMethod(method, addr);
                    Func func = new Func(t_int.name, method, t_int.name);
                    func.addParam(t_int.name);
                    func.addParam(t_int.name);
                    funcList.addFunc(func);
                });
        Arrays.asList("__lt__", "__gt__", "__le__", "__ge__", "__eq__", "__ne__").forEach(method -> {
            String addr = t_int.name + "." + method;
            t_int.addMethod(method, addr);
            Func func = new Func(t_int.name, method, "bool");
            func.addParam(t_int.name);
            func.addParam(t_int.name);
            funcList.addFunc(func);
        });
        Arrays.asList("__pos__", "__neg__", "__bitinv__", "__preinc__", "__predec__", "__postinc__", "__postdec__")
                .forEach(method -> {
                    String addr = t_int.name + "." + method;
                    t_int.addMethod(method, addr);
                    Func func = new Func(t_int.name, method, t_int.name);
                    func.addParam(t_int.name);
                    funcList.addFunc(func);
                });
        typeList.addType(t_int);

        Type t_string = new Type("string", true);
        Arrays.asList("__add__").forEach(method -> {
            String addr = t_string.name + "." + method;
            t_string.addMethod(method, addr);
            Func func = new Func(t_string.name, method, t_string.name);
            func.addParam(t_string.name);
            func.addParam(t_string.name);
            funcList.addFunc(func);
        });
        Arrays.asList("__lt__", "__gt__", "__le__", "__ge__", "__eq__", "__ne__").forEach(method -> {
            String addr = t_string.name + "." + method;
            t_string.addMethod(method, addr);
            Func func = new Func(t_string.name, method, "bool");
            func.addParam(t_string.name);
            func.addParam(t_string.name);
            funcList.addFunc(func);
        });
        Arrays.asList("length", "parseInt").forEach(method -> {
            String addr = t_string.name + "." + method;
            t_string.addMethod(method, addr);
            Func func = new Func(t_string.name, method, "int");
            func.addParam("string");
            funcList.addFunc(func);
        });
        Arrays.asList("ord").forEach(method -> {
            String addr = t_string.name + "." + method;
            t_string.addMethod(method, addr);
            Func func = new Func(t_string.name, method, "int");
            func.addParam("string");
            func.addParam("int");
            funcList.addFunc(func);
        });
        Arrays.asList("substring").forEach(method -> {
            String addr = t_string.name + "." + method;
            t_string.addMethod(method, addr);
            Func func = new Func(t_string.name, method, "string");
            func.addParam("string");
            func.addParam("int");
            func.addParam("int");
            funcList.addFunc(func);
        });
        typeList.addType(t_string);

        Type t_array = new Type("__array__", true);
        Arrays.asList("size").forEach(method -> {
            String addr = t_array.name + "." + method;
            t_array.addMethod(method, addr);
            Func func = new Func(t_array.name, method, "int");
            func.addParam("__array__");
            funcList.addFunc(func);
        });
        typeList.addType(t_array);
    }

    void registerBuiltinFunc() {
        Func print = new Func(null, "print", "void");
        print.addParam("string");
        funcList.addFunc(print);

        Func println = new Func(null, "println", "void");
        println.addParam("string");
        funcList.addFunc(println);

        Func getInt = new Func(null, "getInt", "int");
        funcList.addFunc(getInt);

        Func getString = new Func(null, "getString", "string");
        funcList.addFunc(getString);

        Func toString = new Func(null, "toString", "string");
        toString.addParam("int");
        funcList.addFunc(toString);
    }
}