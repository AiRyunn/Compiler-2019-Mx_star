package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class ParamList extends ProgramFragment {
    @Expose
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

    public boolean match(ParamList rhs) {
        if (params.size() != rhs.params.size()) {
            return false;
        }
        for (int i = 0; i < params.size(); i++) {
            if (!params.get(i).equals(rhs.params.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int count() {
        return params.size();
    }
}