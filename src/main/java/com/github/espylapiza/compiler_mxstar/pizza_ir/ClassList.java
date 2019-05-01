package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.HashMap;
import java.util.Map;

public class ClassList {
    private Map<String, Class> classList;

    ClassList() {
        classList = new HashMap<String, Class>();
    }

    public void add(Class class1) {
        classList.put(class1.getName(), class1);
    }

    public Class get(String name) {
        return classList.get(name);
    }
}