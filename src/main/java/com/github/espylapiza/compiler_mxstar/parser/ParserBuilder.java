package com.github.espylapiza.compiler_mxstar.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.misc.*;
import java.io.*;

class ThrowingErrorListener extends BaseErrorListener {
    public static final ThrowingErrorListener INSTANCE = new ThrowingErrorListener();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
            String msg, RecognitionException e) throws ParseCancellationException {
        throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
    }
}

public class ParserBuilder {
    public static ParseTree fromStream(InputStream istream) throws Exception {
        CharStream input = CharStreams.fromStream(istream);

        Mx_starLexer lexer = new Mx_starLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Mx_starParser parser = new Mx_starParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);

        try {
            return parser.program();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
