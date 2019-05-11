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

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(java.lang.Object rhs) {
        if (rhs instanceof OperandInt) {
            return value.equals(((OperandInt) rhs).value);
        }
        return false;
    }
}
