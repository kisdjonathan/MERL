package derivedAST;

import baseAST.SyntaxNode;
import data.Type;

public abstract class FinalSyntaxNode extends SyntaxNode {
    private FinalSyntaxNode type = null;

    public boolean isComplete() {
        return true;
    }

    public FinalSyntaxNode getReplacement(){return this;}

    public abstract Type getType();

    //call as soon after getReplacement as possible on the replacement
    public void evaluate(){}
}
