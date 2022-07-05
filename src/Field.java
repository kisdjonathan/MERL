public class Field extends Operator{
    public Field(SyntaxNode parent, SyntaxNode field){
        super(" ");
        addChild(parent);
        addChild(field);
    }
}
