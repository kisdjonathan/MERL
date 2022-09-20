package operations.arithmetic;

import derivedAST.FinalSyntaxNode;

public class Multiply extends ArithmeticInfix{
    public Multiply(){}
    public Multiply(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "mul";
    }
}
