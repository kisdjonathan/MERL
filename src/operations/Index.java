package operations;

import baseAST.Literal;
import baseTypes.BasicType;
import derivedAST.FinalSyntaxNode;

//builtin indexing provided for array/list types only
public class Index extends BuiltinOperation {
    public Index(FinalSyntaxNode ref, FinalSyntaxNode pos) {
        setFirst(ref);
        setSecond(pos);
    }
    public Index(FinalSyntaxNode ref, int pos) {
        this(ref, new Literal(String.valueOf(pos), BasicType.INT));
    }

    public FinalSyntaxNode getType() {
        return getFirst().getType().getComponent(0);
    }

    public String getName() {
        return "index";
    }
}
