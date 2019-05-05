package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionPop extends Instruction {
    private static final String name = "pop";
    Operand src;

    public InstructionPop(Operand src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return String.format("%-30s%s", name, src.toString());
    }
}
