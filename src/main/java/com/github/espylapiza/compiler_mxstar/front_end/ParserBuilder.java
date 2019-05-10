package com.github.espylapiza.compiler_mxstar.front_end;

import java.io.InputStream;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starLexer;
import com.github.espylapiza.compiler_mxstar.parser.Mx_starParser;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

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
        istream.close();

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
