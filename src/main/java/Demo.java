import model.LogEntry;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.Trees;

import java.util.Arrays;
import java.util.List;

public class Demo {




    public static void main(String[] args) {
        String logLines = "2018-May-05 14:20:21 DEBUG entering awesome method\r\n" +
                "2018-May-05 14:20:24 ERROR Bad thing happened\r\n";
        LogLexer serverLogLexer = new LogLexer(CharStreams.fromString(logLines));
        CommonTokenStream tokens = new CommonTokenStream( serverLogLexer );
        LogParser logParser = new LogParser(tokens);
        ParseTreeWalker walker = new ParseTreeWalker();
        CustomLogListener logWalker = new CustomLogListener();
        walker.walk(logWalker, logParser.log());


        System.out.println(logWalker.getEntries().size());
        LogEntry error = logWalker.getEntries().get(1);
        System.out.println(error.getLevel());
        System.out.println(error.getMessage());
        System.out.println(error.getTimestamp());


        /*

          assertThat(logWalker.getEntries().size(), is(2));
        LogEntry error = logWalker.getEntries().get(1);
        assertThat(error.getLevel(), is(LogLevel.ERROR));
        assertThat(error.getMessage(), is("Bad thing happened"));
        assertThat(error.getTimestamp(), is(LocalDateTime.of(2018,5,5,14,20,24)));


         */
    }
}
