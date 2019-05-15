package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionXor extends Instruction {
    private static final String name = "xor";
    Operand src;
    int imm;

    public InstructionXor(Operand dst, int imm) {
        this.dst = dst;
        this.imm = imm;
    }

    public InstructionXor(Operand dst, Operand src) {
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
