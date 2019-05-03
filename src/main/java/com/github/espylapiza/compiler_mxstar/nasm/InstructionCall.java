package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionCall extends Instruction {
    private static final String name = "call";

    OperandFuncAddr addr;

    public InstructionCall(OperandFuncAddr addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return name + "\t\t" + addr.toString();
    }
}
