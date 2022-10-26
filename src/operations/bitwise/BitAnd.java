package operations.bitwise;

import derivedAST.FinalSyntaxNode;

public class BitAnd extends BitwiseInfix{
    public BitAnd() {}
    public BitAnd(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "bit and";
    }
}
