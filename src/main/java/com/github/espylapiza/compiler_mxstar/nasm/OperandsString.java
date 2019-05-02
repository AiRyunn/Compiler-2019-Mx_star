package com.github.espylapiza.compiler_mxstar.nasm;

public class OperandsString extends Operands {
    private final String str;

    OperandsString(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
