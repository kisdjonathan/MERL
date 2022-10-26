package baseTypes;

import data.Usage;
import derivedAST.FinalSyntaxNode;
import derivedAST.RelativeFunction;
import derivedAST.RelativeVariable;

import java.util.List;

public class Float extends Numerical{
    private double value = 0;

    public Float(){}
    public Float(double val) {
        value = val;
    }

    public String getName() {
        return "float";
    }
    public double getValue() {
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
        return new Float(Double.parseDouble(s));
    }

    public String toString() {
        return super.toString() + " " + value;
    }
}
