import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class SymbolReader {
    private BufferedReader source;
    private Queue<String> lineBuffer = new LinkedList<>();

    public SymbolReader(File source) {
        try {
            this.source = new BufferedReader(new FileReader(source));
            peek(); //fill buffer
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    public String get() {
        peek();
        return lineBuffer.remove();
    }

    public String peek() {
        try {
            String line = source.readLine();
            //TODO
        } catch (IOException ignored) { }
        return lineBuffer.peek();
    }

    public boolean eof() {
        return lineBuffer.isEmpty();
    }
}
