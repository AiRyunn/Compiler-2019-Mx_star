package com.github.espylapiza.compiler_mxstar.ast;

public abstract class Node {

}

class PizzaIR extends Node {

}

class Function extends Node {
    String name, rtype;

    Function(String name, String type) {
        this.name = name;
        this.rtype = type;
    }
}