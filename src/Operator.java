import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

//Operator represents an operation between children
public class Operator extends SyntaxNode {
    private static List<Set<String>> precedences = new ArrayList<>();
    private static int indexOf(String op) {
        for(int index = 0; index < precedences.size(); ++index)
            if(precedences.get(index).contains(op))
                return index;
        return -1;
    }

    private String name = null;
    private Type type = null;
    private List<SyntaxNode> children = new ArrayList<>();
    private boolean constant = false;
    private boolean complete = false;

    public Operator(){}
    public Operator(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }
    public Usage getUsage() {
        return Usage.OPERATOR;
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

    public void setType(Type type) {
        this.type = type;
    }
    public Type getType() {
        return type;
    }

    public void setComplete(boolean v) {
        complete = v;
    }

    public int compareTo(String other) {
        return indexOf(getName()) - indexOf(other);
    }
}
