package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionMov extends Instruction {
    private static final String name = "mov";
    Operand dst;
    Operand src;

    public InstructionMov(Operand dst, Operand src) {
        this.dst = dst;
        this.src = src;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s, %s", "", name, dst, src);
    }
}
