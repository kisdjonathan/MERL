package operations.controls;

import baseAST.SyntaxNode;
import derivedAST.FinalSyntaxNode;

public class Repeat extends ControlStructure {
    public Repeat(FinalSyntaxNode count, FinalSyntaxNode body) {
        setBase(count, body);
    }
    public Repeat(SyntaxNode count, SyntaxNode body) {
        setBase(count, body);
    }
}
