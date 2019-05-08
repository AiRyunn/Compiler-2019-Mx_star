package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionResq extends Instruction {
    private static final String name = "resq";
    private final String varName;

    public InstructionResq(String varName) {
        this.varName = varName;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%d", varName + ":", name, 1);
    }
}
