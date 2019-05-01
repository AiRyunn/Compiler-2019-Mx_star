package com.github.espylapiza.compiler_mxstar.nasm;

public abstract class Line {
    private Label label;
    private Instruction instruction;
    private Operands operands;

    Line(Label label, Instruction instruction, Operands operands) {
        this.label = label;
        this.instruction = instruction;
        this.operands = operands;
    }

    @Override
    public String toString() {
        String result = new String();
        if (label != null) {
            result += label.toString();
        }
        result += "\t\t";
        if (instruction != null) {
            result += instruction.toString();
        }
        result += "\t\t";
        if (operands != null) {
            result += operands.toString();
        }
        return result;
    }
}