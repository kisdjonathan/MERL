package baseAST;

import derivedAST.FinalSyntaxNode;
import baseTypes.Tuple;
import operations.bool.And;
import operations.bool.BoolInfix;
import operations.BuiltinOperation;

import java.util.ArrayList;
import java.util.List;

//used only in the non-final AST in chained operators
public class ChainedOperator extends Operator {
    private List<String> operators = new ArrayList<>();

    public ChainedOperator(){}

    public String getName() {
        return "chain";
    }
    public boolean isChained() {
        return true;
    }

    /**
     * performs both an addOperator and an addChild
     */
    public void addChild(String op, SyntaxNode opand) {
        addChild(opand);
        addOperator(op);
    }
    /**
     * adds op at the end of the list of consecutive operators
     */
    public void addOperator(String op) {
        operators.add(op);
    }

    /**
     * true if op is an operator that is part of the chain
     */
    public boolean hasOperator(String op) {
        return operators.contains(op);
    }

    private FinalSyntaxNode getReplacementBoolOper() {
        FinalSyntaxNode prev = getChild(1).getReplacement();
        FinalSyntaxNode ret = BuiltinOperation.infix(operators.get(0), getChild(0).getReplacement(), prev);
        for(int i = 1; i < operators.size(); ++i) {
            FinalSyntaxNode temp = getChild(i + 1).getReplacement();
            ret = BuiltinOperation.infix("and", ret, BuiltinOperation.infix(operators.get(i), prev, temp));
            prev = temp;
        }
        return ret;
    }
    private FinalSyntaxNode getReplacementAssignOper() {
        FinalSyntaxNode val = getChild(size()-1).getReplacement();
        FinalSyntaxNode ret = BuiltinOperation.infix("<<", getChild(0).getReplacement(), val);
        for(int i = 1; i < operators.size(); ++i) {
            FinalSyntaxNode temp = getChild(i + 1).getReplacement();
            ret = BuiltinOperation.infix("with", ret, BuiltinOperation.infix(operators.get(i), val, temp));
        }
        return ret;
    }

    public FinalSyntaxNode getReplacement(){
        if(operators.contains("<") || operators.contains("<=") || operators.contains(">") || operators.contains(">="))
            return getReplacementBoolOper();
        else if(operators.contains(";") || operators.contains(",")) {
            return new Tuple(){{
                setParent(ChainedOperator.this.getParent());
                for(SyntaxNode child : getChildren())
                    addIndex(child);
            }};
        }
        else if(operators.contains("=")) {
            boolean allDefined = true;
            for(int i = 0; i + 1 < size(); ++i) {
                if(isUndefinedVariable(getChild(i))){
                    allDefined = false;
                    break;
                }
            }
            if(allDefined)
                return getReplacementBoolOper();
            else
                return getReplacementAssignOper();
        }
        else if(operators.contains("<<")) {
            return getReplacementAssignOper();
        }
        return null;
    }

    public boolean equals(Object other) {
        if(!(super.equals(other) && ((Operator)other).isChained() &&
                operators.size() == ((ChainedOperator)other).operators.size()))
            return false;
        for(int i = 0; i < operators.size(); ++i)
            if(!operators.get(i).equals(((ChainedOperator)other).operators.get(i)))
                return false;
        return true;
    }
}
