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
        OutputStream asmOstream = System.out;
        OutputStream irOstream = null;

        boolean debugMode = false, semantic = false;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg) {
                case "--debug":
                    debugMode = true;
                    break;
                case "--semantic":
                    semantic = true;
                    break;
                case "-o":
                case "--output":
                    i++;
                    try {
                        asmOstream = new FileOutputStream(args[i], false);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(-1);
                    }
                    break;
                case "--ir":
                    i++;
                    try {
                        irOstream = new FileOutputStream(args[i], false);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(-1);
                    }
                    break;
                default:
                    try {
                        istream = new FileInputStream(args[i]);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(-1);
                    }
            }
        }

        configLogger(debugMode);

        return new Compiler(semantic, istream, asmOstream, irOstream);
    }

    private static void configLogger(boolean debugMode) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$s] %2$s > %5$s %n");

        Level level;
        if (debugMode) {
            level = Level.FINE;
        } else {
            level = Level.SEVERE;
        }

        Logger rootLogger = LogManager.getLogManager().getLogger("");
        rootLogger.setLevel(level);
        for (Handler h : rootLogger.getHandlers()) {
            h.setLevel(level);
        }
    }
}
