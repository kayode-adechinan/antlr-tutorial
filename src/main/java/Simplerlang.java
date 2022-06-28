import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Simplerlang {


    public static void main(String[] args) {
        try {

            simplerlangLexer lexer = new simplerlangLexer(CharStreams.fromFileName("test.simpler"));
            CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
            simplerlangParser parser = new simplerlangParser(commonTokenStream);
            parser.addParseListener(new SimplerlangCustomListener());
            parser.program();
        } catch (IOException ex) {
            Logger.getLogger(Simplerlang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
