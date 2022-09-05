package baseTypes;

import data.Usage;
import derivedAST.FinalSyntaxNode;
import derivedAST.Variable;

import java.util.List;

//TODO
public class Str extends FinalSyntaxNode implements BasicType{

    public String getName() {
        return "str";
    }
    public Usage getUsage() {
        return Usage.TYPE;
    }


    public BasicType getBaseType() {
        return this;
    }
    public boolean typeEquals(FinalSyntaxNode other) {
        return false;
    }

    public boolean isIndexed() {
        return true;
    }
    public boolean isFielded() {
        return false;
    }

    public void assertField(String name, FinalSyntaxNode t) {

    }

    public FinalSyntaxNode getField(String name) {
        return null;
    }
    public List<Variable> getFields() {
        return null;
    }
    public List<Function> getFunctions() {
        return null;
    }
}