package operations.bitwise;

import derivedAST.FinalSyntaxNode;

public class BitXnor extends  BitwiseInfix{
    public BitXnor() {}
    public BitXnor(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "bit xnor";
    }
}
