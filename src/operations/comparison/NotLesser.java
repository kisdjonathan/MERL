package operations.comparison;

import derivedAST.FinalSyntaxNode;

public class NotLesser extends ComparisonInfix{
    public NotLesser() {}
    public NotLesser(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "not lesser";
    }
}
