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

    //true for FinalSyntaxNode
    public boolean isComplete() {
        return false;
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
    public void putVariable(Variable value) {
        parent.putVariable(value);
    }
    public void putVariable(String name) {
        putVariable(new Variable(name));
    }

    public boolean hasFunction(String name, Tuple args, Tuple rets) {
        return getFunction(name, args, rets) != null;
    }
    public Function getFunction(String name, Tuple args, Tuple rets) {
        List<Function> possibilities = getFunction(name);
        for(int i = possibilities.size()-1; i >= 0; --i) {
            //TODO L flexible casting
            if(possibilities.get(i).getArgs().typeEquals(args) && possibilities.get(i).getRets().typeEquals(rets))
                return possibilities.get(i);
        }
        return null;
    }
    public List<Function> getFunction(String name) {return parent.getFunction(name);}
    public void putFunction(Function value) {
        parent.putFunction(value);
    }
    public void putFunction(String name) {
        putFunction(new Function(name));
    }

    //current instance will be invalidated after a call to getReplaced
    public abstract FinalSyntaxNode getReplacement();
    public FinalSyntaxNode getEvaluatedReplacement() {
        FinalSyntaxNode ret = getReplacement();
        return ret.evaluated();
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
    public boolean equals(Usage usage, String name) {
        return equals(usage) && equals(name);
    }

    public String toString() {
        return getUsage() + " " + getName();
    }
}
