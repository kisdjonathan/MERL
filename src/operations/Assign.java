package operations;

import derivedAST.FinalSyntaxNode;

public class Assign extends BuiltinOperation {
    public Assign(){}
    public Assign(FinalSyntaxNode dest, FinalSyntaxNode value){
        setOrigin(dest);
        setVector(value);
    }

    public String getName() {
        return "assign";
    }
}
