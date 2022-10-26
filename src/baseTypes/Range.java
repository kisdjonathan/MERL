package baseTypes;

import baseAST.ChainedOperator;
import baseAST.Group;
import baseAST.Operator;
import baseAST.SyntaxNode;
import data.Usage;
import derivedAST.*;

import java.util.List;

public abstract class Range extends BasicType {
    private FinalSyntaxNode start, stop, step = null;   //TODO compare between values of literals

    public Range(){}
    public Range(int start, int stop) {
        this.start = new Int(start);
        this.stop = new Int(stop);
    }
    public Range(int start, int stop, int step) {
        this.start = new Int(start);
        this.stop = new Int(stop);
        this.step = new Int(step);
    }

    public String getName() {
        return "range";
    }
    public Usage getUsage() {
        return Usage.TYPE;
    }


    public FinalSyntaxNode getStart() {
        return start;
    }
    public void setStart(FinalSyntaxNode v) {
        start = v;
    }
    public void setStart(SyntaxNode v) {
        v.setParent(this);
        setStart(v.getEvaluatedReplacement());
    }

    public FinalSyntaxNode getStop() {
        return stop;
    }
    public void setStop(FinalSyntaxNode v) {
        stop = v;
    }
    public void setStop(SyntaxNode v) {
        v.setParent(this);
        setStop(v.getEvaluatedReplacement());
    }

    /**
     * null step means consecutive
     */
    public FinalSyntaxNode getStep() {
        return step;
    }
    public void setStep(FinalSyntaxNode v) {
        step = v;
    }
    public void setStep(SyntaxNode v) {
        v.setParent(this);
        setStep(v.getEvaluatedReplacement());
    }

    /**
     * range indices are start + i * step at [i] if start is inclusive, otherwise start + (1 + i) * step
     * if step is not given, it is assumed to be 0
     */
    public int indexCount() {
        return Integer.MAX_VALUE;
    }

    public List<FinalSyntaxNode> getFields() {
        return null;    //TODO
    }
    public RelativeVariable getField(String name) {
        return null;    //TODO
    }

    public List<Function> getMethods() {
        return null;    //TODO
    }

    public TypeSize getByteSize() {
        return null;    //TODO
    }

    public static Range decode(Group node) {
        Range ret = switch (node.getName()){
            case "()" -> new RangeEE();
            case "(]" -> new RangeEI();
            case "[)" -> new RangeIE();
            case "[]" -> new RangeII();
            default -> throw new Error("invalid range " + node.getName());
        };
        SyntaxNode body = node.getBody();
        if(body.equals(Usage.OPERATOR) &&
                ((Operator)body).isChained() && (
                    ((ChainedOperator)body).hasOperator(",") ||
                    ((ChainedOperator)body).hasOperator(";")
                )){
            ret.setStart(((Operator)body).getChild(0));
            ret.setStop(((Operator)body).getChild(1));
            ret.setStep(((Operator)body).getChild(2));
        }
        else if (body.equals(Usage.OPERATOR) &&
                (body.equals(",") || body.equals(";"))
        ){
            ret.setStart(((Operator)body).getChild(0));
            ret.setStop(((Operator)body).getChild(1));
        }
        else {
            ret.setStop(body);
        }

        return ret;
    }
    public FinalSyntaxNode newInstance(String s) {
        throw new Error("unable to create new range instance as a literal (from " + s + ")");
    }
}
