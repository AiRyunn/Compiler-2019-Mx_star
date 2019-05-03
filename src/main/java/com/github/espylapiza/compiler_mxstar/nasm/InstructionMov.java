package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionMov extends Instruction {
    private static final String name = "mov";
    Operand src;
    Operand dst;

    public InstructionMov(Operand src, Operand dst) {
        this.src = src;
        this.dst = dst;
    }

    @Override
    public String toString() {
        return name + "\t\t" + src + ", " + dst;
    }
}
