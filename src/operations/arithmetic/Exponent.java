package operations.arithmetic;

import derivedAST.FinalSyntaxNode;

public class Exponent extends ArithmeticInfix{
    public Exponent(){}
    public Exponent(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "exp";
    }
}
