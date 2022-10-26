package baseTypes;

import derivedAST.FinalSyntaxNode;

public class Int extends Numerical{
    private long value;

    public Int(){
        value = 0;
    }
    public Int(long val){
        value = val;
    }

    public String getName() {
        return "int";
    }
    public long getValue() {
        return value;
    }

    public boolean typeEquals(FinalSyntaxNode other) {
        return getByteSize().compareTo(other.getBaseType().getByteSize()) == 0;
    }
    public boolean typeContains(FinalSyntaxNode other) {
        return getByteSize().compareTo(other.getBaseType().getByteSize()) >= 0;
    }

    protected int defaultByteSize() {
        return 4;
    }

    public FinalSyntaxNode newInstance(String s) {
        return new Int(Integer.parseInt(s));
    }

    public String toString() {
        return super.toString() + " " + value;
    }
}
