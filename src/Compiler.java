import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class Compiler {
    public static void main(String[] args) throws IOException {
        try {
            // args
            InputStream istream = new FileInputStream("program.txt");

            ParseTree ast = AST.fromStream(istream);
            /*
             * ParseTreeWalker walker = new ParseTreeWalker(); Evaluator eval = new
             * Evaluator();
             * 
             * // System.out.println("LISP:"); //
             * System.out.println(tree.toStringTree(parser)); walker.walk(eval, tree);
             */

            // System.out.println("target asm code");
        } catch (final IOException e) {
            System.err.println(e);
            return;
        }
    }
}
