package baseTypes;

import data.Usage;
import derivedAST.FinalSyntaxNode;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

public class Pair extends Tuple{
    public Pair(){}
    public Pair(FinalSyntaxNode v1, FinalSyntaxNode v2){
        super(List.of(v1, v2));
    }

    public FinalSyntaxNode getFirst() {
        return getChild(0);
    }
    public FinalSyntaxNode setFirst(FinalSyntaxNode first) {
        return setChild(0, first);
    }

    public FinalSyntaxNode getSecond() {
        return getChild(1);
    }
    public FinalSyntaxNode setSecond(FinalSyntaxNode second) {
        return setChild(1, second);
    }

    public String getName() {
        return "<"+ getFirst().getName() + "," + getSecond().getName() +">";
    }

    public Usage getUsage() {
        return Usage.TUPLE;
    }

    public FinalSyntaxNode getField(String name) {
        //TODO first and second
        return null;
    }
}
