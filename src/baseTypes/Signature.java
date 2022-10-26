package baseTypes;

import baseAST.SyntaxNode;
import data.Usage;
import derivedAST.FinalSyntaxNode;

public class Signature extends BasicType{
    private Tuple args = null, rets = null;

    public Signature() {}
    public Signature(Tuple args, Tuple rets) {
        this.args = args;
        this.rets = rets;
    }

    public String getName() {
        return "signature";
    }


    public Tuple getArgs() {
        return args;
    }
    public void setArgs(FinalSyntaxNode first) {
        args = Tuple.asTuple(first);
        args.setParent(this);
    }
    public void setArgs(SyntaxNode first) {
        first.setParent(this);
        setArgs(first.getEvaluatedReplacement());
    }

    public Tuple getRets() {
        return rets;
    }
    public void setRets(FinalSyntaxNode second) {
        rets = Tuple.asTuple(second);
        rets.setParent(this);
    }
    public void setRets(SyntaxNode second) {
        second.setParent(this);
        setRets(second.getEvaluatedReplacement());
    }


    public boolean typeEquals(FinalSyntaxNode other) {
        if(!(other instanceof Signature fother))
            return false;
        return args.typeEquals(fother.getArgs()) && rets.typeEquals(fother.getRets());
    }

    public TypeSize getByteSize() {
        return new TypeSize();
    }
}
