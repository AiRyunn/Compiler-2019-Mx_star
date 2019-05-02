package com.github.espylapiza.compiler_mxstar.nasm;

public class DirectiveExtern extends Directive {
    private final String name;

    public DirectiveExtern(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "extern " + name;
    }
}
