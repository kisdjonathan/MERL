package operations.control;

import baseAST.SyntaxNode;
import derivedAST.FinalSyntaxNode;

public class If extends ControlStructure {
    public If(FinalSyntaxNode condition, FinalSyntaxNode body) {
        setBase(condition, body);
    }
    public If(SyntaxNode condition, SyntaxNode body, SyntaxNode parent) {
        setParent(parent);
        setBase(condition, body);
    }
}
