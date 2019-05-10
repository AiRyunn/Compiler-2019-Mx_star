package com.github.espylapiza.compiler_mxstar.nasm;

import com.github.espylapiza.compiler_mxstar.pizza_ir.Scope;

public class InstructionJmp extends Instruction {
    private static final String name = "jmp";
    public Scope scp;

    public InstructionJmp(Scope scp) {
        this.scp = scp;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, scp.getLabel());
    }
}
