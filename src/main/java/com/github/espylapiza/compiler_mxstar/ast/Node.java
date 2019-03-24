package com.github.espylapiza.compiler_mxstar.ast;

public abstract class Node {

}

class ParamListInstance extends Node {
    ParamsInstance params;

    ParamListInstance(ParamsInstance params) {
        this.params = params;
    }
}

class ParamList extends Node {
    Params params;

    ParamList(Params params) {
        this.params = params;
    }
}

class FunctionNode extends Node {
    String name, rtype;

    FunctionNode(String name, String rtype) {
        this.name = name;
        this.rtype = rtype;
    }
}

class VariableNode extends Node {
    String name, type;

    VariableNode(String name, String type) {
        this.name = name;
        this.type = type;
    }
}

class ObjectNode extends Node {
    String name, type, owner;

    ObjectNode(String name, String type, String owner) {
        this.name = name;
        this.type = type;
        this.owner = owner;
    }
}