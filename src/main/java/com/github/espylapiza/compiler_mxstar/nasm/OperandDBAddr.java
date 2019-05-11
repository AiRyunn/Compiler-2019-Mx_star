package com.github.espylapiza.compiler_mxstar.nasm;

public class OperandDBAddr extends Operand {
    private final String addr;

    public OperandDBAddr(String addr) {
        this.addr = addr;
    }

    public OperandDBAddr(Label label) {
        this.addr = label.getName();
    }

    @Override
    public String toString() {
        return addr;
    }

    @Override
    public boolean equals(java.lang.Object rhs) {
        if (rhs instanceof OperandDBAddr) {
            return addr.equals(((OperandDBAddr) rhs).addr);
        }
        return false;
    }
}
