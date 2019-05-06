package com.github.espylapiza.compiler_mxstar.nasm;

public class Label extends SectionItem {
    private final String name;
    private static int db_index = 0;

    public static Label newDB() {
        return new Label("db_" + String.valueOf(db_index++));
    }

    public Label(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ":";
    }
}
