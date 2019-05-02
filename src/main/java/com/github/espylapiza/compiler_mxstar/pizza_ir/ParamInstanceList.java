package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;

public class ParamInstanceList extends ProgramFragment {
    private List<Object> params;

    public ParamInstanceList() {
        params = new ArrayList<Object>();
    }

    public ParamInstanceList(Object obj) {
        params = new ArrayList<Object>();
        params.add(obj);
    }

    public void add(Object obj) {
        params.add(obj);
    }

    public List<Object> get() {
        return params;
    }

    public boolean match(ParamList rhs) {
        if (params.size() != rhs.get().size()) {
            return false;
        }
        for (int i = 0; i < params.size(); i++) {
            if (!params.get(i).type.equals(rhs.get().get(i))) {
                return false;
            }
        }
        return true;
    }
}
