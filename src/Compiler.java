import org.antlr.v4.runtime.tree.*;
import java.io.*;

import Mx_star.AST.*;
import Mx_star.PizzaIR.*;
import NASM.*;
import logging.*;

public class Compiler {
    public static void main(String[] args) throws Exception {
        Logging.info("compiling...");

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

            Logging.info("AST");

            ParseTree ast = AST.fromStream(istream);

            istream.close();

            Logging.info("PizzaIR");

            PizzaIR ir = PizzaIR.fromAST(ast);

            System.exit(0);

            System.out.println(ir.toString());

            Logging.info("optimize");

            ir.optimize();

            Logging.info("NASM");

            NASM nasm = ir.toNASM();

            ostream.write(nasm.toString().getBytes());
            ostream.close();

            // ParseTreeWalker walker = new ParseTreeWalker(); Evaluator eval = new
            // Evaluator();

            // // System.out.println("LISP:"); //
            // System.out.println(ast.toStringTree(parser)); walker.walk(eval, tree);

        } catch (Exception e) {
            e.printStackTrace(System.err);
            e.notify();
        }
    }
}
