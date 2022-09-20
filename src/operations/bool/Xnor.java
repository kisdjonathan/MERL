package operations.bool;

import derivedAST.FinalSyntaxNode;

public class Xnor extends BoolInfix{
    public Xnor() {}
    public Xnor(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "xnor";
    }
}
