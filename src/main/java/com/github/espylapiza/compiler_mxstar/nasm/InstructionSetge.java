package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionSetge extends Instruction {
    private static final String name = "setge";
    Operand src;

    public InstructionSetge(Operand src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, src);
    }
}
