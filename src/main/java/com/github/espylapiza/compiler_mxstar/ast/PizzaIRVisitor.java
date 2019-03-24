package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.Mx_starBaseVisitor;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser.*;
import com.github.espylapiza.compiler_mxstar.logging.*;
import com.github.espylapiza.compiler_mxstar.utils.Pair;
import com.google.gson.JsonObject;

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
        VariableDeclarationListener lser = new VariableDeclarationListener();
        variableDeclaration.enterRule(lser);

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
        VariableDefinitionListener lser = new VariableDefinitionListener();
        variableDefinition.enterRule(lser);

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

        // FunctionDefinitionStatementListener lser = new
        // FunctionDefinitionStatementListener();
        // ctx.functionDefinitionStatement().enterRule(lser);
        visit(ctx.functionDefinitionStatement());
        return null;
    }

    @Override
    public Node visitClassVariableDeclarationStatement(Mx_starParser.ClassVariableDeclarationStatementContext ctx) {
        VariableDeclarationListener lser = new VariableDeclarationListener();
        ctx.variableDeclarationStatement().variableDeclaration().enterRule(lser);
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
        Function lser = (Function) visit(ctx.constructionFunctionStatement());
        // ConstructionFunctionStatementListener lser = new
        // ConstructionFunctionStatementListener();
        // ctx.constructionFunctionStatement().enterRule(lser);

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
        Function lser = (Function) visit(ctx.functionDefinitionStatement());
        // FunctionDefinitionStatementListener lser = new
        // FunctionDefinitionStatementListener();
        // ctx.functionDefinitionStatement().enterRule(lser);

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

        ParamListDefinitionListener lser = new ParamListDefinitionListener();
        ctx.paramListDefinition().enterRule(lser);

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
                StatementsListener stmtLser = new StatementsListener();
                ctx.statements().enterRule(stmtLser);
            }

            code.packScope();
            code.packSection();
        }

        Logging.debug("exit construction function: " + name);
        dom.exitFunc();
        return new Function(name, "");
    }

    @Override
    public Node visitFunctionDefinitionStatement(Mx_starParser.FunctionDefinitionStatementContext ctx) {
        String name = ctx.Identifier().getText();
        String rtype = ctx.type().getText();
        Function result = new Function(name, rtype);
        String trace = PizzaIRVisitor.dom.getClassTrace();

        if (!rtype.equals("void") && !PizzaIRVisitor.typeList.hasType(rtype)) {
            assert false;
        }
        Logging.debug("enter function: " + name);

        PizzaIRVisitor.dom.enterFunc(trace, name, rtype);

        ParamListDefinitionListener lser = new ParamListDefinitionListener();
        ctx.paramListDefinition().enterRule(lser);

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
                StatementsListener stmtLser = new StatementsListener();
                ctx.statements().enterRule(stmtLser);
            }

            PizzaIRVisitor.code.packScope();
            PizzaIRVisitor.code.packSection();
        }

        Logging.debug("exit function: " + name);
        PizzaIRVisitor.dom.exitFunc();
        return null;
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