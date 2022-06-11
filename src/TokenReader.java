import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class TokenReader {
    private SymbolReader source;
    private Queue<Token> queue = new LinkedList<>();

    public TokenReader(File source) {
        this.source = new SymbolReader(source);
    }

    public Token get() {
        //TODO
        return null;
    }

    public Token peek() {
        if(queue.isEmpty())
            queue.add(get());
        return queue.peek();
    }

    public boolean eof() {
        return queue.isEmpty() && source.eof();
    }
}
