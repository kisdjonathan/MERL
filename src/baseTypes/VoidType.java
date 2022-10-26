package baseTypes;

import data.Usage;
import derivedAST.FinalSyntaxNode;
import derivedAST.RelativeFunction;
import derivedAST.RelativeVariable;

import java.util.List;

public class VoidType extends BasicType {
    public String getName() {
        return "void";
    }

    public TypeSize getByteSize() {
        return new TypeSize(0);
    }
    public FinalSyntaxNode newInstance(String s) {
        return new VoidType();
    }
}
