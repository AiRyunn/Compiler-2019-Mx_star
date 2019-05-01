package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionMov extends Instruction {
    private String name = "mov";

    @Override
    public String toString() {
        return name;
    }
}