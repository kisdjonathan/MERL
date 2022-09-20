package operations.comparison;

import derivedAST.FinalSyntaxNode;

public class NotEqual extends ComparisonInfix{
    public NotEqual() {}
    public NotEqual(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "not equal";
    }
}
