package baseTypes;

import derivedAST.FinalSyntaxNode;
import derivedAST.Function;
import derivedAST.RelativeFunction;
import derivedAST.RelativeVariable;

import java.util.List;

//TODO define storage (ie does it only store values of the same type...)
public class Storage extends BasicType {
    public String getName() {
        return "storage";
    }

    public int indexCount() {
        return Integer.MAX_VALUE;
    }

    public RelativeVariable getIndex(int i) {
        return null;    //TODO
    }

    public List<FinalSyntaxNode> getFields() {
        return null;    //TODO
    }

    public RelativeVariable getField(String name) {
        return null;    //TODO
    }

    public List<Function> getMethods() {
        return null;    //TODO
    }

    public TypeSize getByteSize() {
        return null;
    }

    public FinalSyntaxNode newInstance(String s) {
        throw new Error("unable to create new storage instance as a literal (from " + s + ")");
    }
}
