package baseTypes;

import derivedAST.FinalSyntaxNode;
import derivedAST.Function;
import derivedAST.RelativeFunction;
import derivedAST.RelativeVariable;

import java.util.List;

//TODO
public class Str extends Storage{
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
    public Char getCharType() {
        return isLong() ? new Char(){{setLong(true);}} : new Char();
    }

    public int indexCount() {
        return Integer.MAX_VALUE;
    }
    public RelativeVariable getIndex(int i) {
        RelativeVariable ret = new RelativeVariable("", getCharType());
        int offset = getCharType().getByteSize().forceInt() * i + 4;    //TODO L + 4 is to account for .length; find better alternative to adding 4
        ret.setOffset(new Int(offset));
        return ret;
    }

    public List<FinalSyntaxNode> getFields() {
        //TODO length
        return null;
    }
    public RelativeVariable getField(String name) {
        //TODO length
        return null;
    }
    public List<Function> getMethods() {
        //TODO
        return null;
    }
    public RelativeFunction getMethod(Function signature) {
        //TODO
        return null;
    }

    public TypeSize getByteSize() {
        return new TypeSize();  //TODO
    }
    public FinalSyntaxNode newInstance(String s) {
        return new Str(s);
    }

    public String toString() {
        return super.toString() + " \"" + value + "\"";
    }
}
