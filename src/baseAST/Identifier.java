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
        Variable ret = getVariable(name);
        if(ret == null)
            ret = putVariable(name);
        return ret;
    }
}
