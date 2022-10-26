package operations;

import baseAST.SyntaxNode;
import baseTypes.BasicType;
import derivedAST.FinalSyntaxNode;

/**
 * Cast represents cases of conversions where from != to and from->to is not overridden
 */
public class Cast extends BuiltinOperation {
    public Cast(FinalSyntaxNode from, FinalSyntaxNode to) {
        setOrigin(from);
        setVector(to);
    }
    public Cast(FinalSyntaxNode from, FinalSyntaxNode to, SyntaxNode parent) {
        this(from, to);
        setParent(parent);
    }

    public String getName() {
        return "cast";
    }

    public BasicType getBaseType() {
        return getVector().getBaseType();
    }
    public boolean typeEquals(FinalSyntaxNode other) {
        return getVector().typeEquals(other);
    }
}
