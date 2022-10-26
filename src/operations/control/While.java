package operations.control;

import baseAST.SyntaxNode;
import derivedAST.FinalSyntaxNode;

public class While extends ControlStructure {
    public While(FinalSyntaxNode condition, FinalSyntaxNode body) {
        setBase(condition, body);
    }
    public While(SyntaxNode condition, SyntaxNode body, SyntaxNode parent) {
        setParent(parent);
        setBase(condition, body);
    }
}
