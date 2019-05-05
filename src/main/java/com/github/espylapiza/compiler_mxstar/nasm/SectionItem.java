package com.github.espylapiza.compiler_mxstar.nasm;

public class SectionItem {
    Label label;
    Instruction instruction;

    public SectionItem(Label label, Instruction instruction) {
        this.label = label;
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        String strLabel = "", strInstruction = "";
        if (label != null) {
            strLabel = label.toString() + ":";
        }
        if (instruction != null) {
            strInstruction = instruction.toString();
        }
        return String.format("%-30s%s\n", strLabel, strInstruction);
    }
}
