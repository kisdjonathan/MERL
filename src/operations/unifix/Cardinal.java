package operations.unifix;

import baseTypes.Int;
import derivedAST.FinalSyntaxNode;
import operations.BuiltinOperation;

public class Cardinal extends BuiltinOperation {
    public Cardinal(){}
    public Cardinal(FinalSyntaxNode value) {
        setOrigin(value);
    }

    public String getName() {
        return "cardinal";
    }

    public FinalSyntaxNode getDeclaredType() {
        return new Int();
    }
}
