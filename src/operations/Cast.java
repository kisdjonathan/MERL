package operations;

import baseAST.SyntaxNode;
import baseTypes.Function;
import baseTypes.Tuple;
import derivedAST.FinalSyntaxNode;
import derivedAST.*;
import baseTypes.Pair;

import java.util.Collection;

public class Cast extends BuiltinOperation {
    public Cast(FinalSyntaxNode from, FinalSyntaxNode to) {
        setOrigin(from);
        setVector(to);
    }

    public Cast(FinalSyntaxNode from, FinalSyntaxNode to, SyntaxNode parent) {
        this(from, to);
        setParent(parent);
    }

    public FinalSyntaxNode getType() {
        return getOrigin();
    }
    public void setType(FinalSyntaxNode t) {
        setOrigin(t);
    }

    public String getName() {
        return "cast";
    }

    //returns SyntaxNode that produces a converted form of source from ref to dest when evaluated
    public FinalSyntaxNode getReplacement() {
        Function directConversion = getFunction("convert", Tuple.asTuple(getOrigin()), Tuple.asTuple(getVector()));
        if(directConversion != null) {
            return new Call(directConversion, getOrigin()).getReplacement();
            //cases where {a,b,c}-->{x,y,z} is defined
        }

        if(getOrigin().equals(getVector())) {
            return new Copy(getOrigin()).getReplacement();
            //cases where {a,b,c}-->{a,b,c}, where types a==a..., regardless of whether each position is named or
            //where {a A, b B, c C}-->{c C, b B, a A}, regardless of order
        }

        Variable singleRet = new Variable("ret"); singleRet.setType(getVector());
        LambdaFunctionDefinition piecewiseConversion = new LambdaFunctionDefinition(new Tuple(), new Tuple(){{addChild(singleRet);}});
        Tuple body = new Tuple();
        Collection<FinalSyntaxNode.SplitPair> parts = ref.splitMatchWith(dest);
        for(FinalSyntaxNode.SplitPair part : parts) {
            Cast test = new Cast(new Index(getOrigin(), part.referencePosition), part.destinationType, this);
            if(!test.isDefined()) {
                piecewiseConversion = null;
                break;
            }
            body.addChild(new Assign(new Index(singleRet, part.destinationPosition), test));
            //cases where {a,b,c}-->{x,y,z}, where a-->x... are defined, regardless of whether each position is named or
            //where {a A, b B, c C}-->{z C, y B, x A}, regardless of order
        }
        if(!parts.isEmpty() && piecewiseConversion != null) {
            piecewiseConversion.setBody(body);
            return new Call(piecewiseConversion, new Tuple());
        }

        if(ref.scatter().equals(dest.scatter())) {
            return null;    //TODO
            //cases where foundational primitive composition matches
        }

        //when all fails
        return null;
    }

    public boolean isDefined() {
        return this.getReplacement() != null;
    }
}
