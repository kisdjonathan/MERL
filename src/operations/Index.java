package operations;

import baseAST.Literal;
import baseTypes.BasicType;
import baseTypes.Int;
import derivedAST.FinalSyntaxNode;

//builtin indexing provided for array/list types only
public class Index extends BuiltinOperation {
    public Index(FinalSyntaxNode ref, FinalSyntaxNode pos) {
        setOrigin(ref);
        setVector(pos);
    }
    public Index(FinalSyntaxNode ref, int pos) {
        this(ref, new Literal(String.valueOf(pos), new Int()));
    }

    public FinalSyntaxNode getType() {
        //return getOrigin().getType().getIndexedType();
        return null;    //TODO
    }

    public String getName() {
        return "index";
    }
}
