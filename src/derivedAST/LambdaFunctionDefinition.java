package derivedAST;

import baseTypes.Tuple;

//TODO L complete
public class LambdaFunctionDefinition extends FunctionDefinition{
    public LambdaFunctionDefinition(Tuple param, Tuple ret) {
       super(param, ret);
    }

    public LambdaFunctionDefinition(Tuple param, Tuple ret, Tuple definition) {
        super(param, ret, definition);
    }
}
