package operations;

import baseAST.Operator;
import baseAST.SyntaxNode;

public class Without extends Operator {
    SyntaxNode value = null;

    public Without(){}
    public Without(SyntaxNode val){
        value = val;
    }
}
