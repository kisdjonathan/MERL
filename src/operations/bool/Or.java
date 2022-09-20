package operations.bool;

import derivedAST.FinalSyntaxNode;

public class Or extends BoolInfix{
    public Or() {}
    public Or(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "or";
    }
}
