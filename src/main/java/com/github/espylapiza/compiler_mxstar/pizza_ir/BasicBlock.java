package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BasicBlock implements Iterable<Inst> {
    private BlockType type;
    private String label;
    private List<Inst> insts = new ArrayList<Inst>();

    public BasicBlock(BlockType type, String label) {
        this.type = type;
        this.label = label;
    }

    /**
     * @return the insts
     */
    public List<Inst> getInsts() {
        return insts;
    }

    /**
     * @param insts the insts to set
     */
    public void setInsts(List<Inst> insts) {
        this.insts = insts;
    }

    public BlockType getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public void addInstruction(Inst inst) {
        insts.add(inst);
    }

    public Inst lastInstruction() {
        return insts.get(insts.size() - 1);
    }

    @Override
    public String toString() {
        String result = label + ":\n";
        for (Inst inst : getInsts()) {
            result += "\t" + inst.toString() + "\n";
        }
        return result;
    }

    @Override
    public Iterator<Inst> iterator() {
        return getInsts().iterator();
    }

}
