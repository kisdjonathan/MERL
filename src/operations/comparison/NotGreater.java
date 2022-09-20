package operations.comparison;

import derivedAST.FinalSyntaxNode;

public class NotGreater extends ComparisonInfix {
    public NotGreater() {}
    public NotGreater(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "not greater";
    }
}
