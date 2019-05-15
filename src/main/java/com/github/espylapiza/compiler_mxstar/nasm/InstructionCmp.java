package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionCmp extends Instruction {
    private static final String name = "cmp";
    Operand lhs, rhs;

    public InstructionCmp(Operand lhs, Operand rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, lhs + ", " + rhs);
    }
}
