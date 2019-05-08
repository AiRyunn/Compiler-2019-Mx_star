package com.github.espylapiza.compiler_mxstar.nasm;

public class OperandString extends Operand {
    private String str;

    public OperandString(String str) {
        str = str.substring(1, str.length() - 1);

        int len = str.length();
        str = String.valueOf(len / 256 / 256 / 256) + ", " + String.valueOf(len / 256 / 256 % 256)
                + ", " + String.valueOf(len / 256 % 256) + ", " + String.valueOf(len % 256) + ", "
                + "`" + str + "`" + ", 0";
    }

    @Override
    public String toString() {
        return str;
    }
}
