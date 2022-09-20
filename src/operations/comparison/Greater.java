package operations.comparison;

import derivedAST.FinalSyntaxNode;

public class Greater extends ComparisonInfix{
    public Greater() {}
    public Greater(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "greater";
    }
}
