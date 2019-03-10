
import java.io.*;

public class Compile {
    public static void main(String[] args) throws IOException {
        try {
            InputStream istream = new FileInputStream("program.txt");

            // System.out.println("target asm code");
        } catch (final IOException e) {
            System.err.println(e);
            return;
        }
    }
}
