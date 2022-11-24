package derivedAST;

import baseTypes.Signature;
import baseTypes.Tuple;

/**
 * used to store a function that is a field of a value
 * requires reference value to be provided at some point
 */
public class RelativeFunction extends Function {
    private FinalSyntaxNode reference;
    private FinalSyntaxNode offset;

    public RelativeFunction(String name) {
        super(name);
    }
    public RelativeFunction(String name, Signature sig) {
        super(name, sig);
    }
    public RelativeFunction(String name, Tuple args, Tuple rets) {
        super(name, args, rets);
    }

    public void setReference(FinalSyntaxNode reference) {
        this.reference = reference;
    }

    public void setOffset(FinalSyntaxNode offset) {
        this.offset = offset;
    }
}
