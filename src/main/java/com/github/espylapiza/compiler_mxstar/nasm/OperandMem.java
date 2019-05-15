package com.github.espylapiza.compiler_mxstar.nasm;

public class OperandMem extends Operand {
    OperandRegister reg;
    public Integer offset;
    String varName;
    int bits = 64;

    public OperandMem(OperandRegister reg, Integer offset) {
        this.reg = reg;
        this.offset = offset;
    }

    public OperandMem(OperandRegister reg, Integer offset, int bits) {
        this.reg = reg;
        this.offset = offset;
        this.bits = bits;
    }

    public OperandMem(String varName, Integer offset) {
        this.varName = varName;
        this.offset = offset;
    }

    @Override
    public String toString() {
        String name;
        String size = null;
        if (bits == 64) {
            size = "qword";
        } else if (bits == 32) {
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

    @Override
    public boolean equals(java.lang.Object rhs) {
        if (rhs instanceof OperandMem) {
            if (varName == null) {
                return reg == ((OperandMem) rhs).reg && offset == ((OperandMem) rhs).offset
                        && ((OperandMem) rhs).varName == null;
            }
            return reg == ((OperandMem) rhs).reg && offset == ((OperandMem) rhs).offset
                    && varName.equals(((OperandMem) rhs).varName);
        }
        return false;
    }
}
