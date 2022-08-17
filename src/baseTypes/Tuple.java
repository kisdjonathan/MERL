package baseTypes;

import baseAST.SyntaxNode;
import data.Usage;
import derivedAST.ExternalFunction;
import derivedAST.ExternalVariable;
import derivedAST.FinalSyntaxNode;
import operations.Index;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;

//TODO special type here

//Tuple represents an ordered comma or semicolon group
public class Tuple extends FinalSyntaxNode implements BasicType, Iterable<FinalSyntaxNode>{
    public static Tuple asTuple(FinalSyntaxNode node) {
        if(node.getUsage() == Usage.TUPLE)
            return (Tuple)node;
        else
            return new Tuple(){{addChild(node);}};
    }

    private List<FinalSyntaxNode> children = new ArrayList<>();
    private boolean constant = false;

    public Tuple(){}
    public Tuple(List<FinalSyntaxNode> children){
        this.children = children;
    }

    public String getName() {
        StringBuilder ret = new StringBuilder("(");
        for(FinalSyntaxNode child : children) {
            if (ret.length() > 1)
                ret.append(",");
            ret.append(child.getName());
        }
        ret.append(")");

        return ret.toString();
    }
    public Usage getUsage() {
        return Usage.TUPLE;
    }

    public boolean isEmpty() {
        return children.isEmpty();
    }
    public int size() {
        return children.size();
    }

    public FinalSyntaxNode getChild(int index) {
        return children.get(index);
    }
    public FinalSyntaxNode setChild(int index, FinalSyntaxNode val) {
        val.setParent(this);
        return children.set(index, val);
    }
    public FinalSyntaxNode removeChild(int index) {
        FinalSyntaxNode ret = children.remove(index);
        ret.setParent(null);
        return ret;
    }
    public void addChild(int index, FinalSyntaxNode child) {
        child.setParent(this);
        children.add(index, child);
    }
    public void addChild(FinalSyntaxNode child) {
        child.setParent(this);
        children.add(child);
    }
    public void addChild(SyntaxNode child) {
        child.setParent(this);
        addChild(child.getEvaluatedReplacement());
    }

    public boolean isConstant() {
        return constant;
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

    public List<ExternalVariable> getFields() {
        return null;
    }
    public List<ExternalFunction> getFunctions() {
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
}
