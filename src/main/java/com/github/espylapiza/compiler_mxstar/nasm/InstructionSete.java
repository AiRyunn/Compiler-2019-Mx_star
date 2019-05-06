package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionSete extends Instruction {
    private static final String name = "sete";
    Operand src;

    public InstructionSete(Operand src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, src);
    }
}
