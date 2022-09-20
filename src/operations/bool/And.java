package operations.bool;

import derivedAST.FinalSyntaxNode;

public class And extends BoolInfix{
    public And() {}
    public And(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "and";
    }
}
