package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionDec extends Instruction {
    private static final String name = "inc";
    Operand src;

    public InstructionDec(Operand src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, src);
    }
}
