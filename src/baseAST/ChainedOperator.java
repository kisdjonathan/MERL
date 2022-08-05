package baseAST;

import derivedAST.FinalSyntaxNode;
import derivedAST.Tuple;
import operations.BooleanInfix;
import operations.ComparisonInfix;

import java.util.ArrayList;
import java.util.List;

//used only in the non-final AST in chained operators
public class ChainedOperator extends Operator {
    private List<String> operators = new ArrayList<>();

    public ChainedOperator(){}

    public boolean isChained() {
        return true;
    }

    public void addChild(String op, SyntaxNode opand) {
        addChild(opand);
        addOperator(op);
    }
    public void addOperator(String op) {
        operators.add(op);
    }

    public FinalSyntaxNode getReplacement(){
        if(operators.contains("<") || operators.contains("<=") || operators.contains(">") || operators.contains(">=")){
            FinalSyntaxNode prev = getChild(1).getReplacement();
            FinalSyntaxNode ret = new ComparisonInfix(operators.get(0), getChild(0).getReplacement(), prev);
            for(int i = 1; i < operators.size(); ++i) {
                FinalSyntaxNode temp = getChild(i + 1).getReplacement();
                ret = new BooleanInfix("and", ret, new ComparisonInfix(operators.get(i), prev, temp));
                prev = temp;
            }
            return ret;
        }
        else if(operators.contains(";") || operators.contains(",")) {
            return new Tuple(){{
                for(SyntaxNode child : getChildren()) {
                    FinalSyntaxNode temp = child.getReplacement();
                    addChild(temp);
                    temp.evaluate();
                }
            }};
        }
        else if(operators.contains("=")) {
            //TODO
        }
        else if(operators.contains("<<")) {
            //TODO
        }
        return null;
    }
}
