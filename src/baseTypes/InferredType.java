package baseTypes;

import data.Usage;
import derivedAST.FinalSyntaxNode;
import derivedAST.Variable;

import java.util.List;

public class InferredType extends FinalSyntaxNode implements BasicType{

    public String getName() {
        return "custom";
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
        return ;
    }   //TODO when is this used? if after it is evaluated with the rest of context, do stuff with assertField
    public boolean isFielded() {
        return ;
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
