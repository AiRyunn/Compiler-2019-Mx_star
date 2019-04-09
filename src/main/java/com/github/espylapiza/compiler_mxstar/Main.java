package com.github.espylapiza.compiler_mxstar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Compiler compiler = createCompiler(args);
        compiler.compile();
    }

    private static Compiler createCompiler(String[] args) {
        InputStream istream = System.in;
        OutputStream ostream = System.out;

        boolean debugMode = false;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            if (arg.equals("-o")) {
                i++;
                try {
                    ostream = new FileOutputStream(args[i], false);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            } else if (arg.equals("--debug")) {
                debugMode = true;
            } else {
                try {
                    istream = new FileInputStream(args[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        }

        configLogger(debugMode);

        return new Compiler(istream, ostream);
    }

    private static void configLogger(boolean debugMode) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$s] %2$s > %5$s %n");

        Level level;
        if (debugMode) {
            level = Level.FINE;
        } else {
            level = Level.INFO;
        }

        Logger rootLogger = LogManager.getLogManager().getLogger("");
        rootLogger.setLevel(level);
        for (Handler h : rootLogger.getHandlers()) {
            h.setLevel(level);
        }
    }
}