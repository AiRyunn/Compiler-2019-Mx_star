package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionTest extends Instruction {
    private static final String name = "test";
    Operand op1, op2;

    public InstructionTest(Operand op1, Operand op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, op1 + ", " + op2);
    }
}
