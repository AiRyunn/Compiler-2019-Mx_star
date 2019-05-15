package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionImul extends Instruction {
    private static final String name = "imul";
    Operand src;
    Integer imm;

    public InstructionImul(Operand dst, int imm) {
        this.dst = dst;
        this.imm = imm;
    }

    public InstructionImul(Operand dst, Operand src) {
        this.dst = dst;
        this.src = src;
    }

    @Override
    public String toString() {
        if (src != null) {
            return String.format("%-8s%-8s%s", "", name, dst + ", " + src);
        } else if (imm != null) {
            return String.format("%-8s%-8s%s", "", name, dst + ", " + imm);
        }
        return null;
    }
}
