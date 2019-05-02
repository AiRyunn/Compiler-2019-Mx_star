package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;

public class VarList {
    private List<Object> varList = new ArrayList<Object>();

    public void add(Object variable) {
        varList.add(variable);
    }

    public int count() {
        return varList.size();
    }

    Object get(int index) {
        return varList.get(index);
    }
}
