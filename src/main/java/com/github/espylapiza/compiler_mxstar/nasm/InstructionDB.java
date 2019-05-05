package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionDB extends Instruction {
    private static final String name = "db";

    OperandString src;

    public InstructionDB(OperandString src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return String.format("%-30s%s", name, src.toString());
    }
}
