package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.Mx_starBaseVisitor;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser.*;
import com.github.espylapiza.compiler_mxstar.logging.*;
import com.github.espylapiza.compiler_mxstar.utils.Pair;
import com.google.gson.JsonObject;

import org.antlr.v4.runtime.tree.ParseTree;

enum VisitState {
    TYPE_DECLARATION, MEMBER_DECLARATION, SEMANTIC_ANALYSIS, TRANSLATION_ENTRANCE, TRANSLATION
}

public class PizzaIRVisitor extends Mx_starBaseVisitor<Node> {
    JsonObject data;

    public static VisitState state;

    public static Domain dom = new Domain();

    public static TypeList typeList = new TypeList();
    public static VarList varList = new VarList();
    public static FuncList funcList = new FuncList();

    public static int counter = 0;

    public static Code code = new Code();

    @Override
    public Node visitProgram(Mx_starParser.ProgramContext ctx) {
        data = new JsonObject();

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

        data.add("Type", typeList.toJson());
        data.add("Func", funcList.toJson());
        data.add("Var", varList.toJson());

        return null;
    }

    @Override
    public Node visitProgramVariableDeclarationStatement(Mx_starParser.ProgramVariableDeclarationStatementContext ctx) {
        if (state != VisitState.SEMANTIC_ANALYSIS) {
            return null;
        }

        VariableDeclarationContext variableDeclaration = ctx.variableDeclarationStatement().variableDeclaration();
        VariableNode lser = (VariableNode) visit(variableDeclaration);

        String name = lser.name, type = lser.type;
        allocateVariable(name, type);
        return null;
    }

