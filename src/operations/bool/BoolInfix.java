package operations.bool;

import baseTypes.Bool;
import derivedAST.FinalSyntaxNode;
import operations.BuiltinOperation;

public class BoolInfix extends BuiltinOperation {
    public BoolInfix() {}
    public BoolInfix(FinalSyntaxNode a, FinalSyntaxNode b) {
        setOrigin(a);
        setVector(b);
    }

    public FinalSyntaxNode getDeclaredType() {
        return new Bool();
    }
}
