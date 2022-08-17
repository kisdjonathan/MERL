package derivedAST;

import baseTypes.Tuple;

public class KappaFunctionDefinition extends FunctionDefinition{
    public KappaFunctionDefinition(Tuple param, FinalSyntaxNode definition) {
        setParam(param);
        setBody(definition);
        setReturn(Tuple.asTuple(definition));
    }
}
