package com.github.espylapiza.compiler_mxstar.nasm;

public class OperandMem extends Operand {
    OperandRegister reg;
    int offset;
    String varName;
    int width = 8;

    public OperandMem(OperandRegister reg, int offset) {
        this.reg = reg;
        this.offset = offset;
    }

    public OperandMem(OperandRegister reg, int offset, int width) {
        this.reg = reg;
        this.offset = offset;
        this.width = width;
    }

    public OperandMem(String varName, int offset) {
        this.varName = varName;
        this.offset = offset;
    }

    @Override
    public String toString() {
        String name;
        String size = null;
        if (width == 8) {
            size = "qword";
        } else if (width == 4) {
            size = "dword";
        } else {
            assert false;
        }
        if (reg != null) {
            name = reg.name;
        } else {
            name = "rel " + varName;
        }
        if (offset > 0) {
            return size + " [ " + name + " + " + String.valueOf(offset) + " ]";
        } else if (offset < 0) {
            return size + " [ " + name + " - " + String.valueOf(-offset) + " ]";
        } else {
            return size + " [ " + name + " ]";
        }
    }
}
