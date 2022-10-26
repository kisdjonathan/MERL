package baseTypes;

import data.Usage;
import derivedAST.FinalSyntaxNode;
import derivedAST.RelativeFunction;
import derivedAST.RelativeVariable;

import java.util.List;

public class Char extends Numerical{
    private short value = '\0';

    public Char(){}
    public Char(short val) {
        value = val;
    }

    public String getName() {
        return "char";
    }
    public short getValue() {
        return value;
    }

    public boolean typeEquals(FinalSyntaxNode other) {
        return getByteSize().compareTo(other.getBaseType().getByteSize()) == 0;
    }
    public boolean typeContains(FinalSyntaxNode other) {
        return getByteSize().compareTo(other.getBaseType().getByteSize()) >= 0;
    }

    protected int defaultByteSize() {
        return 1;
    }

    public FinalSyntaxNode newInstance(String s) {
        return new Char((short) Integer.parseInt(s));
    }

    public String toString() {
        return super.toString() + " " + value;
    }
}
