package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionIdiv extends Instruction {
    private static final String name = "idiv";
    Operand src;
    int imm;

    public InstructionIdiv(int imm) {
        this.imm = imm;
    }

    public InstructionIdiv(Operand src) {
        this.src = src;
    }

    @Override
    public String toString() {
        if (src == null) {
            return String.format("%-8s%-8s%s", "", name, imm);
        } else {
            return String.format("%-8s%-8s%s", "", name, src);
        }
    }
}
