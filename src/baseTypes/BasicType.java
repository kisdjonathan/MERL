package baseTypes;

import baseAST.Literal;
import baseAST.SyntaxNode;
import data.Usage;
import derivedAST.ExternalFunction;
import derivedAST.ExternalVariable;
import derivedAST.FinalSyntaxNode;
import operations.Call;
import operations.Cast;

import java.util.*;

//TODO complete
public interface BasicType{
    public static BasicType  //TODO L make exhaustive
            CHAR    = new BasicType("char"),
            CHARL   = new BasicType("charl"),
            FLOAT   = new BasicType("float"),
            FLOATL  = new BasicType("floatl");

    boolean typeEquals(FinalSyntaxNode other);

    boolean isIndexed();
    boolean isFielded();

    //for inference types
    //TODO
    void assertField(String name, FinalSyntaxNode t);
    default boolean hasField(String name) {
        return getField(name) != null;
    }
    FinalSyntaxNode getField(String name);

    //TODO
    List<ExternalVariable> getFields();
    List<ExternalFunction> getFunctions();

    static boolean isSuffix(String suffix) {
        return Literal.isSuffix(suffix);
    }
    static BasicType decode(String suffix) {
        //TODO L make exhaustive
        return switch (suffix.toLowerCase()) {
            case "b" -> new Bool();
            case "c" -> CHAR;
            case "lc" -> CHARL;
            case "d" -> new Int();
            case "ld" -> new Int();
            case "f" -> FLOAT;
            case "lf" -> FLOATL;
            case "s" -> new Str();
            case "ls" -> new Str();
            default -> null;
        };
    }
}
