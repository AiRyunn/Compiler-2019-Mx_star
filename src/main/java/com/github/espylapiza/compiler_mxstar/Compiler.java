package com.github.espylapiza.compiler_mxstar;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;
import org.antlr.v4.runtime.tree.ParseTree;
import com.github.espylapiza.compiler_mxstar.optimizers.PizzaIROptimizer;
import com.github.espylapiza.compiler_mxstar.back_end.NASMTranslator;
import com.github.espylapiza.compiler_mxstar.front_end.ParserBuilder;
import com.github.espylapiza.compiler_mxstar.front_end.PizzaIRBuilder;
import com.github.espylapiza.compiler_mxstar.nasm.NASM;

public class Compiler {
    private final static Logger LOGGER = Logger.getLogger(Compiler.class.getName());

    InputStream istream;
    OutputStream ostream;

    Compiler(InputStream istream, OutputStream ostream) {
        this.istream = istream;
        this.ostream = ostream;
    }

    void compile() {
        try {
            LOGGER.info("build parse tree...");
            ParseTree parser = ParserBuilder.fromStream(istream);

            LOGGER.info("build PizzaIR...");
            PizzaIRBuilder builder = new PizzaIRBuilder();
            builder.fromMxStarParser(parser);

            LOGGER.info("optimize PizzaIR...");
            PizzaIROptimizer optimizer = new PizzaIROptimizer(builder.getIR());
            optimizer.optimize();

            LOGGER.info("translate to NASM...");
            NASMTranslator translator = new NASMTranslator(optimizer.getIR());
            NASM nasm = translator.getNASM();

            // TODO: Printer
            ostream.write(nasm.toString().getBytes());
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
