package operations;

import derivedAST.FinalSyntaxNode;

public class Copy extends BuiltinOperation {
    public Copy(FinalSyntaxNode v) {
        setFirst(v);
    }

    public String getName() {
        return "copy";
    }
}
