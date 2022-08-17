package baseAST;

import derivedAST.FinalSyntaxNode;
import data.Usage;

import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.HashSet;

//baseAST.Literal stores the type and name of a literal (numbers and strings)
public class Literal extends FinalSyntaxNode {
    private String name = null;
    private FinalSyntaxNode type = null;

    public Literal(String name, FinalSyntaxNode type){
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public FinalSyntaxNode getType() {return type;}
    public Usage getUsage() {
        return Usage.LITERAL;
    }
    public void writeBytes(BufferedWriter out) {
        //TODO write bytes
    }

    //TODO allow custom suffixes, and move suffix support to Type
    private static HashSet<String> suffixes = new HashSet<>(Arrays.asList(
            "d", "ud", "ld", "uld",
            "c", "uc", "lc", "ulc",
            "f", "uf", "lf", "ulf",

            "cl", "ucl", "lcl", "ulcl", //dynamic string
            "cv", "ucv", "lcv", "ulcv", //static string

            "l", "v",
            "us", "s",
            "m", "um",
            "r"
    ));
    public static boolean isSuffix(String s) {
        return suffixes.contains(s);
    }
    public static boolean isLiteral(String s) {
        return s.equals("\"") || Character.isDigit(s.charAt(0));
    }
}
