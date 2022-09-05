package operations;

import baseTypes.Bool;
import derivedAST.FinalSyntaxNode;

public class ComparisonInfix extends GeneralizedBuiltinOperation{
    public ComparisonInfix(String op, FinalSyntaxNode a, FinalSyntaxNode b) {
        setName(op);
        setOrigin(a);
        setVector(b);
    }

    public FinalSyntaxNode getType() {
        return new Bool();
    }
}
