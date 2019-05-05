package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionAdd extends Instruction {
    private static final String name = "add";
    Operand dst, src;
    int imm;

    public InstructionAdd(Operand dst, int imm) {
        this.dst = dst;
        this.imm = imm;
    }

    public InstructionAdd(Operand dst, Operand src) {
        this.dst = dst;
        this.src = src;
    }

    @Override
    public String toString() {
        if (src == null) {
            return String.format("%-30s%s", name, dst + ", " + imm);
        } else {
            return String.format("%-30s%s", name, dst + ", " + src);
        }
    }
}
