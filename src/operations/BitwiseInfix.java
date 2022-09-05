package operations;

import derivedAST.FinalSyntaxNode;

public class BitwiseInfix extends GeneralizedBuiltinOperation{
    public BitwiseInfix(String op, FinalSyntaxNode a, FinalSyntaxNode b) {
        setName(op);
        setOrigin(a);
        setVector(b);
    }
}
