package com.github.espylapiza.compiler_mxstar.ast;

import com.github.espylapiza.compiler_mxstar.parser.*;

import org.antlr.v4.runtime.tree.ParseTree;

import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser.ConstantContext;

public class ObjectListener extends Mx_starBaseListener {
    String type;
    String name;
    String owner;

    @Override
    public void enterThisObject(Mx_starParser.ThisObjectContext ctx) {
        if (PizzaIRBuilder.dom.isGlobal()) {
            assert false;
        }
        type = PizzaIRBuilder.dom.getClassTrace();
    }

    @Override
    public void enterIdentifierObject(Mx_starParser.IdentifierObjectContext ctx) {
        name = ctx.Identifier().getText();

        Variable variable = PizzaIRBuilder.dom.getVar(name);

        String owner = "";
        if (variable != null && variable.owner != null) {
            owner = variable.owner;
        }

        if (variable != null && owner.equals(PizzaIRBuilder.dom.getAddr())) {
            // local variable
            type = variable.type;
            return;
        }
        if (!PizzaIRBuilder.dom.isGlobal()) {
            String trace = PizzaIRBuilder.dom.getClassTrace();
            if (PizzaIRBuilder.funcList.getFunc(trace + "." + name) != null) {
                // method
                this.owner = trace;
                type = "__method__";
                return;
            }
        }

        if (variable != null) {
            // global variable
            type = variable.type;
            return;
        }

        Func func = PizzaIRBuilder.funcList.getFunc(name);
        if (func != null) {
            // global function
            type = "__func__";
            return;
        }
        assert false;
    }

    @Override
    public void enterConstantObject(Mx_starParser.ConstantObjectContext ctx) {
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
    }

    @Override
    public void enterLvalueObject(Mx_starParser.LvalueObjectContext ctx) {
        LvalueListener lser = new LvalueListener();
        ctx.lvalue().enterRule(lser);
        type = lser.type;
    }

    @Override
    public void enterMemberObject(Mx_starParser.MemberObjectContext ctx) {
        String member = ctx.Identifier().getText();

        // if (ctx.This() != null) {
        // if (PizzaIRBuilder.dom.isGlobal()) {
        // assert false;
        // }
        // identifier_typename = PizzaIRBuilder.dom.getClassTrace();
        // }

        ObjectListener lser = new ObjectListener();
        ctx.object().enterRule(lser);

        Type identifier_type = PizzaIRBuilder.typeList.getType(lser.type);

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
    }

    @Override
    public void enterBracketObject(Mx_starParser.BracketObjectContext ctx) {
        ObjectListener lser = new ObjectListener();
        ctx.object().enterRule(lser);
        type = lser.type;
    }

    @Override
    public void enterFunctionReturnObject(Mx_starParser.FunctionReturnObjectContext ctx) {
        ObjectListener lser = new ObjectListener();
        ctx.object().enterRule(lser);

        if (!lser.type.equals("__func__") && !lser.type.equals("__method__")) {
            assert (false);
        }

        String addr = lser.name;
        if (lser.owner != null) {
            addr = lser.owner + "." + addr;
        }

        Func func = PizzaIRBuilder.funcList.getFunc(addr);

        ParamListListener paramLser = new ParamListListener();
        ctx.paramList().enterRule(paramLser);

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

        type = func.rtype;
    }

    @Override
    public void enterSubscriptObject(Mx_starParser.SubscriptObjectContext ctx) {
        ObjectListener obj = new ObjectListener();
        ctx.object(0).enterRule(obj);

        type = obj.type;
        if (!type.endsWith("[]")) {
            assert (false);
        }

        ObjectListener sub = new ObjectListener();
        ctx.object(1).enterRule(sub);
        if (!sub.type.equals("int")) {
            assert (false);
        }

        type = type.substring(0, type.length() - 2);
    }

    @Override
    public void enterUnaryOperatorObject(Mx_starParser.UnaryOperatorObjectContext ctx) {
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
            LvalueListener lser = new LvalueListener();
            ctx.lvalue().enterRule(lser);
            objType = lser.type;
        } else {
            ObjectListener lser = new ObjectListener();
            ctx.object().enterRule(lser);
            objType = lser.type;
        }

        String fname = PizzaIRBuilder.typeList.getType(objType).getMethod(method);

        if (fname == null) {
            assert false;
        }

        Func func = PizzaIRBuilder.funcList.getFunc(fname);

        Params params = new Params();
        params.add(objType);
        if (!func.params.match(params)) {
            assert false;
        }

        type = func.rtype;
    }

    @Override
    public void enterNewObject(Mx_starParser.NewObjectContext ctx) {
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
                ObjectListener lser = new ObjectListener();
                ((Mx_starParser.ObjectContext) ch).enterRule(lser);
                if (!lser.type.equals("int")) {
                    assert false;
                }
            }
        }
        type = ctx.type().getText();

        if (!PizzaIRBuilder.typeList.hasType(type)) {
            assert false;
        }

        for (int i = 0; i < cntSquareBracket; i++) {
            type += "[]";
        }
    }

    @Override
    public void enterBinaryOperatorObject(Mx_starParser.BinaryOperatorObjectContext ctx) {
        ObjectListener lser0 = new ObjectListener();
        ctx.object(0).enterRule(lser0);
        String type0 = lser0.type;

        ObjectListener lser1 = new ObjectListener();
        ctx.object(1).enterRule(lser1);
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
                    if (type0 != "null" && PizzaIRBuilder.typeList.getType(type0).isBuiltin()
                            || type1 != "null" && PizzaIRBuilder.typeList.getType(type1).isBuiltin()) {
                        assert false;
                    }
                }
                type = "bool";
                return;
            }
            break;
        case "!=":
            method = "__ne__";
            if (type0.equals("null") || type1.equals("null")) {
                if (!type0.endsWith("[]") && !type1.endsWith("[]")) {
                    if (type0 != "null" && PizzaIRBuilder.typeList.getType(type0).isBuiltin()
                            || type1 != "null" && PizzaIRBuilder.typeList.getType(type1).isBuiltin()) {
                        assert false;
                    }
                }
                type = "bool";
                return;
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

        String fname = PizzaIRBuilder.typeList.getType(lser0.type).getMethod(method);

        if (fname == null) {
            assert false;
        }

        Func func = PizzaIRBuilder.funcList.getFunc(fname);

        Params params = new Params();
        params.add(lser0.type);
        params.add(lser1.type);
        if (!func.params.match(params)) {
            assert false;
        }

        type = func.rtype;
    }
}