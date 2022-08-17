package baseAST;

import baseTypes.BasicType;
import baseTypes.Tuple;
import derivedAST.FinalSyntaxNode;
import data.Usage;
import derivedAST.*;
import operations.Call;
import operations.Index;

public class Consecutive extends SyntaxNode {
    private SyntaxNode origin = null, vector = null;

    public Consecutive(SyntaxNode first, SyntaxNode second){
        origin = first;
        vector = second;

        origin.setParent(this);
        vector.setParent(this);
    }

    public String getName() {
        return " ";
    }
    public Usage getUsage() {
        return Usage.FIELD;
    }

    public SyntaxNode getOrigin() {
        return origin;
    }
    public SyntaxNode getVector() {
        return vector;
    }

    //true if: IDENTIFIER (..)
    //TODO L allow for structures on functions
    public boolean isInferredFunction() {
        return vector.equals(Usage.GROUP, "()") && origin.equals(Usage.IDENTIFIER);
    }

    //true if: IDENTIFIER {..} (..)
    public boolean isTypedFunction() {
        return vector.equals(Usage.GROUP, "()") &&
                origin.equals(Usage.FIELD) &&
                ((Consecutive)origin).isStructure();
    }

    //true if: ANY (..)
    public boolean isCall() {
        return vector.equals(Usage.GROUP, "()");
    }

    //true if: IDENTIFIER {..}
    public boolean isStructure() {
        return vector.equals(Usage.GROUP, "{}") && origin.equals(Usage.IDENTIFIER);
    }

    //true if: (..) {..}
    public boolean isInferredLambda() {
        return vector.equals(Usage.GROUP, "{}") && origin.equals(Usage.GROUP, "()");
    }

    //true if {..}(..){..}
    public boolean isTypedLambda(){
        return vector.equals(Usage.GROUP, "{}") &&
                origin.equals(Usage.FIELD) &&
                ((Consecutive)origin).getOrigin().equals(Usage.GROUP, "{}") &&
                ((Consecutive)origin).getVector().equals(Usage.GROUP, "()");
    }

    //true if ANY [...]
    public boolean isIndex() {
        return vector.equals(Usage.GROUP, "[]");
    }

    //true if (...)SUFFIX
    public boolean isGroupedLiteral() {
        return origin.equals(Usage.GROUP) && vector.equals(Usage.IDENTIFIER) && BasicType.isSuffix(vector.getName());
    }

    public FinalSyntaxNode getReplacement() {
        if(isCall())
            return new Call(origin.getReplacement(), ((Group)vector).getBody().getReplacement());
        else if(isIndex())
            return new Index(origin.getReplacement(), ((Group)vector).getBody().getReplacement());
        else if(isTypedLambda())
            return new LambdaFunctionDefinition(
                    Tuple.asTuple(((Consecutive)origin).getVector().getReplacement()),
                    Tuple.asTuple(((Consecutive)origin).getOrigin().getReplacement()),
                    Tuple.asTuple(vector.getReplacement())
            );
        else if(isInferredLambda())
            return new LambdaFunctionDefinition(
                    Tuple.asTuple(origin.getReplacement())
                    , null,
                    Tuple.asTuple(vector.getReplacement())
            );
        else if(isGroupedLiteral()) {
            return origin.getReplacement();//TODO L
        }

        return new Field(origin.getReplacement(), vector.getReplacement());
    }
    //TODO L in the future, add functionality for return type and argument type overloading (including determining inferred types)
    //TODO L ie in Operator, where this is called, replace with a completely separate function which evaluates the body first

    public String toString() {
        return super.toString() + "[" +
                origin + ", " + vector +
                "]";
    }
}
