package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionCqo extends Instruction {
    private static final String name = "cqo";

    @Override
    public String toString() {
        return String.format("%-8s%-8s", "", name);
    }
}
