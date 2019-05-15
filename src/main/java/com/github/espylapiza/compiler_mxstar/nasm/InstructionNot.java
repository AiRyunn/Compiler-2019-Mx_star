package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionNot extends Instruction {
    private static final String name = "not";

    public InstructionNot(Operand src) {
        this.dst = src;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, dst);
    }
}
