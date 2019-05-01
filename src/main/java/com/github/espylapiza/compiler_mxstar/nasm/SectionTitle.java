package com.github.espylapiza.compiler_mxstar.nasm;

public class SectionTitle extends Line {
    SectionTitle(String strTitle) {
        super(null, new InstructionSection(), new OperandsString(strTitle));
    }
}