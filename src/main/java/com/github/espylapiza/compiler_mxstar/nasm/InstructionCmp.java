package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionCmp extends Instruction {
    private static final String name = "cmp";
    Operand dst, src;

    public InstructionCmp(Operand dst, Operand src) {
        this.dst = dst;
        this.src = src;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, dst + ", " + src);
    }
}
