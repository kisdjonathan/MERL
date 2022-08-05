package operations;

import derivedAST.FinalSyntaxNode;
import data.Type;
import data.Usage;

//TODO
public abstract class BuiltinOperation extends FinalSyntaxNode {
    private FinalSyntaxNode origin, vector;

    public Usage getUsage() {
        return Usage.OPERATOR;
    }

    public FinalSyntaxNode getOrigin() {
        return origin;
    }
    public void setOrigin(FinalSyntaxNode origin) {
        this.origin = origin;
        origin.setParent(this);
    }
    public FinalSyntaxNode getVector() {
        return vector;
    }
    public void setVector(FinalSyntaxNode vector) {
        this.vector = vector;
        vector.setParent(this);
    }

    public Type getType() {
        return getOrigin().getType();
    }

    public void evaluate(){}

    public String toString() {
        return super.toString() +
                "[" + origin +
                ", " + vector +
                ']';
    }
}
