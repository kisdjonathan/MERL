package baseTypes;

import data.TypeSize;
import data.Usage;
import derivedAST.FinalSyntaxNode;
import derivedAST.Variable;

import java.util.List;

public class Storage extends FinalSyntaxNode implements BasicType {
    public String getName() {
        return "storage";
    }

    public int indexCount() {
        return Integer.MAX_VALUE;
    }

    public Variable getIndex(int i) {
        return null;
    }

    public List<Variable> getFields() {
        return null;
    }

    public Variable getField(String name) {
        return null;
    }

    public List<Function> getMethods() {
        return null;
    }

    public Function getMethod(Function signature) {
        return null;
    }

    public TypeSize getByteSize() {
        return null;
    }

    public Usage getUsage() {
        return null;
    }
}
