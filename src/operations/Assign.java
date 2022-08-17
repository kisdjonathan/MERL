package operations;

import derivedAST.FinalSyntaxNode;

public class Assign extends BuiltinOperation {
    public Assign(){}
    public Assign(FinalSyntaxNode dest, FinalSyntaxNode value){
        setFirst(dest);
        setSecond(value);
    }

    public String getName() {
        return "assign";
    }
}
