package baseAST;

import data.*;
import derivedAST.FinalSyntaxNode;
import derivedAST.Function;
import derivedAST.Variable;
import operations.Cast;

import java.util.List;

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
        putVariable(name, new Variable(name));
    }
    public boolean hasFunction(Signature signature) {
        return getFunction(signature) != null;
    }
    public Function getFunction(Signature signature) {
        List<Function> possibilities = getFunction(signature.getName());
        int distance = Integer.MAX_VALUE;   //MAX = no matching, 0 = exact signature, 1 = conversion required
        Function current = null;
        for(int i = possibilities.size()-1; i >= 0; --i) {
            if(possibilities.get(i).getType().equals(signature))
                return possibilities.get(i);
            Cast test = new Cast(possibilities.get(i), signature);
            if(distance > 1 && test.isDefined()) {
                distance = 1;
                current = possibilities.get(i); //TODO implement conversion inline
            }
        }
        return current;
    }
    public List<Function> getFunction(String name) {return parent.getFunction(name);}
    public void putFunction(String name, Function value) {
        parent.putFunction(name, value);
    }
    public void putFunction(String name) {
        putFunction(name, new Function(name));
    }

    //current instance will be invalidated after a call to getReplaced
    public abstract FinalSyntaxNode getReplacement();

    public boolean equals(Usage usage) {
        return getUsage() == usage;
    }
    public boolean equals(String name) {
        return getName().equals(name);
    }
    public boolean equals(SyntaxNode other) {
        return other == this;
    }
    public boolean equals(Usage usage, String name) {
        return equals(usage) && equals(name);
    }

    public boolean isConstant() {
        return true;
    }
    public boolean isComplete() {
        return false;
    }

    public String toString() {
        return getUsage() + " " + getName();
    }
}
