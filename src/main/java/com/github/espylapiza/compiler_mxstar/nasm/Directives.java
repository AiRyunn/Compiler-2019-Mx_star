package com.github.espylapiza.compiler_mxstar.nasm;

import java.util.ArrayList;
import java.util.List;

public class Directives {
    private List<Directive> directiveList = new ArrayList<Directive>();

    void add(Directive directive) {
        directiveList.add(directive);
    }

    @Override
    public String toString() {
        String result = new String();
        for (Directive directive : directiveList) {
            result += directive.toString() + "\n";
        }
        return result;
    }
}