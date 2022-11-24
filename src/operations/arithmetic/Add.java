package operations.arithmetic;

import Interpreter.Context;
import Interpreter.Value;
import derivedAST.FinalSyntaxNode;
import derivedAST.Variable;

public class Add extends ArithmeticInfix{
    public Add(){}
    public Add(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        super(origin, vector);
    }

    public String getName() {
        return "add";
    }

    public Value interpret(Context context) {
        Value first = getOrigin().interpret(context);
        Value second = getOrigin().interpret(context);


    }
}
