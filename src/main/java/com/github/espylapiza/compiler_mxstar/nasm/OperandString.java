package com.github.espylapiza.compiler_mxstar.nasm;

public class OperandString extends Operand {
    private String str;

    public OperandString(String str) {
        str = str.substring(1, str.length() - 1);

        String str_save = str.replace("`", "\\`");
        String str_u = str.replace("\\\\", "\\").replace("\\n", "\n").replace("\\\"", "\"").replace("\\t", "\t");

        int len = str_u.length();

        this.str = String.valueOf(len % 256) + ", " + String.valueOf(len / 256 % 256) + ", "
                + String.valueOf(len / 256 / 256 % 256) + ", " + String.valueOf(len / 256 / 256 / 256) + ", " + "`"
                + str_save + "`" + ", 0";
    }

    @Override
    public String toString() {
        return str;
    }
}