    @Override
    public Node visitProgramVariableDefinitionStatement(Mx_starParser.ProgramVariableDefinitionStatementContext ctx) {
        if (state != VisitState.SEMANTIC_ANALYSIS) {
            return null;
        }

        VariableDefinitionContext variableDefinition = ctx.variableDefinitionStatement().variableDefinition();
        VariableNode lser = (VariableNode) visit(variableDefinition);

        String name = lser.name, type = lser.type;
        allocateVariable(name, type);
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

    @Override
    public Node visitClassVariableDeclarationStatement(Mx_starParser.ClassVariableDeclarationStatementContext ctx) {
        VariableNode lser = (VariableNode) visit(ctx.variableDeclarationStatement().variableDeclaration());
        String name = lser.name, type = lser.type;

        if (state == VisitState.MEMBER_DECLARATION) {
            Type t = typeList.getType(dom.getClassTrace());

            t.addMember(name, type);
        } else {
            allocateVariable(name, type);
        }
        return null;
    }

    @Override
    public Node visitClassConstructionFunctionStatement(Mx_starParser.ClassConstructionFunctionStatementContext ctx) {
        FunctionNode lser = (FunctionNode) visit(ctx.constructionFunctionStatement());

        if (state == VisitState.MEMBER_DECLARATION) {
            String name = lser.name;

            if (!dom.getLastClass().equals(name)) {
                assert false;
            }

            Type t = typeList.getType(dom.getClassTrace());

            t.addMethod(name, dom.getClassTrace() + "." + name);
        } else {

        }
        return null;
    }

    @Override
    public Node visitClassFunctionDefinitionStatement(Mx_starParser.ClassFunctionDefinitionStatementContext ctx) {
        FunctionNode lser = (FunctionNode) visit(ctx.functionDefinitionStatement());

        if (state == VisitState.MEMBER_DECLARATION) {
            String name = lser.name;
            Type t = typeList.getType(dom.getClassTrace());

            t.addMethod(name, dom.getClassTrace() + "." + name);
        } else {

        }
        return null;
    }

    @Override
    public Node visitConstructionFunctionStatement(Mx_starParser.ConstructionFunctionStatementContext ctx) {
        String name = ctx.Identifier().getText();
        String trace = dom.getClassTrace();

        dom.enterFunc(trace, name, "void");

        Logging.debug("enter construction function: " + name);

        ParamListInstance lser = (ParamListInstance) visit(ctx.paramListDefinition());

        if (state == VisitState.MEMBER_DECLARATION) {
            Func func = new Func(trace, name, "void");
            if (!dom.isGlobal()) {
                func.addParam(trace);
            }
            lser.params.params.forEach(param -> func.addParam(param.second));

            if (funcList.addFunc(func) == false) {
                assert false;
            }
        } else {
            code.newSection(dom.getAddr());

            for (Pair<String, String> param : lser.params.params) {
                allocateVariable(param.first, param.second);
            }

            if (ctx.statements() != null) {
                visit(ctx.statements());
            }

            code.packScope();
            code.packSection();
        }

        Logging.debug("exit construction function: " + name);
        dom.exitFunc();
        return new FunctionNode(name, "");
    }

    @Override
    public Node visitFunctionDefinitionStatement(Mx_starParser.FunctionDefinitionStatementContext ctx) {
        String name = ctx.Identifier().getText();
        String rtype = ctx.type().getText();
        FunctionNode result = new FunctionNode(name, rtype);
        String trace = PizzaIRVisitor.dom.getClassTrace();

        if (!rtype.equals("void") && !PizzaIRVisitor.typeList.hasType(rtype)) {
            assert false;
        }
        Logging.debug("enter function: " + name);

        PizzaIRVisitor.dom.enterFunc(trace, name, rtype);

        ParamListInstance lser = (ParamListInstance) visit(ctx.paramListDefinition());

        if (PizzaIRVisitor.state == VisitState.MEMBER_DECLARATION) {
            String owner;
            if (!PizzaIRVisitor.dom.isGlobal()) {
                owner = trace;
            } else {
                owner = null;
            }

            Func func = new Func(owner, name, rtype);
            if (!PizzaIRVisitor.dom.isGlobal()) {
                func.addParam(trace);
            }
            lser.params.params.forEach(param -> func.addParam(param.second));

            if (PizzaIRVisitor.funcList.addFunc(func) == false) {
                assert false;
            }
        } else {
            PizzaIRVisitor.code.newSection(PizzaIRVisitor.dom.getAddr());

            for (Pair<String, String> param : lser.params.params) {
                PizzaIRVisitor.allocateVariable(param.first, param.second);
            }

            if (ctx.statements() != null) {
                visit(ctx.statements());
            }

            PizzaIRVisitor.code.packScope();
            PizzaIRVisitor.code.packSection();
        }

        Logging.debug("exit function: " + name);
        PizzaIRVisitor.dom.exitFunc();
        return result;
    }

    @Override
    public Node visitParamListDefinition(Mx_starParser.ParamListDefinitionContext ctx) {
        ParamsInstance params = new ParamsInstance();
        for (VariableDeclarationContext member : ctx.variableDeclaration()) {
            VariableNode lser = (VariableNode) visit(member);
            params.add(lser.name, lser.type);
        }
        return new ParamListInstance(params);
    }

    @Override
    public Node visitParamList(Mx_starParser.ParamListContext ctx) {
        Params params = new Params();

        ctx.object().forEach(member -> {
            ObjectNode lser = (ObjectNode) visit(member);
            String type = lser.type;
            params.add(type);
        });
        return new ParamList(params);
    }

    @Override
    public Node visitStatements(Mx_starParser.StatementsContext ctx) {
        ctx.statement().forEach(ch -> {
            visit(ch);
        });
        return null;
    }

    @Override
    public Node visitObjectStatement(Mx_starParser.ObjectStatementContext ctx) {
        visit(ctx.object());
        return null;
    }

    @Override
    public Node visitVariableDeclarationStatement(Mx_starParser.VariableDeclarationStatementContext ctx) {
        VariableNode lser = (VariableNode) visit(ctx.variableDeclaration());
        PizzaIRVisitor.allocateVariable(lser.name, lser.type);
        return null;
    }

    @Override
    public Node visitVariableDefinitionStatement(Mx_starParser.VariableDefinitionStatementContext ctx) {
        VariableNode lser = (VariableNode) visit(ctx.variableDefinition());
        PizzaIRVisitor.allocateVariable(lser.name, lser.type);
        return null;
    }

    public Node visitCompoundStatement(Mx_starParser.CompoundStatementContext ctx) {
        if (ctx.statements() != null) {
            PizzaIRVisitor.dom.enterCondition(-1);

            visit(ctx.statements());

            PizzaIRVisitor.dom.exitCondition();
        }
        return null;
    }

    @Override
    public Node visitStmtEmptyStatement(Mx_starParser.StmtEmptyStatementContext ctx) {
        return null;
    }

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
        if (!PizzaIRVisitor.dom.inFunc()) {
            assert false;
        }

        if (ctx.object() != null) {
            ObjectNode lser = (ObjectNode) visit(ctx.object());
            String objType = lser.type;

            String rtype = PizzaIRVisitor.dom.getRtype();
            if (objType.equals("null")) {
                if (rtype.endsWith("[]") || !PizzaIRVisitor.typeList.getType(rtype).isBuiltin()) {
                    // return null
                } else {
                    assert false;
                }
            } else if (!objType.equals(PizzaIRVisitor.dom.getRtype())) {
                assert false;
            } else {

            }

        } else {
            if (!PizzaIRVisitor.dom.getRtype().equals("void")) {
                assert false;
            }
        }
        return null;
    }

