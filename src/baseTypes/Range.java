package baseTypes;

import baseAST.SyntaxNode;
import data.TypeSize;
import data.Usage;
import derivedAST.FinalSyntaxNode;
import derivedAST.Variable;

import java.util.List;

public class Range extends FinalSyntaxNode implements BasicType {
    public String getName() {
        return "range";
    }
    public Usage getUsage() {
        return Usage.TYPE;
    }

    public int indexCount() {
        return 0;   //TODO
    }
    public Variable getIndex(int i) {
        return null;    //TODO
    }

    public List<Variable> getFields() {
        return null;    //TODO
    }
    public Variable getField(String name) {
        return null;    //TODO
    }

    public List<Function> getMethods() {
        return null;
    }
    public Function getMethod(Function signature) {
        return null;
    }

    public TypeSize getByteSize() {
        return null;    //TODO
    }

    public static Range decode(SyntaxNode node) {
        //TODO
    }
}
