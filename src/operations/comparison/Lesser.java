package operations.comparison;

import derivedAST.FinalSyntaxNode;

public class Lesser extends ComparisonInfix{
    public Lesser() {}
    public Lesser(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "lesser";
    }
}
