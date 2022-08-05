package derivedAST;

import baseAST.SyntaxNode;
import data.Type;
import data.Usage;

public class Field extends FinalSyntaxNode {
    private FinalSyntaxNode origin, vector;

    public Field(FinalSyntaxNode parent, FinalSyntaxNode field){
        origin = parent;
        vector = field;
    }

    public FinalSyntaxNode getOrigin() {
        return origin;
    }
    public FinalSyntaxNode getVector() {
        return vector;
    }

    public String getName() {
        return "field";
    }
    public Usage getUsage() {
        return Usage.FIELD;
    }
    public Type getType() {
        return origin.getType().getComponent(vector.getName());
    }

    public String toString() {
        return super.toString() + "[" +
                origin + ", " + vector +
                "]";
    }
}
