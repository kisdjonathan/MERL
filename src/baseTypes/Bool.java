package baseTypes;

import data.Usage;
import derivedAST.FinalSyntaxNode;
import derivedAST.RelativeFunction;
import derivedAST.RelativeVariable;

import java.util.List;

public class Bool extends BasicType {
    private boolean value = false;

    public Bool(){}
    public Bool(boolean val) {
        value = val;
    }

    public String getName() {
        return "bool";
    }

    public boolean typeEquals(FinalSyntaxNode other) {
        return getByteSize().compareTo(other.getBaseType().getByteSize()) == 0;
    }
    public boolean typeContains(FinalSyntaxNode other) {
        return getByteSize().compareTo(other.getBaseType().getByteSize()) >= 0;
    }

    public TypeSize getByteSize() {
        return new TypeSize(1);
    }
    public FinalSyntaxNode newInstance(String s) {
        return new Bool(Integer.parseInt(s) != 0);
    }

    public String toString() {
        return super.toString() + " " + value;
    }
}
