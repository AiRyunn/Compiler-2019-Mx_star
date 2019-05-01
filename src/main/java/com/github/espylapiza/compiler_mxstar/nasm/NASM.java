package com.github.espylapiza.compiler_mxstar.nasm;

public class NASM {
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