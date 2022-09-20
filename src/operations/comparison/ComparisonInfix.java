package operations.comparison;

import baseTypes.Bool;
import derivedAST.FinalSyntaxNode;
import operations.BuiltinOperation;

/**
 * represents operator infix that performs comparison
 */
public class ComparisonInfix extends BuiltinOperation {
    public ComparisonInfix(){}
    public ComparisonInfix(FinalSyntaxNode a, FinalSyntaxNode b) {
        setOrigin(a);
        setVector(b);
    }

    public FinalSyntaxNode getDeclaredType() {
        return new Bool();
    }
}
