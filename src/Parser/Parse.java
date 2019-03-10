import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class Parse {
    public static void parse(InputStream istream) throws IOException {
        try {
            CharStream input = CharStreams.fromStream(istream);

            // System.out.println("LISP:");
            Mx_starLexer lexer = new Mx_starLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            Mx_starParser parser = new Mx_starParser(tokens);
            ParseTree tree = parser.program();
            // System.out.println(tree.toStringTree(parser));

            ParseTreeWalker walker = new ParseTreeWalker();
            Evaluator eval = new Evaluator();
            walker.walk(eval, tree);
        } catch (final IOException e) {
            System.err.println(e);
            return;
        }
    }
}
