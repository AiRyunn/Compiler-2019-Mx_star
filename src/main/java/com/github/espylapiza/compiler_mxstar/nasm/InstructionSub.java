package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionSub extends Instruction {
    private static final String name = "sub";
    Operand dst;
    int imm;

    public InstructionSub(Operand dst, int imm) {
        this.dst = dst;
        this.imm = imm;
    }

    @Override
    public String toString() {
        return name + "\t\t" + dst + ", " + imm;
    }
}
