package operations.bool;

import derivedAST.FinalSyntaxNode;

public class Xor extends BoolInfix{
    public Xor() {}
    public Xor(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "xor";
    }
}
