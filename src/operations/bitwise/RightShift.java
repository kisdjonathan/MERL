package operations.bitwise;

import derivedAST.FinalSyntaxNode;

public class RightShift extends  BitwiseInfix{
    public RightShift() {}
    public RightShift(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "right shift";
    }
}
