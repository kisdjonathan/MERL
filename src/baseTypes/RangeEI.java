package baseTypes;

import derivedAST.FinalSyntaxNode;
import operations.arithmetic.Add;
import operations.arithmetic.Multiply;

public class RangeEI extends Range {
    public RangeEI(){}
    public RangeEI(int start, int stop){
        super(start, stop);
    }
    public RangeEI(int start, int stop, int step){
        super(start, stop, step);
    }

    public RangeEI(FinalSyntaxNode start, FinalSyntaxNode end) {
        //TODO
    }
    //TODO remaining constructors for floats and finalsyntaxnodes

    public FinalSyntaxNode getIndex(int i) {
        return new Add(getStart(), new Multiply(new Int(i + 1), getStep()));
    }
}
