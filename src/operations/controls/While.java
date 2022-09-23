package operations.controls;

import baseAST.SyntaxNode;
import derivedAST.FinalSyntaxNode;

public class While extends ControlStructure {
    public While(FinalSyntaxNode condition, FinalSyntaxNode body) {
        setBase(condition, body);
    }
    public While(SyntaxNode condition, SyntaxNode body) {
        setBase(condition, body);
    }
}
