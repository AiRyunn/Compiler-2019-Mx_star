package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;

public class ParamList extends ProgramFragment {
    private List<Type> params;

    public ParamList() {
        params = new ArrayList<Type>();
    }

    public ParamList(Type type) {
        params = new ArrayList<Type>();
        params.add(type);
    }

    public ParamList(List<Type> types) {
        params = types;
    }

    public void add(Type type) {
        params.add(type);
    }

    List<Type> get() {
        return params;
    }

    public int count() {
        return params.size();
    }
}
