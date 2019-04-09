package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;

public class Scope {
    private ScopeType type;
    private String label;
    private List<Inst> insts = new ArrayList<Inst>();

    Scope(ScopeType type, String label) {
        this.type = type;
        this.label = label;
    }

    ScopeType getType() {
        return type;
    }

    String getLabel() {
        return "#" + label;
    }

    void addInstruction(Inst inst) {
        insts.add(inst);
    }

    @Override
    public String toString() {
        String result = "#" + label + "\n";
        for (Inst inst : insts) {
            result += "\t" + inst.toString() + "\n";
        }
        return result;
    }
}
