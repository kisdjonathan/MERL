package derivedAST;

import baseAST.SyntaxNode;
import derivedAST.FunctionDefinition;
import derivedAST.Tuple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//TODO L complete
public class LambdaFunctionDefinition extends FunctionDefinition{
    public LambdaFunctionDefinition(Tuple param, Tuple ret) {
       super(param, ret);
    }

    public LambdaFunctionDefinition(Tuple param, Tuple ret, Tuple definition) {
        super(param, ret, definition);
    }
}
