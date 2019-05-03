package com.github.espylapiza.compiler_mxstar.nasm;

import java.util.ArrayList;
import java.util.List;

public class SectionData extends Section {
    private List<SectionItem> items;

    public SectionData() {
        super(".data");
        items = new ArrayList<SectionItem>();
    }

    public void addItem(SectionItem item) {
        items.add(item);
    }

    @Override
    public String toString() {
        String result = super.getDeclaration();
        for (SectionItem item : items) {
            result += item.toString();
        }
        return result;
    }
}
