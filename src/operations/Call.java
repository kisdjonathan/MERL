package operations;

import baseTypes.InferredType;
import derivedAST.FinalSyntaxNode;
import data.Usage;
import derivedAST.Function;
import baseTypes.Tuple;

//Call stores information of a function call
//TODO L for now, Call is expected to return the values of ret, but in the future, allow Call to write directly to ret
//TODO L allow calls on non-functions
//TODO complete
public class Call extends BuiltinOperation {
    //func should be a function when passed in (ie, not an identifier)
    public Call(FinalSyntaxNode func, FinalSyntaxNode args) {
        setOrigin(func);
        setVector(Tuple.asTuple(args));
        setDeclaredType(new InferredType());
    }

    public Call(FinalSyntaxNode func, FinalSyntaxNode args, FinalSyntaxNode type) {
        setOrigin(func);
        setVector(Tuple.asTuple(args));
        setDeclaredType(type);
    }

    public Call(Function func, FinalSyntaxNode args) {
        this((FinalSyntaxNode)func, args);
        setDeclaredType(func.getDeclaredType());
    }

    public Usage getUsage() {
        return Usage.CALL;
    }
    public String getName() {
        return "call";
    }
}
