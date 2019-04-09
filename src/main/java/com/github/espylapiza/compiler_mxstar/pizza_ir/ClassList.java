package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

public class ClassList {
    @Expose
    private Map<String, Class> classList;

    ClassList() {
        classList = new HashMap<String, Class>();
    }

    public void add(Class class1) {
        classList.put(class1.getName(), class1);
    }

    public Class get(String name) {
        return classList.get(name);
    }

    JsonElement toJson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJsonTree(classList, HashMap.class);
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create().toJson(classList);
    }
}