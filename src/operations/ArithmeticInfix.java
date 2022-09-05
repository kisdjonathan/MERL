package operations;

import derivedAST.FinalSyntaxNode;

public class ArithmeticInfix extends GeneralizedBuiltinOperation{
    public ArithmeticInfix(String op, FinalSyntaxNode a, FinalSyntaxNode b) {
        setName(op);
        setOrigin(a);
        setVector(b);
    }
}