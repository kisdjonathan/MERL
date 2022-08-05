package operations;

import derivedAST.FinalSyntaxNode;
import baseAST.Literal;
import data.Type;

//builtin indexing provided for array/list types only
public class Index extends BuiltinOperation {
    public Index(FinalSyntaxNode ref, FinalSyntaxNode pos) {
        setOrigin(ref);
        setVector(pos);
    }
    public Index(FinalSyntaxNode ref, int pos) {
        this(ref, new Literal(String.valueOf(pos), Type.INT));
    }

    public Type getType() {
        return getOrigin().getType().getComponent(0);
    }

    public String getName() {
        return "index";
    }
}
