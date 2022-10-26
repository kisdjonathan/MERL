package operations.unifix;

import derivedAST.FinalSyntaxNode;
import operations.BuiltinOperation;

public abstract class ArithmeticUnifix extends BuiltinOperation {
    public FinalSyntaxNode getDeclaredType() {
        return getOrigin();
    }
}
