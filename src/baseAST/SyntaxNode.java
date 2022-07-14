package baseAST;

import data.*;

//baseAST.SyntaxNode represents any general component of the program
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
    public boolean hasVariable(String name) {
        return getVariable(name) != null;
    }
    public Variable getVariable(String name) {
        return parent.getVariable(name);
    }
    public void putVariable(String name, Variable value) {
        parent.putVariable(name, value);
    }
    public void putVariable(String name) {
        putVariable(name, new Variable());
    }
    public boolean hasFunction(Signature signature) {
        return getFunction(signature) != null;
    }
    public Type.TypeConversion getFunctionWithConversion(Signature signature) {
        return parent.getFunctionWithConversion(signature);
    }
    public Function getFunction(Signature signature) {
        return getFunctionWithConversion(signature).conversion;
    }
    public void putFunction(String name, Function value) {
        putFunction(name, value);
    }
    public void putFunction(String name) {
        putFunction(name, new Function());
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
//    private data.Usage usage = null;
//    private baseAST.SyntaxNode parent = null;
//    private Boolean complete = false;
//    private List<baseAST.SyntaxNode> children = new ArrayList<>();
//
//    public baseAST.SyntaxNode(){}
//    public baseAST.SyntaxNode(String name){this.name = name;}
//    public baseAST.SyntaxNode(String name, data.Usage usage) {
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
//    public data.Usage getUsage() {
//        return usage;
//    }
//    public void setUsage(data.Usage usage) {
//        this.usage = usage;
//    }
//
//    public baseAST.SyntaxNode getParent() {
//        return parent;
//    }
//
//    public List<baseAST.SyntaxNode> getChildren() {
//        return children;
//    }
//    public int size() {
//        return children.size();
//    }
//
//    public baseAST.SyntaxNode getChild(int index) {
//        return children.get(index);
//    }
//    public baseAST.SyntaxNode setChild(int index, baseAST.SyntaxNode val) {
//        val.parent = this;
//        return children.set(index, val);
//    }
//
//    public baseAST.SyntaxNode removeChild(int index) {
//        baseAST.SyntaxNode ret = children.remove(index);
//        ret.parent = null;
//        return ret;
//    }
//    public void addChild(int index, baseAST.SyntaxNode child) {
//        child.parent = this;
//        children.add(index, child);
//    }
//    public void addChild(baseAST.SyntaxNode child) {
//        child.parent = this;
//        children.add(child);
//    }
//
//    public data.Variable getVariable(String name) {
//        return parent.getVariable(name);
//    }
//    public void putVariable(String name, data.Variable value) {
//        parent.putVariable(name, value);
//    }
//
//    public boolean isConstant() {
//        return false;   //for subclass use
//    }
//
//    public boolean equals(data.Usage usage) {
//        return this.usage == usage;
//    }
//    public boolean equals(data.Usage usage, String name) {
//        return this.usage == usage && (this.name == name || this.name.equals(name));
//    }
//    public boolean equals(baseAST.SyntaxNode other) {
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
