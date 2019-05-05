package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.Arrays;

public class PizzaIR implements PizzaIRPart {
    public TypeTable typeTable = new TypeTable();
    public ClassList classList = new ClassList();
    public FuncList funcList = new FuncList();

    @Override
    public void accept(PizzaIRPartBaseVisitor visitor) {
        visitor.visit(this);
    }

    public PizzaIR() {
        registerBuiltinClass();
    }

    private void registerBuiltinClass() {
        TypeNull t_null = new TypeNull("null");
        typeTable.add(t_null);

        Class c_void = new Class("void");
        TypeVoid t_void = new TypeVoid("void");
        classList.add(c_void);
        typeTable.add(t_void);

        Class c_bool = new Class("bool");
        TypeBool t_bool = new TypeBool("bool", c_bool);
        Arrays.asList("__lgcnot__").forEach(method -> {
            FuncBuiltin func = new FuncBuiltin(FuncAddr.createMethodAddr(c_bool, method), method,
                    t_bool, new ParamList());
            c_bool.addMethod(func);
            funcList.addFunc(func);
        });
        Arrays.asList("__lgcand__", "__lgcor__", "__eq__", "__ne__").forEach(method -> {
            FuncBuiltin func = new FuncBuiltin(FuncAddr.createMethodAddr(c_bool, method), method,
                    t_bool, new ParamList(new Object(null, "rhs", t_bool)));
            c_bool.addMethod(func);
            funcList.addFunc(func);
        });
        classList.add(c_bool);
        typeTable.add(t_bool);

        Class c_int = new Class("int");
        TypeInt t_int = new TypeInt("int", c_int);
        Arrays.asList("__pos__", "__neg__", "__bitinv__", "__preinc__", "__predec__", "__postinc__",
                "__postdec__").forEach(method -> {
                    FuncBuiltin func = new FuncBuiltin(FuncAddr.createMethodAddr(c_int, method),
                            method, t_int, new ParamList());
                    c_int.addMethod(func);
                    funcList.addFunc(func);
                });
        Arrays.asList("__add__", "__sub__", "__mul__", "__div__", "__mod__", "__shl__", "__shr__",
                "__bitand__", "__bitxor__", "__bitor__").forEach(method -> {
                    FuncBuiltin func = new FuncBuiltin(FuncAddr.createMethodAddr(c_int, method),
                            method, t_int, new ParamList(new Object(null, "rhs", t_int)));
                    c_int.addMethod(func);
                    funcList.addFunc(func);
                });
        Arrays.asList("__lt__", "__gt__", "__le__", "__ge__", "__eq__", "__ne__")
                .forEach(method -> {
                    FuncBuiltin func = new FuncBuiltin(FuncAddr.createMethodAddr(c_int, method),
                            method, t_bool, new ParamList(new Object(null, "rhs", t_int)));
                    c_int.addMethod(func);
                    funcList.addFunc(func);
                });
        classList.add(c_int);
        typeTable.add(t_int);

        Class c_string = new Class("string");
        TypeString t_string = new TypeString("string", c_string);
        Arrays.asList("__add__").forEach(method -> {
            FuncBuiltin func = new FuncBuiltin(FuncAddr.createMethodAddr(c_string, method), method,
                    t_string, new ParamList(new Object(null, "rhs", t_string)));
            c_string.addMethod(func);
            funcList.addFunc(func);
        });
        Arrays.asList("__lt__", "__gt__", "__le__", "__ge__", "__eq__", "__ne__")
                .forEach(method -> {
                    FuncBuiltin func = new FuncBuiltin(FuncAddr.createMethodAddr(c_string, method),
                            method, t_bool, new ParamList(new Object(null, "rhs", t_string)));
                    c_string.addMethod(func);
                    funcList.addFunc(func);
                });
        Arrays.asList("length", "parseInt").forEach(method -> {
            FuncBuiltin func = new FuncBuiltin(FuncAddr.createMethodAddr(c_string, method), method,
                    t_int, new ParamList());
            c_string.addMethod(func);
            funcList.addFunc(func);
        });
        Arrays.asList("ord").forEach(method -> {
            FuncBuiltin func = new FuncBuiltin(FuncAddr.createMethodAddr(c_string, method), method,
                    t_int, new ParamList(new Object(null, "rhs", t_int)));
            c_string.addMethod(func);
            funcList.addFunc(func);
        });
        Arrays.asList("substring").forEach(method -> {
            FuncBuiltin func = new FuncBuiltin(FuncAddr.createMethodAddr(c_string, method), method,
                    t_string,
                    new ParamList(new Object(null, "rhs", t_int), new Object(null, "rhs", t_int)));
            c_string.addMethod(func);
            funcList.addFunc(func);
        });
        classList.add(c_string);
        typeTable.add(t_string);

        Class c_array = new Class("__array__");
        Arrays.asList("size").forEach(method -> {
            FuncBuiltin func = new FuncBuiltin(FuncAddr.createMethodAddr(c_array, method), method,
                    t_int, new ParamList());
            c_array.addMethod(func);
            funcList.addFunc(func);
        });
        classList.add(c_array);

        Class c_func = new Class("__func__");
        TypeFunc t_func = new TypeFunc("__func__", c_void);
        classList.add(c_func);
        typeTable.add(t_func);

        Class c_method = new Class("__method__");
        TypeMethod t_method = new TypeMethod("__method__", c_void);
        classList.add(c_method);
        typeTable.add(t_method);
    }


}
