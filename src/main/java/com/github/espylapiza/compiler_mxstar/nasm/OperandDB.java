package com.github.espylapiza.compiler_mxstar.nasm;

public class OperandDB extends Operand {
    private final String str;

    public OperandDB(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
