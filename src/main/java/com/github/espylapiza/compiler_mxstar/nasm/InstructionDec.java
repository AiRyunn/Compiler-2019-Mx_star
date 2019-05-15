package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionDec extends Instruction {
    private static final String name = "dec";

    public InstructionDec(Operand dst) {
        this.dst = dst;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, dst);
    }
}
