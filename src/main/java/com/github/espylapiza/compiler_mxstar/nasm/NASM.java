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
    private Sections sections = new Sections();

    public NASM() {
    }

    @Override
    public String toString() {
        String result = new String();
        result += directives.toString();
        result += "\n";
        result += sections.toString();
        return result;
    }

    public void addDirective(DirectiveGlobal directiveGlobal) {
        directives.add(directiveGlobal);
    }

    public void addSection(Section section) {
        sections.add(section);
    }
}