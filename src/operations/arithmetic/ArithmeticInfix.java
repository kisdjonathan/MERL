package operations.arithmetic;

import baseTypes.BasicType;
import derivedAST.FinalSyntaxNode;
import operations.BuiltinOperation;

public class ArithmeticInfix extends BuiltinOperation {
    private static FinalSyntaxNode largerType(FinalSyntaxNode a, FinalSyntaxNode b) {
        BasicType t1 = a.getBaseType(), t2 = b.getBaseType();
        return t1.getByteSize().compareTo(t2.getByteSize()) > 0 ? a : b;
    }

    public ArithmeticInfix() {}
    public ArithmeticInfix(FinalSyntaxNode a, FinalSyntaxNode b) {
        setOrigin(a);
        setVector(b);
    }

    public FinalSyntaxNode getDeclaredType() {
        return largerType(getOrigin(), getVector());
    }
}
