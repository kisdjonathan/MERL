package baseTypes;

import data.Usage;
import derivedAST.FinalSyntaxNode;
import derivedAST.Function;
import derivedAST.RelativeFunction;
import derivedAST.RelativeVariable;
import operations.arithmetic.Add;
import operations.arithmetic.Multiply;

import java.util.List;

public class TypeSize extends BasicType implements Comparable{
    public static TypeSize add(TypeSize a, TypeSize b) {
        if(a.isConstant() && b.isConstant())
            return new TypeSize(a.forceInt() + b.forceInt());
        else
            return new TypeSize(new Add(a.getValue(), b.getValue()));
    }
    public static TypeSize multiply(TypeSize a, TypeSize b) {
        if(a.isConstant() && b.isConstant())
            return new TypeSize(a.forceInt() * b.forceInt());
        else
            return new TypeSize(new Multiply(a.getValue(), b.getValue()));
    }

    private boolean constant;
    private int constantValue;
    private FinalSyntaxNode nonConstantValue;

    /**
     * defaults to size of pointer
     */
    public TypeSize() {
        constantValue = 8;
        constant = true;
    }
    public TypeSize(int bytes) {
        constantValue = bytes;
        constant = true;
    }
    public TypeSize(FinalSyntaxNode bytes) {
        nonConstantValue = bytes;
        constant = false;
    }

    public String getName() {
        return "size";
    }
    public Usage getUsage() {
        return Usage.TYPE;
    }

    public boolean isConstant() {
        return constant;
    }
    public int forceInt() {
        return constantValue;
    }

    public int indexCount() {
        return 0;
    }
    public RelativeVariable getIndex(int i) {
        return null;
    }
    public List<FinalSyntaxNode> getFields() {
        return null;
    }
    public RelativeVariable getField(String name) {
        return null;
    }
    public List<Function> getMethods() {
        return null; //TODO
    }

    public FinalSyntaxNode getValue() {
        return isConstant() ? new Int(constantValue) : nonConstantValue;
    }
    public TypeSize getByteSize() {
        return new TypeSize();
    }
    public FinalSyntaxNode newInstance(String s) {
        return new TypeSize(Integer.parseInt(s));
    }

    public int compareTo(Object o) {
        if(o instanceof Integer)
            return 0;   //TODO
        else if(o instanceof TypeSize)
            return 0;   //TODO
        else if(o instanceof BasicType)
            return 0;   //TODO
        throw new Error("TypeSize can not be compared to " + o);
    }
}
