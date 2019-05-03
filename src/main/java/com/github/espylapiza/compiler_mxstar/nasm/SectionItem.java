package com.github.espylapiza.compiler_mxstar.nasm;

public class SectionItem {
    Label label;
    Instruction instruction;
    //Operands operands;

    public SectionItem(Label label, Instruction instruction/*, Operands operands*/) {
        this.label = label;
        this.instruction = instruction;
        //this.operands = operands;
    }

    @Override
    public String toString() {
        String result = new String();
        if (label != null) {
            result += label.toString() + ":";
        }
        if (instruction != null) {
            result += "\t\t";
            result += instruction.toString();
        }
        result += "\n";
        return result;
    }
}
