package operations;

import baseAST.Operator;
import baseAST.SyntaxNode;
import derivedAST.FinalSyntaxNode;

public class With extends BuiltinOperation {
    private FinalSyntaxNode value = null;

    public With(){}
    public With(FinalSyntaxNode val){
        value = val;
    }

    public String getName() {
        return "with";
    }
}
