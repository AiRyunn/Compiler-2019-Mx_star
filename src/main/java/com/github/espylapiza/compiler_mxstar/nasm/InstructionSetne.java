package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionSetne extends Instruction {
    private static final String name = "setne";
    Operand src;

    public InstructionSetne(Operand src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, src);
    }
}
