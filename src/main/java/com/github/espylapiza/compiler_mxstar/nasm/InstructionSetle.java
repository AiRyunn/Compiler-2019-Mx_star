package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionSetle extends Instruction {
    private static final String name = "setle";
    Operand src;

    public InstructionSetle(Operand src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, src);
    }
}
