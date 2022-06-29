//SyntaxNode represents any general component of the program
//TODO remove commented code block after it is no longer needed
public abstract class SyntaxNode {
    public abstract String getName();
    public abstract Usage getUsage();

    private SyntaxNode parent;

    public SyntaxNode getParent() {
        return parent;
    }
    public void setParent(SyntaxNode parent) {
        this.parent = parent;
    }

    public boolean isEmpty() {
        return true;
    }
    public int size() {
        return 0;
    }

    public void removeVariable(String name) {
        parent.removeVariable(name);
    }
    public Identifier getVariable(String name) {
        return parent.getVariable(name);
    }
    public void putVariable(String name, Variable value) {
        parent.putVariable(name, value);
    }


    public boolean equals(Usage usage) {
        return getUsage() == usage;
    }
    public boolean equals(String name) {
        return getName().equals(name);
    }
    public boolean equals(SyntaxNode other) {
        return other == this;
    }

    public boolean isConstant() {
        return true;
    }
    public boolean isComplete() {
        return true;
    }

    public abstract Type getType();

    public String toString() {
        return getUsage() + " " + getName();
    }


//    private String name = null;
//    private Usage usage = null;
//    private SyntaxNode parent = null;
//    private Boolean complete = false;
//    private List<SyntaxNode> children = new ArrayList<>();
//
//    public SyntaxNode(){}
//    public SyntaxNode(String name){this.name = name;}
//    public SyntaxNode(String name, Usage usage) {
//        this.name = name;
//        this.usage = usage;
//    }
//
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Usage getUsage() {
//        return usage;
//    }
//    public void setUsage(Usage usage) {
//        this.usage = usage;
//    }
//
//    public SyntaxNode getParent() {
//        return parent;
//    }
//
//    public List<SyntaxNode> getChildren() {
//        return children;
//    }
//    public int size() {
//        return children.size();
//    }
//
//    public SyntaxNode getChild(int index) {
//        return children.get(index);
//    }
//    public SyntaxNode setChild(int index, SyntaxNode val) {
//        val.parent = this;
//        return children.set(index, val);
//    }
//
//    public SyntaxNode removeChild(int index) {
//        SyntaxNode ret = children.remove(index);
//        ret.parent = null;
//        return ret;
//    }
//    public void addChild(int index, SyntaxNode child) {
//        child.parent = this;
//        children.add(index, child);
//    }
//    public void addChild(SyntaxNode child) {
//        child.parent = this;
//        children.add(child);
//    }
//
//    public Variable getVariable(String name) {
//        return parent.getVariable(name);
//    }
//    public void putVariable(String name, Variable value) {
//        parent.putVariable(name, value);
//    }
//
//    public boolean isConstant() {
//        return false;   //for subclass use
//    }
//
//    public boolean equals(Usage usage) {
//        return this.usage == usage;
//    }
//    public boolean equals(Usage usage, String name) {
//        return this.usage == usage && (this.name == name || this.name.equals(name));
//    }
//    public boolean equals(SyntaxNode other) {
//        return this == other;
//    }
//
//    public Boolean isComplete() {
//        return complete;
//    }
//    public void setComplete(Boolean complete) {
//        this.complete = complete;
//    }
}
