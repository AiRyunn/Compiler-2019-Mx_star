package com.github.espylapiza.compiler_mxstar.nasm;

public class OperandDBAddr extends Operand {
    private final String addr;

    public OperandDBAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return addr;
    }
}
