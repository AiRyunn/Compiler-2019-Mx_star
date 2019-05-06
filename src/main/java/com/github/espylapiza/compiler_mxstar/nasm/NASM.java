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

    public NASM() {
    }

    @Override
    public String toString() {
        String result = new String();
        result += directives.toString();
        result += "\n";
        result += sectionText.toString();
        result += "\n";
        result += sectionData.toString();
        return result;
    }

    public void addDirective(Directive directive) {
        // FIXME
        directives.add(directive);
    }
}
