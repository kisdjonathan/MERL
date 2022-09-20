package baseTypes;

import data.TypeSize;
import data.Usage;
import derivedAST.FinalSyntaxNode;
import derivedAST.Variable;

import java.util.List;

//TODO
public class Str extends FinalSyntaxNode implements BasicType{
    private boolean extended = false;
    private String value;

    public Str() {
        value = "";
    }
    public Str(String val) {
        value = val;
    }

    public String getName() {
        return "str";
    }
    public Usage getUsage() {
        return Usage.TYPE;
    }

    public boolean isLong() {
        return extended;
    }
    public void setLong(boolean v) {
        extended = v;
    }

    public BasicType getBaseType() {
        return this;
    }
    public boolean typeEquals(FinalSyntaxNode other) {
        return getByteSize().compareTo(other.getBaseType().getByteSize()) == 0;
    }
    public boolean typeContains(FinalSyntaxNode other) {
        return getByteSize().compareTo(other.getBaseType().getByteSize()) >= 0;
    }

    public int indexCount() {
        return Integer.MAX_VALUE;
    }
    public Variable getIndex(int i) {
        //TODO
    }

    public List<Variable> getFields() {
        //TODO
    }
    public Variable getField(String name) {
        //TODO
    }
    public List<Function> getMethods() {
        //TODO
    }
    public Function getMethod(Function signature) {
        //TODO
    }

    public TypeSize getByteSize() {
        return new TypeSize();
    }
}
