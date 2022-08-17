package operations;

import derivedAST.FinalSyntaxNode;

public class ComparisonInfix extends GeneralizedBuiltinOperation{
    public ComparisonInfix(String op, FinalSyntaxNode a, FinalSyntaxNode b) {
        setName(op);
        setFirst(a);
        setSecond(b);
    }

    public FinalSyntaxNode getType() {
        return FinalSyntaxNode.BOOL;
    }
}
