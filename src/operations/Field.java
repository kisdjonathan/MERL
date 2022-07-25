package operations;

import baseAST.Operator;
import baseAST.SyntaxNode;
import data.StructureType;
import data.Type;
import data.Usage;

public class Field extends SyntaxNode {
    SyntaxNode origin, vector;

    public Field(SyntaxNode parent, SyntaxNode field){
        origin = parent;
        vector = field;
    }

    public String getName() {
        return " ";
    }
    public Usage getUsage() {
        return Usage.FIELD;
    }

    public Type getType() {
        return origin.getType().getComponent(vector.getName());
    }

    public SyntaxNode getOrigin() {
        return origin;
    }
    public SyntaxNode getVector() {
        return vector;
    }
}
