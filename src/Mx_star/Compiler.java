package Mx_star;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

import Mx_star.AST.*;
import Mx_star.IR.*;
import Mx_star.NASM.*;

public class Compiler {
    public static void main(String[] args) throws IOException {
        String inputFilename = "";
        String outputFilename = "";

        boolean oState = false;

        for (String arg : args) {
            if (oState) {
                outputFilename = arg;
                oState = false;
            } else if (arg.equals("-o")) {
                oState = true;
            } else {
                inputFilename = arg;
            }
        }

        try {
            InputStream istream;
            OutputStream ostream;

            if (inputFilename.isEmpty()) {
                istream = System.in;
            } else {
                istream = new FileInputStream(inputFilename);
            }

            if (outputFilename.isEmpty()) {
                ostream = System.out;
            } else {
                ostream = new FileOutputStream(outputFilename, false);
            }

            ParseTree ast = AST.fromStream(istream);

            istream.close();

            IR ir = IR.fromAST(ast);

            ir.optimize();

            NASM nasm = ir.toNASM();

            ostream.write(nasm.toString().getBytes());
            ostream.close();
            /*
             * ParseTreeWalker walker = new ParseTreeWalker(); Evaluator eval = new
             * Evaluator();
             * 
             * // System.out.println("LISP:"); //
             * System.out.println(tree.toStringTree(parser)); walker.walk(eval, tree);
             */

        } catch (final IOException e) {
            System.err.println(e);
            return;
        }
    }
}
