import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TokenWriter {
    BufferedWriter writer;

    public TokenWriter(File output) {
        try {
            writer = new BufferedWriter(new FileWriter(output));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-2);
        }
    }
}
