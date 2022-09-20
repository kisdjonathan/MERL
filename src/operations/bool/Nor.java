package operations.bool;

import derivedAST.FinalSyntaxNode;

public class Nor extends BoolInfix{
    public Nor() {}
    public Nor(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "nor";
    }
}
