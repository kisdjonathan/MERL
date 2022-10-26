package operations;

import baseTypes.Signature;
import baseTypes.Tuple;
import derivedAST.FinalSyntaxNode;
import derivedAST.Function;
import derivedAST.Variable;

public class Assign extends BuiltinOperation {
    public Assign(){}
    public Assign(FinalSyntaxNode dest, FinalSyntaxNode value){
        setOrigin(dest);
        setVector(value);
    }

    public void evaluate() {
        super.evaluate();
        Variable dest = getVariable(getOrigin().getName());
        FinalSyntaxNode source = getVector();
        if(dest.getDeclaredType().equals("inferred"))
            dest.setDeclaredType(source);
        else if(!dest.getDeclaredType().equals(source)) {
            Function conv = getFunction("convert", new Signature(Tuple.asTuple(source.getDeclaredType()), Tuple.asTuple(dest.getDeclaredType())));
            if(conv != null)
                setVector(new Call(conv, source));
            else if(source.typeConvertsTo(dest))
                setVector(new Cast(source, dest));
            else
                throw new Error("No conversion exists from " + source + " to " + dest);
        }
    }

    public String getName() {
        return "assign";
    }
}
