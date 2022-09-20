package baseTypes;

import data.TypeSize;
import data.Usage;
import derivedAST.FinalSyntaxNode;
import derivedAST.Variable;

import java.util.List;

public class Char extends FinalSyntaxNode implements BasicType{
    private boolean extended = false;
    private char value = '\0';

    public Char(){}
    public Char(char val) {
        value = val;
    }

    public String getName() {
        return "char";
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
        return 0;
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
        return new TypeSize(extended ? 2 : 1);
    }
}
