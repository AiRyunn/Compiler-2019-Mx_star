package com.github.espylapiza.compiler_mxstar.nasm;

import java.util.ArrayList;
import java.util.List;

public class Sections {
    private List<Section> sectionList = new ArrayList<Section>();

    void add(Section section) {
        sectionList.add(section);
    }

    @Override
    public String toString() {
        String result = new String();

        boolean first = true;

        for (Section section : sectionList) {
            if (!first) {
                result += "\n";
            } else {
                first = false;
            }
            result += section.toString();
        }
        return result;
    }
}
