package operations.unifix;

import derivedAST.FinalSyntaxNode;
import operations.bitwise.BitwiseInfix;

public class BitNot extends BitwiseInfix {
    public BitNot(){}
    public BitNot(FinalSyntaxNode value) {
        setOrigin(value);
    }

    public String getName() {
        return "bit not";
    }
}
