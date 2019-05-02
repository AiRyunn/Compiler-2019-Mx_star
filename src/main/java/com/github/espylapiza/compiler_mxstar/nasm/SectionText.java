package com.github.espylapiza.compiler_mxstar.nasm;

import java.util.ArrayList;
import java.util.List;

public class SectionText extends Section {
    private List<SectionTextItem> items;

    public SectionText() {
        super(".text");
        items = new ArrayList<SectionTextItem>();
    }

    public void addLabel(String label) {
        items.add(new Label(label));
    }

    @Override
    public String toString() {
        String result = super.getDeclaration();
        for (SectionTextItem item : items) {
            if (item instanceof Label) {
                result += item.toString() + ":\n";
            }
        }
        return result;
    }
}
