package com.github.espylapiza.compiler_mxstar.nasm;

public class OperandString extends Operand {
    private String str;

    public OperandString(String str) {
        str = str.substring(1, str.length() - 1);
        str = "`" + str + "`";
        this.str = str + ", 0";
    }

    @Override
    public String toString() {
        return str;
    }
}
