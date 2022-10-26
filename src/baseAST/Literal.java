package baseAST;

import baseTypes.BasicType;
import baseTypes.Int;
import derivedAST.FinalSyntaxNode;
import data.Usage;

//baseAST.Literal stores the type and name of a literal (numbers and strings)
public class Literal extends FinalSyntaxNode {
    private String name;
    private FinalSyntaxNode type;

    public Literal(String name, FinalSyntaxNode type){
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public FinalSyntaxNode getDeclaredType() {return type;}
    public Usage getUsage() {
        return Usage.LITERAL;
    }

    public FinalSyntaxNode getReplacement() {
        return ((BasicType)type).newInstance(name);
    }

    /**
     * checks if the value of s signals a literal
     * s signals a literal if s==" or s is a number
     */
    public static boolean isLiteral(String s) {
        return s.equals("\"") || Character.isDigit(s.charAt(0));
    }
}
