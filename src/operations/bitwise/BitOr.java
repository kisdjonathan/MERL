package operations.bitwise;

import derivedAST.FinalSyntaxNode;

public class BitOr extends  BitwiseInfix{
    public BitOr() {}
    public BitOr(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "bit or";
    }
}
