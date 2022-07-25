package operations;

import baseAST.Operator;
import baseAST.SyntaxNode;
import data.Function;
import data.LambdaFunction;
import data.Signature;
import data.Type;

import java.util.Collection;
import java.util.List;

//TODO implement flexible casting
public class Cast extends Operator {
    private SyntaxNode source;
    private Type dest;

    public Cast(SyntaxNode from, Type to) {
        source = from;
        dest = to;
    }

    public Cast(SyntaxNode from, Type to, SyntaxNode parent) {
        this(from, to);
        setParent(parent);
    }

    public Type getType() {
        return dest;
    }

    public Function getConversion() {
        if(source.getType().equals(dest)) {
            return source.getType().directConvert(dest);
            //cases where {a,b,c}-->{a,b,c}, where types a==a..., regardless of whether each position is named or
            //where {a A, b B, c C}-->{c C, b B, a A}, regardless of order
        }

        Function directConversion = getFunction(new Signature(dest, source.getType()));
        if(directConversion != null) {
            return directConversion;
            //cases where {a,b,c}-->{x,y,z} is defined
        }

        LambdaFunction piecewiseConversion = new LambdaFunction();
        Collection<Type.SplitPair> parts = source.getType().splitMatchWith(dest);
        for(Type.SplitPair part : parts) {
            Cast test = new Cast(new Index(source, part.referencePosition), part.destinationType, this);
            if(!test.isDefined()) {
                piecewiseConversion = null;
                break;
            }
            piecewiseConversion.putComponent(test);
            //cases where {a,b,c}-->{x,y,z}, where a-->x... are defined, regardless of whether each position is named or
            //where {a A, b B, c C}-->{z C, y B, x A}, regardless of order
        }
        if(!parts.isEmpty() && piecewiseConversion != null)
            return piecewiseConversion;

        if(source.getType().scatter().equals(dest.scatter())) {
            return dest.collectConvert(source.getType().scatter());
            //cases where foundational primitive composition matches
        }

        //when all fails
        return null;
    }

    public boolean isDefined() {
        return getConversion() != null;
    }

    private void generateConversion(){
    }
}
