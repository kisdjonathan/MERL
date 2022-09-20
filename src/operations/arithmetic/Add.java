package operations.arithmetic;

import derivedAST.FinalSyntaxNode;

public class Add extends ArithmeticInfix{
    public Add(){}
    public Add(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "add";
    }
}
