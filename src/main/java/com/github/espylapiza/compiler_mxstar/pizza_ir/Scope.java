package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Scope implements Iterable<Inst> {
    private ScopeType type;
    private String label;
    private List<Inst> insts = new ArrayList<Inst>();

    public Scope(ScopeType type, String label) {
        this.type = type;
        this.label = label;
    }

    public ScopeType getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public void addInstruction(Inst inst) {
        insts.add(inst);
    }

    @Override
    public String toString() {
        String result = label + ":\n";
        for (Inst inst : insts) {
            result += "\t" + inst.toString() + "\n";
        }
        return result;
    }

    @Override
    public Iterator<Inst> iterator() {
        return insts.iterator();
    }
}
