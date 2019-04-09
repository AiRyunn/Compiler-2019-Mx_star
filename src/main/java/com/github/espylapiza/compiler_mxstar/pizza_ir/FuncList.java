package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class FuncList {
    private Map<FuncAddr, Func> funcList = new HashMap<FuncAddr, Func>();

    public void addFunc(Func func) {
        FuncAddr addr = func.getAddr();
        if (funcList.containsKey(addr)) {
            assert false;
        }
        funcList.put(addr, func);
    }

    public Func get(FuncAddr addr) {
        return funcList.get(addr);
    }

    JsonElement toJson() {
        return new Gson().toJsonTree(funcList, HashMap.class);
    }

    // @Override
    // public String toString() {
    //     return new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create().toJson(funcList);
    // }
    @Override
    public String toString() {
        String result = new String();
        boolean first = true;

        for (Map.Entry<FuncAddr, Func> entry : funcList.entrySet()) {
            Func func = entry.getValue();
            if (func instanceof FuncExtra) {
                if (first) {
                    first = false;
                } else {
                    result += "\n\n";
                }
                result += func.toString();
            }
        }
        return result;
    }
}
