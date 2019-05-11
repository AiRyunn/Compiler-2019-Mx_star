package com.github.espylapiza.compiler_mxstar.nasm;

public class OperandFuncAddr extends Operand {
    private final String addr;

    public OperandFuncAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return addr;
    }

    @Override
    public boolean equals(java.lang.Object rhs) {
        if (rhs instanceof OperandFuncAddr) {
            return addr.equals(((OperandFuncAddr) rhs).addr);
        }
        return false;
    }
}
