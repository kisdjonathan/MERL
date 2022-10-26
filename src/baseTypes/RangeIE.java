package baseTypes;

import derivedAST.FinalSyntaxNode;
import operations.arithmetic.Add;
import operations.arithmetic.Multiply;

public class RangeIE extends Range {
    public RangeIE(){}
    public RangeIE(int start, int stop){
        super(start, stop);
    }
    public RangeIE(int start, int stop, int step){
        super(start, stop, step);
    }

    public RangeIE(FinalSyntaxNode start, FinalSyntaxNode end) {
        //TODO
    }
    //TODO remaining constructors for floats and finalsyntaxnodes

    public FinalSyntaxNode getIndex(int i) {
        return new Add(getStart(), new Multiply(new Int(i), getStep()));
    }
}
