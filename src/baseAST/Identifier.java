package baseAST;

import data.Usage;
import derivedAST.Variable;

//baseAST.Identifier stores the type and name of a variable name
public class Identifier extends SyntaxNode {
    private String name = null;

    public Identifier(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public Usage getUsage() {
        return Usage.IDENTIFIER;
    }
    public Variable getReplacement() {
        if(!hasVariable(name))
            putVariable(name);
        return getVariable(name);
    }
}
