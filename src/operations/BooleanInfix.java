package operations;

import derivedAST.FinalSyntaxNode;

public class BooleanInfix extends GeneralizedBuiltinOperation{
    public BooleanInfix(String op, FinalSyntaxNode a, FinalSyntaxNode b) {
        setName(op);
        setFirst(a);
        setSecond(b);
    }
}
