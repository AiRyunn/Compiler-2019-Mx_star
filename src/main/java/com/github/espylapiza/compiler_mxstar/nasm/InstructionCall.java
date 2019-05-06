package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionCall extends Instruction {
    private static final String name = "call";

    OperandFuncAddr addr;

    public InstructionCall(OperandFuncAddr addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, addr.toString());
    }
}
