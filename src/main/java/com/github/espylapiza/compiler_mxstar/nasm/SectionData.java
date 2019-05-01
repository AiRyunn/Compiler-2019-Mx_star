package com.github.espylapiza.compiler_mxstar.nasm;

public class SectionData extends Section {
    public SectionData() {
        super(".data");
    }

    @Override
    public String toString() {
        String result = super.getDeclaration();
        return result;
    }
}