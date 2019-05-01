package com.github.espylapiza.compiler_mxstar.nasm;

public abstract class Section {
    private String title;

    Section(String title) {
        this.title = title;
    }

    protected String getDeclaration() {
        return "section " + title + "\n";
    }
}