package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FuncList implements Iterable<Func> {
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

    @Override
    public String toString() {
        String result = new String();
        boolean first = true;

        for (Map.Entry<FuncAddr, Func> entry : funcList.entrySet()) {
            Func func = entry.getValue();
            if (first) {
                first = false;
            } else {
                result += "\n\n";
            }
            result += func.toString();
        }
        return result;
    }

    @Override
    public Iterator<Func> iterator() {
        return funcList.values().iterator();
    }
}
