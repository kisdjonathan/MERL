package operations;

import baseAST.Literal;
import baseAST.Operator;
import baseAST.SyntaxNode;
import data.Type;

public class Index extends Operator {

    public Index(SyntaxNode ref, SyntaxNode pos) {
        addChild(ref);
        addChild(pos);
    }
    public Index(SyntaxNode ref, int pos) {
        this(ref, new Literal(String.valueOf(pos), Type.INT));
    }
}