    @Override
    public Node visitJumpBreak(Mx_starParser.JumpBreakContext ctx) {
        if (!PizzaIRVisitor.dom.inLoop()) {
            assert false;
        }
        return null;
    }

    @Override
    public Node visitJumpContinue(Mx_starParser.JumpContinueContext ctx) {
        if (!PizzaIRVisitor.dom.inLoop()) {
            assert false;
        }
        return null;
    }

    @Override
    public Node visitWhileLoop(Mx_starParser.WhileLoopContext ctx) {
        PizzaIRVisitor.dom.enterLoop(-1);

        ObjectNode objLser = (ObjectNode) visit(ctx.object());

        if (!objLser.type.equals("bool")) {
            assert false;
        }

        visit(ctx.statement());

        PizzaIRVisitor.dom.exitLoop();
        return null;
    }

    @Override
    public Node visitForLoop(Mx_starParser.ForLoopContext ctx) {
        PizzaIRVisitor.dom.enterLoop(-1);

        if (ctx.forCondition().forCondition1() != null) {
            visit(ctx.forCondition().forCondition1());
        }
        if (ctx.forCondition().forCondition2() != null) {
            ObjectNode objLser = (ObjectNode) visit(ctx.forCondition().forCondition2().object());
            if (!objLser.type.equals("bool")) {
                assert false;
            }
        }

        if (ctx.forCondition().forCondition3() != null) {
            visit(ctx.forCondition().forCondition3());
        }

        visit(ctx.statement());

        PizzaIRVisitor.dom.exitLoop();
        return null;
    }

    @Override
    public Node visitForCdt1VariableDeclaration(Mx_starParser.ForCdt1VariableDeclarationContext ctx) {
        VariableNode lser = (VariableNode) visit(ctx.variableDeclaration());
        PizzaIRVisitor.allocateVariable(lser.name, lser.type);
        return null;
    }

    @Override
    public Node visitForCdt1VariableDefinition(Mx_starParser.ForCdt1VariableDefinitionContext ctx) {
        VariableNode lser = (VariableNode) visit(ctx.variableDefinition());
        PizzaIRVisitor.allocateVariable(lser.name, lser.type);
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
        ObjectNode objLser = (ObjectNode) visit(ctx.object());

        if (!objLser.type.equals("bool")) {
            assert false;
        }

        ctx.statement().forEach(ch -> {
            PizzaIRVisitor.dom.enterCondition(-1);
            PizzaIRVisitor.code.packScope();

            visit(ch);

            PizzaIRVisitor.dom.exitCondition();
        });

        PizzaIRVisitor.code.packScope();
        return null;
    }

    @Override
    public Node visitVariableDefinition(Mx_starParser.VariableDefinitionContext ctx) {
        ObjectNode lser = (ObjectNode) visit(ctx.object());

        String name = ctx.Identifier().getText();
        String type = ctx.type().getText();

        if (!PizzaIRVisitor.typeList.hasType(type)) {
            assert false;
        }

        if ((type.endsWith("[]") || !PizzaIRVisitor.typeList.getType(type).isBuiltin()) && lser.type.equals("null")) {

        } else {
            if (!type.equals(lser.type)) {
                assert false;
            }
        }
        return new VariableNode(name, type);
    }

    @Override
    public Node visitVariableAssignment(Mx_starParser.VariableAssignmentContext ctx) {
        ObjectNode objLser = (ObjectNode) visit(ctx.object());

        VariableNode lvalueLser = (VariableNode) visit(ctx.lvalue());

        // String name = lvalueLser.name;
        String type = lvalueLser.type;

        if ((type.endsWith("[]") || !PizzaIRVisitor.typeList.getType(type).isBuiltin())
                && objLser.type.equals("null")) {

        } else {
            if (!type.equals(objLser.type)) {
                assert false;
            }
        }
        return null;
    }

    @Override
    public Node visitIdentifierLvalue(Mx_starParser.IdentifierLvalueContext ctx) {
        String name = ctx.Identifier().getText(), type = "";

        if (PizzaIRVisitor.dom.hasVar(name)) {
            type = PizzaIRVisitor.dom.getVarType(name);
        } else {
            assert false;
        }
        return new VariableNode(name, type);
    }

