package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionOr extends Instruction {
    private static final String name = "or";
    Operand src;
    int imm;

    public InstructionOr(Operand dst, int imm) {
        this.dst = dst;
        this.imm = imm;
    }

    public InstructionOr(Operand dst, Operand src) {
        this.dst = dst;
        this.src = src;
    }

    @Override
    public String toString() {
        if (src == null) {
            return String.format("%-8s%-8s%s", "", name, dst + ", " + imm);
        } else {
            return String.format("%-8s%-8s%s", "", name, dst + ", " + src);
        }
    }
}
