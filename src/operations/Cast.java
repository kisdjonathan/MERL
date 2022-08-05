package operations;

import derivedAST.FinalSyntaxNode;
import baseAST.SyntaxNode;
import derivedAST.Function;
import derivedAST.LambdaFunctionDefinition;
import data.Signature;
import data.Type;
import derivedAST.Tuple;
import derivedAST.Variable;

import java.util.Collection;

public class Cast extends BuiltinOperation {
    private Type ref, dest;

    public Cast(FinalSyntaxNode from, Type to) {
        setOrigin(from);
        ref = from.getType();
        dest = to;
    }

    public Cast(Type from, Type to) {
        ref = from;
        dest = to;
    }

    public Cast(FinalSyntaxNode from, Type to, SyntaxNode parent) {
        this(from, to);
        setParent(parent);
    }

    public Type getType() {
        return dest;
    }
    public void setType(Type t) {
        dest = t;
    }

    public String getName() {
        return "cast";
    }

    //returns SyntaxNode that produces a converted form of source from ref to dest when evaluated
    public FinalSyntaxNode getReplacement() {
        Function directConversion = getFunction(new Signature(dest, ref));
        if(directConversion != null) {
            return new Call(directConversion, getOrigin()).getReplacement();
            //cases where {a,b,c}-->{x,y,z} is defined
        }

        if(ref.equals(dest)) {
            return new Copy(getOrigin()).getReplacement();
            //cases where {a,b,c}-->{a,b,c}, where types a==a..., regardless of whether each position is named or
            //where {a A, b B, c C}-->{c C, b B, a A}, regardless of order
        }

        Variable singleRet = new Variable("ret"); singleRet.setType(dest);
        LambdaFunctionDefinition piecewiseConversion = new LambdaFunctionDefinition(new Tuple(), new Tuple(){{addChild(singleRet);}});
        Tuple body = new Tuple();
        Collection<Type.SplitPair> parts = ref.splitMatchWith(dest);
        for(Type.SplitPair part : parts) {
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
