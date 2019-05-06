package com.github.espylapiza.compiler_mxstar.nasm;

public class InstructionShl extends Instruction {
    private static final String name = "shl";
    Operand dst, cl;
    int imm8;

    public InstructionShl(Operand dst, int imm8) {
        this.dst = dst;
        this.imm8 = imm8;
    }

    public InstructionShl(Operand dst, OperandRegister8Bit cl) {
        // assert cl
        this.dst = dst;
        this.cl = cl;
    }

    @Override
    public String toString() {
        if (cl == null) {
            return String.format("%-8s%-8s%s", "", name, dst + ", " + imm8);
        } else {
            return String.format("%-8s%-8s%s", "", name, dst + ", " + cl);
        }
    }
}
