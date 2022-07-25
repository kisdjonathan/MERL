package operations;

import baseAST.*;
import data.Usage;

//derivedAST.Call stores information of a function call
//TODO complete
public class Call extends Operator {
    public Call(String groupType){
        super(groupType);
    }
    public Call(SyntaxNode func, Group args){
        this(args.getName());
        addChild(func);
        addChild(args.getBody());
    }

    public Identifier getFunction() {
        return (Identifier) getChild(0);
    }

    public Usage getUsage() {
        return Usage.CALL;
    }
}
