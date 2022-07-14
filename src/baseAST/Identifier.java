package baseAST;

import data.Type;
import data.Usage;

//baseAST.Identifier stores the type and name of a variable name
public class Identifier extends SyntaxNode {
    private String name = null;
    private Type type = null;

    public Identifier(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public Usage getUsage() {
        return Usage.IDENTIFIER;
    }
    public Type getType() {return type;}
    public void setType(Type type) {
        this.type = type;
    }
}
