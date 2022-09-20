package operations.controls;

import derivedAST.FinalSyntaxNode;
import operations.BuiltinOperation;

public class ControlStructure extends BuiltinOperation {
    //TODO store the structure as a tree where the main body is the root, and the nodes are else/nelse clauses and the
    // children are associated with true/break case and the false/finish cases of every node

    public void addElse(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        //TODO
    }
    public void addNelse(FinalSyntaxNode origin, FinalSyntaxNode vector) {
        //TODO
    }
}
