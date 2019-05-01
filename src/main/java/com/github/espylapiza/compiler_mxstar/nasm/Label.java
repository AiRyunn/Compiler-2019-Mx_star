package com.github.espylapiza.compiler_mxstar.nasm;

class Label extends SectionTextItem {
    private final String name;

    Label(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}