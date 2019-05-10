package com.github.espylapiza.compiler_mxstar.nasm;

import java.util.ArrayList;
import java.util.List;

public class SectionText extends Section {
    public List<SectionItem> items;

    public SectionText() {
        super(".text");
        items = new ArrayList<SectionItem>();
    }

    public void addItem(SectionItem item) {
        items.add(item);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getDeclaration());
        for (SectionItem item : items) {
            sb.append(item.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
