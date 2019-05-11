package com.github.espylapiza.compiler_mxstar.nasm;

public abstract class OperandRegister extends Operand {
    String name;

    OperandRegister(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public final boolean equals(java.lang.Object rhs) {
        if (rhs instanceof OperandRegister) {
            return getClass() == rhs.getClass() && name.equals(((OperandRegister) rhs).name);
        }
        return false;
    }
}
