package com.github.espylapiza.compiler_mxstar.nasm;

public class SectionItem {
    Label label;
    Instruction instruction;
    Operands operands;

    public SectionItem(Label label, Instruction instruction, Operands operands) {
        this.label = label;
        this.instruction = instruction;
        this.operands = operands;
    }
}
