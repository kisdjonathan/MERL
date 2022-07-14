package baseAST;

import data.*;

//baseAST.SyntaxNode represents any general component of the program
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
}
