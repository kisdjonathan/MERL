package operations;

import derivedAST.FinalSyntaxNode;

public class With extends BuiltinOperation {
    public With(){}
    public With(FinalSyntaxNode val, FinalSyntaxNode add){
        setOrigin(val);
        setVector(add);
    }

    public String getName() {
        return "with";
    }
}
