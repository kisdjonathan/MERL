package baseTypes;

import baseAST.Group;
import baseAST.SyntaxNode;
import data.TypeSize;
import data.Usage;
import derivedAST.FinalSyntaxNode;
import derivedAST.Variable;

import java.util.List;

public class Range extends FinalSyntaxNode implements BasicType {
    private FinalSyntaxNode start, stop, step = null;

    public String getName() {
        return "range";
    }
    public Usage getUsage() {
        return Usage.TYPE;
    }


    public void setStart(FinalSyntaxNode v) {
        start = v;
    }

    public void setStop(FinalSyntaxNode v) {
        stop = v;
    }

    /**
     * null step means consecutive
     */
    public void setStep(FinalSyntaxNode v) {
        step = v;
    }


    public int indexCount() {
        return 0;   //TODO
    }
    public Variable getIndex(int i) {
        return null;    //TODO
    }

    public List<Variable> getFields() {
        return null;    //TODO
    }
    public Variable getField(String name) {
        return null;    //TODO
    }

    public List<Function> getMethods() {
        return null;
    }
    public Function getMethod(Function signature) {
        return null;
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
        };
        SyntaxNode body = node.getBody();
        body.setParent(ret);
        Tuple values = Tuple.asTuple(body.getEvaluatedReplacement());

        switch (values.size()) {
            case 3:
                ret.setStep(values.getIndex(2));
            case 2:
                ret.setStop(values.getIndex(1));
                ret.setStart(values.getIndex(0));
                break;
            case 1:
                ret.setStart(new Int(0));
                ret.setStop(values.getIndex(0));
        }
        return ret;
    }
}
