package operations.arithmetic;

import derivedAST.FinalSyntaxNode;

public class Divide extends ArithmeticInfix{
    public Divide(){}
    public Divide(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "div";
    }
}
