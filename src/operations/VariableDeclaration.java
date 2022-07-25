package operations;

import baseAST.Operator;
import baseAST.SyntaxNode;
import derivedAST.Tuple;

//derivedAST.VariableDeclaration stores the declaration of a variable and handles adding the variable to local scope
//TODO handle adding the variable(s) to local scope
public class VariableDeclaration extends Operator {
    public VariableDeclaration(){
        super("declare");
    }
    public VariableDeclaration(Tuple names, Tuple values){
        super("declare");
        super.addChild(names);
        super.addChild(values);
    }

    public void setParent(SyntaxNode parent) {
        super.setParent(parent);
    }
}
