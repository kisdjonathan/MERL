package operations.arithmetic;

import derivedAST.FinalSyntaxNode;

public class Subtract extends ArithmeticInfix{
    public Subtract(){}
    public Subtract(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "sub";
    }
}
