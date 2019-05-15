package com.github.espylapiza.compiler_mxstar.nasm;

import com.github.espylapiza.compiler_mxstar.pizza_ir.BasicBlock;

public class InstructionJmp extends Instruction implements InstructionBaseJump {
    private static final String name = "jmp";
    public BasicBlock scp;

    public InstructionJmp(BasicBlock scp) {
        this.scp = scp;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, scp.getLabel());
    }
}
