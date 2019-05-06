package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionSetg extends Instruction {
    private static final String name = "setg";
    Operand src;

    public InstructionSetg(Operand src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, src);
    }
}
