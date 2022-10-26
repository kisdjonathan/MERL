package operations.bitwise;

import derivedAST.FinalSyntaxNode;

public class LeftShift extends  BitwiseInfix{
    public LeftShift() {}
    public LeftShift(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "left shift";
    }
}
