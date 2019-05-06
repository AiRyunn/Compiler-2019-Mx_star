package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionSetl extends Instruction {
    private static final String name = "setl";
    Operand src;

    public InstructionSetl(Operand src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, src);
    }
}
