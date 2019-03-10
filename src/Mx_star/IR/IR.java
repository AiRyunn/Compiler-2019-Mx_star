package Mx_star.IR;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import Mx_star.NASM.*;

public class IR {
    public static IR fromAST(ParseTree ast) {
        return new IR();
    }

    public NASM toNASM() {
        return new NASM();
    }

    public void optimize() {
        // TODO: Optimization
    }
}