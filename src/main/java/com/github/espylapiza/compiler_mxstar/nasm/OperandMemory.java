package com.github.espylapiza.compiler_mxstar.nasm;

public class OperandMemory extends Operand {
    OperandRegister reg;
    int offset;

    public OperandMemory(OperandRegister reg, int offset) {
        this.reg = reg;
        this.offset = offset;
    }
}
