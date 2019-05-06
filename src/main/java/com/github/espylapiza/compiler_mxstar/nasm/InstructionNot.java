package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionNot extends Instruction {
    private static final String name = "not";
    Operand src;

    public InstructionNot(Operand src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, src);
    }
}
