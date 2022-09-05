package Compiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//TokenWriter writes converted AST to machine code to an executable
//TODO complete
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
