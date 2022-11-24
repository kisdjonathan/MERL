package derivedAST;

/**
 * used to store a variable that is a field of a value
 * requires reference value to be provided at some point
 */
public class RelativeVariable extends Variable{
    private FinalSyntaxNode reference;
    private FinalSyntaxNode offset;

    /**
     * use "__unnamed__" for variables without name (ie index)
     */
    public RelativeVariable(String name, FinalSyntaxNode type) {
        super(name, type);
    }
    public RelativeVariable(String name) {
        super(name);
    }

    public void setReference(FinalSyntaxNode reference) {
        this.reference = reference;
    }

    /**
     * used for index
     */
    public void setOffset(FinalSyntaxNode offset) {
        this.offset = offset;
    }
}
