package com.github.espylapiza.compiler_mxstar.nasm;

public class OperandInt extends Operand {
    private Integer value;

    public OperandInt(Integer value) {
        this.value = value;
    }

    public OperandInt(Boolean value) {
        if (value == true) {
            this.value = 1;
        } else {
            this.value = 0;
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
