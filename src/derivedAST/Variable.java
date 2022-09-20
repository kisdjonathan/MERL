package derivedAST;

import baseAST.SyntaxNode;
import data.Usage;

//Variable stores the data related to a variable (relative memory position, type, specific functions)
public class Variable extends FinalSyntaxNode {
    private String name = null;
    private FinalSyntaxNode type = null;

    public Variable(String name, FinalSyntaxNode type) {
        this.name = name;
        this.type = type;
    }
    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public Usage getUsage() {
        return Usage.VARIABLE;
    }

    public SyntaxNode getParent() {
        //not a singleton,
        //and variables will not be searching for any other variables or functions
        return null;
    }

    public boolean isComplete() {
        return true;
    }

    public void assertField(String name, FinalSyntaxNode t) {

    }

    public FinalSyntaxNode getDeclaredType() {
        return type;
    }
    public void setDeclaredType(FinalSyntaxNode t) {
        type = t;
    }
}
