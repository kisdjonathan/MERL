package derivedAST;

import baseAST.SyntaxNode;
import data.Type;
import data.Usage;

//Variable stores the data related to a variable (relative memory position, type, specific functions)
public class Variable extends FinalSyntaxNode {
    private String name = null;
    private Type type = null;

    public Variable(String name, Type type) {
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
        return null;
    }

    public SyntaxNode getParent() {
        //not a singleton,
        //and variables will not be searching for any other variables or functions
        return null;
    }

    public boolean isComplete() {
        return true;
    }

    public void assertField(String name, Type t) {

    }

    public Type getType() {
        return type;
    }
    public void setType(Type t) {
        type = t;
    }
}
