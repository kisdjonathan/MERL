package derivedAST;

import baseAST.SyntaxNode;
import data.Type;
import data.Usage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//derivedAST.Tuple represents an ordered comma or semicolon group
public class Tuple extends FinalSyntaxNode implements Iterable<FinalSyntaxNode>{
    public static Tuple asTuple(FinalSyntaxNode node) {
        if(node.getUsage() == Usage.TUPLE)
            return (Tuple)node;
        else
            return new Tuple(){{addChild(node);}};
    }

    private List<FinalSyntaxNode> children = new ArrayList<>();
    private boolean constant = false;
    private boolean complete = false;

    public Tuple(){}
    public Tuple(List<FinalSyntaxNode> children){
        this.children = children;
    }


    public String getName() {
        return "(" + getType().toString() + ")";
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

    public boolean isConstant() {
        return constant;
    }
    public boolean isComplete() {
        return complete;
    }

    public Type getType() {
        Type ret = new Type();
        for(SyntaxNode child : children)
            ret.putComponent(((FinalSyntaxNode) child).getType());
        return ret;
    }

    public Tuple getReplacement() {
        for(int i = 0; i < children.size(); ++i)
            children.set(i, children.get(i).getReplacement());
        //TODO determine constant
        complete = true;
        return this;
    }

    public Iterator<FinalSyntaxNode> iterator() {
        return children.listIterator();
    }
}
