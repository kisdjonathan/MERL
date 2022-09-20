package baseAST;

import baseTypes.Tuple;
import data.*;
import derivedAST.FinalSyntaxNode;
import baseTypes.Function;
import derivedAST.Variable;
import operations.Call;
import operations.Cast;

import java.util.List;

//baseAST.SyntaxNode represents any general component of the program
public abstract class SyntaxNode {
    /**
     * returns the name of this that uniquely defines its value with respect to its usage
     * names for FinalSyntaxNodes should be spelt out
     **/
    public abstract String getName();
    /**
     * returns a value that uniquely determines the use of this
     **/
    public abstract Usage getUsage();

    /**
     * returns the SyntaxNode for which this is a child of
     **/
    private SyntaxNode parent;
    public SyntaxNode getParent() {
        return parent;
    }
    public void setParent(SyntaxNode parent) {
        this.parent = parent;
    }

    /**
     * returns whether this has any children
     **/
    public boolean isEmpty() {
        return size() == 0;
    }
    /**
     * returns the number of children this has
     **/
    public int size() {
        return 0;
    }

    /**
     * returns true if this is a FinalSyntaxNode
     **/
    public boolean isComplete() {
        return false;
    }

    /**
     * returns the variable of name from the most recent local which it occurs in
     **/
    public Variable getVariable(String name) {
        return parent.getVariable(name);
    }
    /**
     * puts the variable of name in the most recent local
     * the value should not already exist
     * returns the variable
     **/
    public Variable putVariable(Variable value) {
        return parent.putVariable(value);
    }
    public Variable putVariable(String name) {
        return putVariable(new Variable(name));
    }

    /**
     * returns the function of signature from the most recent local which it occurs in
     **/
    public Function getFunction(Function signature) {
        List<Function> possibilities = getFunction(signature.getName());
        for(int i = possibilities.size()-1; i >= 0; --i) {
            //TODO L flexible casting
            if(possibilities.get(i).typeStrictEquals(signature))
                return possibilities.get(i);
        }
        return null;
    }
    /**
     * returns all functions of name accessible from this
     **/
    public List<Function> getFunction(String name) {return parent.getFunction(name);}
    /**
     * puts the function of name in the most recent local
     * the value should not already exist
     * returns the Function
     **/
    public Function putFunction(Function value) {
        return parent.putFunction(value);
    }
    public Function putFunction(String name) {
        return putFunction(new Function(name));
    }

    /**
     * returns the FinalSyntaxNode equivalent of this
     * the return will have the same parent as this
     * getReplaced will invalidate this
     **/
    public abstract FinalSyntaxNode getReplacement();
    /**
     * returns the FinalSyntaxNode equivalent of this after evaluating it in place of this
     * i.e. this is a declaration, but the replacement will not declare the variable until evaluate is called, which this function does
     * the return will have the same parent as this
     * getReplaced will invalidate this
     **/
    public FinalSyntaxNode getEvaluatedReplacement() {
        FinalSyntaxNode ret = getReplacement();
        return ret.evaluated();
    }

    /**
     * returns true if the specified value(s) match the properties of this
     **/
    public boolean equals(Usage usage) {
        return getUsage() == usage;
    }
    public boolean equals(String name) {
        return getName().equals(name);
    }
    public boolean equals(Object other) {
        return other instanceof SyntaxNode sother && sother.equals(getUsage(), getName());
    }
    public boolean equals(Usage usage, String name) {
        return equals(usage) && equals(name);
    }

    /**
     * debug use
     **/
    public String toString() {
        return getUsage() + " " + getName();
    }
}
