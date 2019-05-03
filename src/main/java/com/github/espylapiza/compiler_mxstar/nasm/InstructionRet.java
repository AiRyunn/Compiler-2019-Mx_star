package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionRet extends Instruction {
    private static final String name = "ret";

    public InstructionRet() {
    }

    @Override
    public String toString() {
        return name;
    }
}
