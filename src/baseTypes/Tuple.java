package baseTypes;

import baseAST.SyntaxNode;
import data.Usage;
import derivedAST.FinalSyntaxNode;
import derivedAST.Variable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//TODO special type here

//Tuple represents an ordered comma or semicolon group
public class Tuple extends FinalSyntaxNode implements BasicType, Iterable<FinalSyntaxNode>{
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
     * the parent of affected children are modified
     **/
    public Variable getIndex(int index) {
        return children.get(index);
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
    public BasicType getBaseType() {
        return this;
    }

    public boolean isIndexed() {
        return true;
    }
    public boolean isFielded() {
        return false;
    }
    public void assertField(String name, FinalSyntaxNode t) {
        throw new IllegalAccessError("unable to access field " + name + " in tuple " + getName());
    }
    public FinalSyntaxNode getField(String name) {
        return null;
    }

    public List<Variable> getFields() {
        return null;
    }
    public List<Function> getFunctions() {
        return null;
    }
//    protected FinalSyntaxNode typeConvert(FinalSyntaxNode self, FinalSyntaxNode other, FinalSyntaxNode context) {
//        FinalSyntaxNode ret = super.typeConvert(self, other, context);
//        if(ret != null)
//            return ret;
//
//
//    }

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
