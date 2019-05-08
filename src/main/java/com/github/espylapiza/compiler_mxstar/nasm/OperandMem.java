package com.github.espylapiza.compiler_mxstar.nasm;

public class OperandMem extends Operand {
    OperandRegister reg;
    int offset;

    public OperandMem(OperandRegister reg, int offset) {
        this.reg = reg;
        this.offset = offset;
    }

    @Override
    public String toString() {
        if (offset > 0) {
            return "qword [ " + reg.name + " + " + String.valueOf(offset) + " ]";
        } else if (offset < 0) {
            return "qword [ " + reg.name + " - " + String.valueOf(-offset) + " ]";
        } else {
            return "qword [ " + reg.name + " ]";
        }
    }
}
