package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionRet extends Instruction implements InstructionBaseJump {
    private static final String name = "ret";

    public InstructionRet() {
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s", "", name);
    }
}