    @Override
    public Node visitMemberLvalue(Mx_starParser.MemberLvalueContext ctx) {
        String identifier_typename;

        if (ctx.This() != null) {
            if (PizzaIRVisitor.dom.isGlobal()) {
                assert false;
            }
            identifier_typename = PizzaIRVisitor.dom.getClassTrace();
        } else {
            VariableNode lser = (VariableNode) visit(ctx.lvalue());

            identifier_typename = lser.type;
        }

        String name = ctx.Identifier().getText(), type = "";

        Type identifier_type = PizzaIRVisitor.typeList.getType(identifier_typename);

        if (identifier_type.hasMember(name)) {
            type = identifier_type.getVarType(name);
        } else {
            assert false;
        }
        return new VariableNode(name, type);
    }

    @Override
    public Node visitSubscriptLvalue(Mx_starParser.SubscriptLvalueContext ctx) {
        VariableNode lvalueLser = (VariableNode) visit(ctx.lvalue());

        String name = lvalueLser.name, type = lvalueLser.type;
        if (!type.endsWith("[]")) {
            assert false;
        }

        ObjectNode objLser = (ObjectNode) visit(ctx.object());
        if (!objLser.type.equals("int")) {
            assert false;
        }

        type = type.substring(0, type.length() - 2);
        return new VariableNode(name, type);
    }

    @Override
    public Node visitVariableDeclaration(Mx_starParser.VariableDeclarationContext ctx) {
        String name = ctx.Identifier().getText();
        String type = ctx.type().getText();

        if (!PizzaIRVisitor.typeList.hasType(type)) {
            assert false;
        }
        return new VariableNode(name, type);
    }

    @Override
    public Node visitThisObject(Mx_starParser.ThisObjectContext ctx) {
        if (PizzaIRVisitor.dom.isGlobal()) {
            assert false;
        }
        String type = PizzaIRVisitor.dom.getClassTrace();
        return new ObjectNode(null, type, null);
    }

