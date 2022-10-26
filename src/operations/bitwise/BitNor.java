package operations.bitwise;

import derivedAST.FinalSyntaxNode;

public class BitNor extends BitwiseInfix{
    public BitNor() {}
    public BitNor(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "bit nor";
    }
}
