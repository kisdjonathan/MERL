package operations;

import data.Usage;
import derivedAST.Field;
import derivedAST.FinalSyntaxNode;

//TODO
public abstract class BuiltinOperation extends Field {
    private FinalSyntaxNode first, second;

    public Usage getUsage() {
        return Usage.OPERATOR;
    }


    public FinalSyntaxNode getType() {
        return getOrigin().getType();
    }

    public void evaluate(){}

    public String toString() {
        return super.toString() +
                "[" + first +
                ", " + second +
                ']';
    }
}
