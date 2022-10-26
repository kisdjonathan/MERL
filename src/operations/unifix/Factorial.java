package operations.unifix;

import derivedAST.FinalSyntaxNode;

public class Factorial extends ArithmeticUnifix {
    public Factorial(){}
    public Factorial(FinalSyntaxNode value) {
        setOrigin(value);
    }

    public String getName() {
        return "factorial";
    }
}
