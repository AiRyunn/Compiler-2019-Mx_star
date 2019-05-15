package com.github.espylapiza.compiler_mxstar.nasm;

import com.github.espylapiza.compiler_mxstar.pizza_ir.BasicBlock;

public class InstructionJz extends Instruction implements InstructionBaseJump {
    private static final String name = "jz";
    BasicBlock scp;

    public InstructionJz(BasicBlock scp) {
        this.scp = scp;
    }

    @Override
    public String toString() {
        return String.format("%-8s%-8s%s", "", name, scp.getLabel());
    }
}
