package com.github.espylapiza.compiler_mxstar.pizza_ir;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.logging.Logger;

import com.github.espylapiza.compiler_mxstar.utils.Pair;

class Domain {
    private final static Logger LOGGER = Logger.getLogger(Domain.class.getName());

    Class currentClass;
    private List<Block> block = new ArrayList<Block>();

    private Integer depth = 0;
    private Stack<Pair<Object, Integer>> varStack = new Stack<Pair<Object, Integer>>();

    boolean isGlobal() {
        return currentClass == null;
    }

    void addVar(Object variable) {
        LOGGER.fine("allocate variable: " + variable.type + " " + variable.name);
        if (variable.name != null) {
            varStack.add(new Pair<Object, Integer>(variable, depth));
        }
    }

    boolean hasVar(String name) {
        for (ListIterator<Pair<Object, Integer>> it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            Pair<Object, Integer> pre = it.previous();
            if (pre.first.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    Object getVar(String name) {
        for (ListIterator<Pair<Object, Integer>> it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            Pair<Object, Integer> pre = it.previous();
            if (pre.first.name != null && pre.first.name.equals(name)) {
                return pre.first;
            }
        }
        return null;
    }

    String getVarType(String name) {
        for (ListIterator<Pair<Object, Integer>> it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            Pair<Object, Integer> pre = it.previous();
            if (pre.first.name.equals(name)) {
                return pre.first.type.toString();
            }
        }
        assert false;
        return null;
    }

    Class getCurrentClass() {
        return currentClass;
    }

    String getClassAddr() {
        if (currentClass == null) {
            return "";
        }
        return currentClass.getName();
    }

    String getAddr() {
        String trace = getClassAddr();
        String currentFunc = getCurrentFunc();
        if (trace == null) {
            return currentFunc;
        } else if (currentFunc == null) {
            return trace;
        } else {
            return trace + "." + currentFunc;
        }
    }

    void setVarName(ObjectID id, String name) {
        for (ListIterator<Pair<Object, Integer>> it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            Pair<Object, Integer> pre = it.previous();
            if (pre.first.id.equals(id)) {
                pre.first.name = name;
                return;
            }
        }
        assert false;
    }

    void enterClass(Class class1) {
        depth++;
        currentClass = class1;
        block.add(new Block(-1, BlockType.CLASS));
    }

    void exitClass() {
        while (!varStack.isEmpty() && varStack.lastElement().second.equals(depth)) {
            varStack.pop();
        }
        depth--;
        currentClass = null;
        block.remove(block.size() - 1);
    }

    void enterFunc(Class owner, String name, Type rtype) {
        depth++;
        block.add(new Block(-1, BlockType.FUNC, name, rtype));
    }

    void exitFunc() {
        while (!varStack.isEmpty() && varStack.lastElement().second.equals(depth)) {
            LOGGER.fine(
                    "remove variable: " + varStack.lastElement().first.type + " " + varStack.lastElement().first.name);
            varStack.pop();
        }
        depth--;
        block.remove(block.size() - 1);
    }

    void enterCondition(int position) {
        LOGGER.fine("enterCondition");
        depth++;
        block.add(new Block(position, BlockType.SCOPE));
    }

    void exitCondition() {
        LOGGER.fine("exitCondition");
        while (!varStack.isEmpty() && varStack.lastElement().second.equals(depth)) {
            varStack.pop();
        }
        depth--;
        block.remove(block.size() - 1);
    }

    void enterLoop(int position) {
        LOGGER.fine("enterLoop");
        depth++;
        block.add(new Block(position, BlockType.LOOP));
    }

    void exitLoop() {
        LOGGER.fine("exitLoop");
        while (!varStack.isEmpty() && varStack.lastElement().second.equals(depth)) {
            varStack.pop();
        }
        depth--;
        block.remove(block.size() - 1);
    }

    boolean inLoop() {
        for (Block b : block) {
            if (b.type == BlockType.LOOP)
                return true;
        }
        return false;
    }

    boolean inFunc() {
        for (Block b : block) {
            if (b.type == BlockType.FUNC)
                return true;
        }
        return false;
    }

    Type getRtype() {
        for (Block b : block) {
            if (b.type == BlockType.FUNC)
                return b.rtype;
        }
        assert false;
        return null;
    }

    String getCurrentFunc() {
        for (Block b : block) {
            if (b.type == BlockType.FUNC)
                return b.name;
        }
        return null;
    }

    boolean canAllocate(String name) {
        for (ListIterator<Pair<Object, Integer>> it = varStack.listIterator(varStack.size()); it.hasPrevious();) {
            Pair<Object, Integer> pre = it.previous();
            if (pre.second < depth) {
                break;
            }
            if (pre.first.name.equals(name)) {
                return false;
            }
        }
        return true;
    }
}
