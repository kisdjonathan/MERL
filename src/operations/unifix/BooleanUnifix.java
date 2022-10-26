package operations.unifix;

import baseTypes.Bool;
import derivedAST.FinalSyntaxNode;
import operations.BuiltinOperation;

public abstract class BooleanUnifix extends BuiltinOperation {
    public FinalSyntaxNode getDeclaredType() {
        return new Bool();
    }
}
