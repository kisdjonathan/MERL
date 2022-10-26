package operations.control;

import baseAST.SyntaxNode;
import derivedAST.FinalSyntaxNode;

public class Repeat extends ControlStructure {
    public Repeat(FinalSyntaxNode count, FinalSyntaxNode body) {
        setBase(count, body);
    }
    public Repeat(SyntaxNode count, SyntaxNode body, SyntaxNode parent) {
        setParent(parent);
        setBase(count, body);
    }
}
