package baseTypes;

import baseAST.Literal;
import derivedAST.FinalSyntaxNode;
import derivedAST.Variable;

import java.util.*;

//TODO complete
public interface BasicType{
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
    List<Variable> getFields();
    List<Function> getFunctions();

    static boolean isSuffix(String suffix) {
        return Literal.isSuffix(suffix);
    }
    static FinalSyntaxNode decode(String suffix) {
        //TODO L make exhaustive
        return switch (suffix.toLowerCase()) {
            case "b" -> new Bool();
            case "c" -> new Char();
            case "lc" -> new Char();
            case "d" -> new Int();
            case "ld" -> new Int();
            case "f" -> new Float();
            case "lf" -> new Float();
            case "s" -> new Str();
            case "ls" -> new Str();
            default -> null;
        };
    }
}
