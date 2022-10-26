package derivedAST;

import baseAST.SyntaxNode;
import baseTypes.BasicType;
import data.Usage;

public class Field extends FinalSyntaxNode {
    private FinalSyntaxNode origin = null, vector = null;

    public Field() {}
    public Field(SyntaxNode parent, SyntaxNode field){
        setOrigin(parent);
        //TODO load fields in parent
        setVector(field);
    }
    public Field(FinalSyntaxNode parent, FinalSyntaxNode field){
        setOrigin(parent);
        setVector(field);
    }

    public void setOrigin(FinalSyntaxNode origin) {
        this.origin = origin;
        origin.setParent(this);
    }
    public void setOrigin(SyntaxNode origin) {
        origin.setParent(this);
        setOrigin(origin.getEvaluatedReplacement());
    }
    public FinalSyntaxNode getOrigin() {
        return origin;
    }
    public void setVector(FinalSyntaxNode vector) {
        this.vector = vector;
        vector.setParent(this);
    }
    public void setVector(SyntaxNode vector) {
        vector.setParent(this);
        setVector(vector.getEvaluatedReplacement());
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

    public BasicType getBaseType() {
        return origin.getBaseType().getField(vector.getName()).getBaseType();
    }
    public boolean typeEquals(FinalSyntaxNode other) {
        return origin.getBaseType().getField(vector.getName()).typeEquals(other);
    }

    public String toString() {
        return super.toString() + "[" +
                origin + ", " + vector +
                "]";
    }
}
