package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionMovzx extends Instruction {
    private static final String name = "movzx";
    Operand dst;
    Operand src;

    public InstructionMovzx(Operand dst, Operand src) {
        this.dst = dst;
        this.src = src;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s, %s", "", name, dst, src);
    }
}
