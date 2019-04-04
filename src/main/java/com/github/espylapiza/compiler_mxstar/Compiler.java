package com.github.espylapiza.compiler_mxstar;

import java.io.*;
import java.util.logging.Logger;

import org.antlr.v4.runtime.tree.*;
import com.github.espylapiza.compiler_mxstar.parser.*;
import com.github.espylapiza.compiler_mxstar.pizza_ir.*;
import com.github.espylapiza.compiler_mxstar.nasm.*;

public class Compiler {
    private final static Logger LOGGER = Logger.getLogger(Compiler.class.getName());

    InputStream istream;
    OutputStream ostream;

    Compiler(InputStream istream, OutputStream ostream) {
        this.istream = istream;
        this.ostream = ostream;
    }

    void run() {
        try {
            LOGGER.info("parser");

            ParseTree parser = ParserBuilder.fromStream(istream);

            istream.close();

            LOGGER.info("PizzaIRBuilder");

            PizzaIRBuilder builder = new PizzaIRBuilder();
            builder.fromParser(parser);
            PizzaIR ir = builder.getIR();

            // System.out.println(ir.toString());

            System.exit(0);

            LOGGER.info("optimization");

            ir.optimize();

            LOGGER.info("NASM");

            NASM nasm = ir.toNASM();

            ostream.write(nasm.toString().getBytes());
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}