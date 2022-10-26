package baseTypes;

import baseAST.SyntaxNode;
import data.Usage;
import derivedAST.FinalSyntaxNode;
import derivedAST.RelativeFunction;
import derivedAST.RelativeVariable;
import derivedAST.Variable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//TODO special type here

//Tuple represents an ordered comma or semicolon group
public class Tuple extends BasicType implements Iterable<FinalSyntaxNode>{
    /**
     * returns node if node is a tuple, otherwise creates a tuple containing node and returns that
     **/
    public static Tuple asTuple(FinalSyntaxNode node) {
        return node.getUsage() == Usage.TUPLE ? (Tuple)node : new Tuple(){{addIndex(node);}};
    }

    /**
     * elements of the tuple, in order
     **/
    private List<FinalSyntaxNode> children = new ArrayList<>();

    public Tuple(){}
    public Tuple(List<FinalSyntaxNode> children){
        this.children = children;
    }

    public String getName() {
        return "tuple";
    }
    public Usage getUsage() {
        return Usage.TUPLE;
    }

    public int size() {
        return children.size();
    }


    /**
     * operations on children
     * the parent of the affected children is modified
     **/
    public FinalSyntaxNode getIndex(int i) {
        return children.get(i);
    }
    public FinalSyntaxNode setIndex(int index, FinalSyntaxNode val) {
        val.setParent(this);
        return children.set(index, val);
    }
    public FinalSyntaxNode removeIndex(int index) {
        FinalSyntaxNode ret = children.remove(index);
        ret.setParent(null);
        return ret;
    }
    public void addIndex(int index, FinalSyntaxNode child) {
        child.setParent(this);
        children.add(index, child);
    }
    public void addIndex(FinalSyntaxNode child) {
        child.setParent(this);
        children.add(child);
    }
    public void addIndex(SyntaxNode child) {
        child.setParent(this);
        addIndex(child.getEvaluatedReplacement());
    }
    public int indexCount() {
        return size();
    }

    public boolean isConstant() {
        for(FinalSyntaxNode child : children)
            if(!child.isConstant())
                return false;
        return true;
    }
    public boolean isComplete() {
        return true;
    }

    public boolean typeEquals(FinalSyntaxNode t) {
        for(FinalSyntaxNode child : children)
            if(!child.typeEquals(t))
                return false;
        return true;
    }

    public TypeSize getByteSize() {
        TypeSize ret = new TypeSize(0);
        for(FinalSyntaxNode child : children)
            ret = TypeSize.add(ret, child.getBaseType().getByteSize());
        return ret;
    }
    public FinalSyntaxNode newInstance(String s) {
        throw new Error("unable to create new tuple instance as a literal (from " + s + ")");
    }


    public Iterator<FinalSyntaxNode> iterator() {
        return children.listIterator();
    }

    public String toString() {
        StringBuilder ret = new StringBuilder("(");
        for(FinalSyntaxNode child : children) {
            if (ret.length() > 1)
                ret.append(",");
            ret.append(child.toString());
        }
        ret.append(")");
        return ret.toString();
    }
}
