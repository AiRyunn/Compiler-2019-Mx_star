package com.github.espylapiza.compiler_mxstar.nasm;

public class OperandMem extends Operand {
    OperandRegister reg;
    int offset;
    String varName;

    public OperandMem(OperandRegister reg, int offset) {
        this.reg = reg;
        this.offset = offset;
    }

    public OperandMem(String varName, int offset) {
        this.varName = varName;
        this.offset = offset;
    }

    @Override
    public String toString() {
        String name;
        if (reg != null) {
            name = reg.name;
        } else {
            name = "rel " + varName;
        }
        if (offset > 0) {
            return "qword [ " + name + " + " + String.valueOf(offset) + " ]";
        } else if (offset < 0) {
            return "qword [ " + name + " - " + String.valueOf(-offset) + " ]";
        } else {
            return "qword [ " + name + " ]";
        }
    }
}
