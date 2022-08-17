package operations;

import baseAST.Operator;
import baseAST.SyntaxNode;
import derivedAST.FinalSyntaxNode;

public class Without extends With {
    public Without(){}

    public Without(FinalSyntaxNode add, FinalSyntaxNode val){
        setOrigin(add);
        setVector(val);
    }

}
