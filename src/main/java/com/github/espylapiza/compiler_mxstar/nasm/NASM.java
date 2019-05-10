package com.github.espylapiza.compiler_mxstar.nasm;

public class NASM {
    /**
     * An implement of simplified NASM.
     * Each line is of the following three cases:
     *   1. [Macro]
     *   2. [Label]:
     *   3. [Instruction] [Operands]
     */
    private Directives directives = new Directives();
    public SectionText sectionText = new SectionText();
    public SectionData sectionData = new SectionData();
    public SectionBSS sectionBSS = new SectionBSS();

    public NASM() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(directives.toString());
        sb.append("\n");
        sb.append(sectionText.toString());
        sb.append("\n");
        sb.append(sectionData.toString());
        sb.append("\n");
        sb.append(sectionBSS.toString());
        return sb.toString();
    }

    public void addDirective(Directive directive) {
        directives.add(directive);
    }
}
