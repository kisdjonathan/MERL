package derivedAST;

import baseAST.SyntaxNode;
import baseTypes.BasicType;
import baseTypes.Function;
import baseTypes.Pair;
import baseTypes.Tuple;
import operations.Call;

import java.util.Collection;
import java.util.List;

public abstract class FinalSyntaxNode extends SyntaxNode {
    private FinalSyntaxNode type = null;
    public FinalSyntaxNode getType() {
        return type;
    }
    public void setType(FinalSyntaxNode dependency) {
        type = dependency;
    }


    public boolean isComplete() {
        return true;
    }
    public boolean isConstant() {
        return true;
    }


    public FinalSyntaxNode getReplacement(){return this;}

    //call as soon after getReplacement as possible on the replacement
    public FinalSyntaxNode evaluated(){
        return this;
    }


    public BasicType getBaseType() {
        return type.getBaseType();
    }
    public boolean typeEquals(FinalSyntaxNode other) {
        return getType().typeEquals(other);
    }
    //TODO L add flexible auto-casting
//    protected FinalSyntaxNode typeConvert(FinalSyntaxNode self, FinalSyntaxNode other, FinalSyntaxNode context) {
//        if(self.typeEquals(other))
//            return other;
//        //direct conversion
//        Function ret = context.getFunction("convert", Tuple.asTuple(this), Tuple.asTuple(other));
//        if(ret != null)
//            return new Call(ret, Tuple.asTuple(self));
//        return null;
//    }
//    //returns a FinalSyntaxNode with the type of other but the value of self, or null if not possible
//    public FinalSyntaxNode typeConvert(FinalSyntaxNode other, FinalSyntaxNode context) {
//        return typeConvert(this, other, context);
//    }
    public boolean typeContains(FinalSyntaxNode other, FinalSyntaxNode context) {
        //TODO L
        //return typeConvert(other, context) != null;
        return typeEquals(other);
    }

}