    @Override
    public Node visitIdentifierObject(Mx_starParser.IdentifierObjectContext ctx) {
        String name = ctx.Identifier().getText(), type = null;

        Variable variable = PizzaIRVisitor.dom.getVar(name);

        String owner = "";
        if (variable != null && variable.owner != null) {
            owner = variable.owner;
        }

        if (variable != null && owner.equals(PizzaIRVisitor.dom.getAddr())) {
            // local variable
            type = variable.type;
            return new ObjectNode(name, type, null);
        }
        if (!PizzaIRVisitor.dom.isGlobal()) {
            String trace = PizzaIRVisitor.dom.getClassTrace();
            if (PizzaIRVisitor.funcList.getFunc(trace + "." + name) != null) {
                // method
                String true_owner = trace;
                type = "__method__";
                return new ObjectNode(name, type, true_owner);
            }
        }

        if (variable != null) {
            // global variable
            type = variable.type;
            return new ObjectNode(name, type, null);
        }

        Func func = PizzaIRVisitor.funcList.getFunc(name);
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
        }
        return new ObjectNode(null, type, null);
    }

    @Override
    public Node visitLvalueObject(Mx_starParser.LvalueObjectContext ctx) {
        VariableNode lser = (VariableNode) visit(ctx.lvalue());
        String type = lser.type;
        return new ObjectNode(null, type, null);
    }

    @Override
    public Node visitMemberObject(Mx_starParser.MemberObjectContext ctx) {
        String name = null, type = null, owner = null;
        String member = ctx.Identifier().getText();

        // if (ctx.This() != null) {
        // if (PizzaIRVisitor.dom.isGlobal()) {
        // assert false;
        // }
        // identifier_typename = PizzaIRVisitor.dom.getClassTrace();
        // }

        ObjectNode lser = (ObjectNode) visit(ctx.object());

        Type identifier_type = PizzaIRVisitor.typeList.getType(lser.type);

        if (identifier_type.hasMember(member)) {
            type = identifier_type.getVarType(member);
        } else if (identifier_type.hasMethod(member)) {
            type = "__method__";
            if (lser.type.endsWith("[]")) {
                owner = "__array__";
            } else {
                owner = lser.type;
            }
            name = member;
        } else {
            assert false;
        }
        return new ObjectNode(name, type, owner);
    }

    @Override
    public Node visitBracketObject(Mx_starParser.BracketObjectContext ctx) {
        ObjectNode lser = (ObjectNode) visit(ctx.object());
        String type = lser.type;
        return new ObjectNode(null, type, null);
    }

    @Override
    public Node visitFunctionReturnObject(Mx_starParser.FunctionReturnObjectContext ctx) {
        ObjectNode lser = (ObjectNode) visit(ctx.object());

        if (!lser.type.equals("__func__") && !lser.type.equals("__method__")) {
            assert (false);
        }

        String addr = lser.name;
        if (lser.owner != null) {
            addr = lser.owner + "." + addr;
        }

        Func func = PizzaIRVisitor.funcList.getFunc(addr);

        ParamList paramLser = (ParamList) visit(ctx.paramList());

        Params params = new Params();
        if (lser.type.equals("__method__")) {
            params.add(func.params.params.get(0));
        }
        for (String param : paramLser.params.params) {
            params.add(param);
        }

        if (!func.params.match(params)) {
            assert false;
        }

        String type = func.rtype;
        return new ObjectNode(null, type, null);
    }

    @Override
    public Node visitSubscriptObject(Mx_starParser.SubscriptObjectContext ctx) {
        ObjectNode obj = (ObjectNode) visit(ctx.object(0));

        String type = obj.type;
        if (!type.endsWith("[]")) {
            assert (false);
        }

        ObjectNode sub = (ObjectNode) visit(ctx.object(1));
        if (!sub.type.equals("int")) {
            assert (false);
        }

        type = type.substring(0, type.length() - 2);

        return new ObjectNode(null, type, null);
    }

    @Override
    public Node visitUnaryOperatorObject(Mx_starParser.UnaryOperatorObjectContext ctx) {
        String op = ctx.op.getText();
        String method = "";
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
        }

        String objType = "";

        if (ctx.lvalue() != null) {
            VariableNode lser = (VariableNode) visit(ctx.lvalue());
            objType = lser.type;
        } else {
            ObjectNode lser = (ObjectNode) visit(ctx.object());
            objType = lser.type;
        }

        String fname = PizzaIRVisitor.typeList.getType(objType).getMethod(method);

        if (fname == null) {
            assert false;
        }

        Func func = PizzaIRVisitor.funcList.getFunc(fname);

        Params params = new Params();
        params.add(objType);
        if (!func.params.match(params)) {
            assert false;
        }

        String type = func.rtype;

        return new ObjectNode(null, type, null);
    }

    @Override
    public Node visitNewObject(Mx_starParser.NewObjectContext ctx) {
        int cntSquareBracket = 0;
        int counter = 0;

        for (ParseTree ch : ctx.children) {
            if (ch.getText().equals("[")) {
                cntSquareBracket++;
                counter++;
            } else if (ch instanceof Mx_starParser.ObjectContext) {
                counter--;
                if (counter > 0) {
                    assert false;
                }
                ObjectNode lser = (ObjectNode) visit(ch);
                if (!lser.type.equals("int")) {
                    assert false;
                }
            }
        }
        String type = ctx.type().getText();

        if (!PizzaIRVisitor.typeList.hasType(type)) {
            assert false;
        }

        for (int i = 0; i < cntSquareBracket; i++) {
            type += "[]";
        }

        return new ObjectNode(null, type, null);
    }

    @Override
    public Node visitBinaryOperatorObject(Mx_starParser.BinaryOperatorObjectContext ctx) {
        String name = null, type = null, owner = null;
        ObjectNode lser0 = (ObjectNode) visit(ctx.object(0));
        String type0 = lser0.type;

        ObjectNode lser1 = (ObjectNode) visit(ctx.object(1));
        String type1 = lser1.type;

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
                    if (type0 != "null" && PizzaIRVisitor.typeList.getType(type0).isBuiltin()
                            || type1 != "null" && PizzaIRVisitor.typeList.getType(type1).isBuiltin()) {
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
                    if (type0 != "null" && PizzaIRVisitor.typeList.getType(type0).isBuiltin()
                            || type1 != "null" && PizzaIRVisitor.typeList.getType(type1).isBuiltin()) {
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
        }

        String fname = PizzaIRVisitor.typeList.getType(lser0.type).getMethod(method);

        if (fname == null) {
            assert false;
        }

        Func func = PizzaIRVisitor.funcList.getFunc(fname);

        Params params = new Params();
        params.add(lser0.type);
        params.add(lser1.type);
        if (!func.params.match(params)) {
            assert false;
        }

        type = func.rtype;
        return new ObjectNode(name, type, owner);
    }

    static void allocateVariable(String name, String type) {
        if (!dom.canAllocate(name)) {
            assert false;
        }
        counter++;
        Variable variable = new Variable(counter, name, type, dom.getAddr());
        varList.add(variable);
        dom.addVar(variable);
    }
}