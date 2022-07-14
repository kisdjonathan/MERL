package derivedAST;

import baseAST.SyntaxNode;
import data.Type;
import data.Usage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//derivedAST.Tuple represents an ordered comma or semicolon group
public class Tuple extends SyntaxNode implements Iterable<SyntaxNode>{
    private Type type = null;
    private List<SyntaxNode> children = new ArrayList<>();
    private boolean constant = false;
    private boolean complete = false;

    public Tuple(){}
    public Tuple(List<SyntaxNode> children){
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

    public SyntaxNode getChild(int index) {
        return children.get(index);
    }
    public SyntaxNode setChild(int index, SyntaxNode val) {
        val.setParent(this);
        return children.set(index, val);
    }

    public SyntaxNode removeChild(int index) {
        SyntaxNode ret = children.remove(index);
        ret.setParent(null);
        return ret;
    }
    public void addChild(int index, SyntaxNode child) {
        child.setParent(this);
        children.add(index, child);
    }
    public void addChild(SyntaxNode child) {
        child.setParent(this);
        children.add(child);
    }

    public boolean isConstant() {
        return constant;
    }
    public void setConstant(boolean v) {
        constant = v;
    }

    public boolean isComplete() {
        return complete;
    }
    public void setComplete(boolean v) {
        complete = v;
    }

    public void setType(Type type) {
        this.type = type;
    }
    public Type getType() {
        return type;
    }

    public Iterator<SyntaxNode> iterator() {
        return children.listIterator();
    }
}
