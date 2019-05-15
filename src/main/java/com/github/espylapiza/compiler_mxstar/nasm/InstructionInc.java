package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionInc extends Instruction {
    private static final String name = "inc";

    public InstructionInc(Operand dst) {
        this.dst = dst;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, dst);
    }
}
