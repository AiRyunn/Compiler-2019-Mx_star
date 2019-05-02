package com.github.espylapiza.compiler_mxstar.nasm;

public final class DirectiveGlobal extends Directive {
    private final String name;

    public DirectiveGlobal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "global " + name;
    }
}
