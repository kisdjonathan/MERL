package operations;

import data.Type;
import derivedAST.FinalSyntaxNode;

public class ComparisonInfix extends GeneralizedBuiltinOperation{
    public ComparisonInfix(String op, FinalSyntaxNode a, FinalSyntaxNode b) {
        setName(op);
        setOrigin(a);
        setVector(b);
    }

    public Type getType() {
        return Type.BOOL;
    }
}
