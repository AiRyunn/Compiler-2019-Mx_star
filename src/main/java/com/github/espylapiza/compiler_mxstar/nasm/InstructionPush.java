package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionPush extends Instruction {
    private static final String name = "push";
    Operand src;

    public InstructionPush(Operand src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return String.format("%-30s%s", name, src);
    }
}
