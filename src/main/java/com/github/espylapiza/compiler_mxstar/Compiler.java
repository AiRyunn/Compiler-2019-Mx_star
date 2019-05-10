package com.github.espylapiza.compiler_mxstar;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;
import org.antlr.v4.runtime.tree.ParseTree;

import com.github.espylapiza.compiler_mxstar.optimizers.NASMOptimizer;
import com.github.espylapiza.compiler_mxstar.optimizers.PizzaIROptimizer;
import com.github.espylapiza.compiler_mxstar.pizza_ir.PizzaIR;
import com.github.espylapiza.compiler_mxstar.back_end.NASMTransformer;
import com.github.espylapiza.compiler_mxstar.front_end.ParserBuilder;
import com.github.espylapiza.compiler_mxstar.front_end.PizzaIRBuilder;
import com.github.espylapiza.compiler_mxstar.nasm.NASM;

public class Compiler {
    private final static Logger LOGGER = Logger.getLogger(Compiler.class.getName());

    // TODO: add class Mx_starParams
    boolean semantic;
    InputStream istream;
    OutputStream asmOstream;
    OutputStream irOstream;

    Compiler(boolean semantic, InputStream istream, OutputStream asmOstream, OutputStream irOstream) {
        this.semantic = semantic;
        this.istream = istream;
        this.asmOstream = asmOstream;
        this.irOstream = irOstream;
    }

    void compile() {
        try {
            LOGGER.info("build parse tree...");
            ParseTree parser = ParserBuilder.fromStream(istream);

            LOGGER.info("build PizzaIR...");
            PizzaIRBuilder builder = new PizzaIRBuilder();
            builder.fromMxStarParser(parser);
            PizzaIR ir = builder.getIR();

            // TODO: Printer
            if (irOstream != null) {
                irOstream.write(ir.funcList.toString().getBytes());
                irOstream.close();
            }

            if (semantic) {
                return;
            }

            LOGGER.info("optimize PizzaIR...");
            PizzaIROptimizer optimizer = new PizzaIROptimizer(ir);
            optimizer.optimize();

            LOGGER.info("translate to NASM...");
            NASMTransformer translator = new NASMTransformer(optimizer.getIR());
            NASM nasm = translator.getNASM();

            NASMOptimizer nasmOptimizer = new NASMOptimizer(nasm);
            nasmOptimizer.optimize();

            // TODO: Printer
            asmOstream.write(nasm.toString().getBytes());
            asmOstream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
