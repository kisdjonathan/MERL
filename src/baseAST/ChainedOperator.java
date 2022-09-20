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

    public FinalSyntaxNode getReplacement(){
        if(operators.contains("<") || operators.contains("<=") || operators.contains(">") || operators.contains(">=")){
            FinalSyntaxNode prev = getChild(1).getReplacement();
            FinalSyntaxNode ret = BuiltinOperation.infix(operators.get(0), getChild(0).getReplacement(), prev);
            for(int i = 1; i < operators.size(); ++i) {
                FinalSyntaxNode temp = getChild(i + 1).getReplacement();
                ret = BuiltinOperation.infix("and", ret, BuiltinOperation.infix(operators.get(i), prev, temp));
                prev = temp;
            }
            return ret;
        }
        else if(operators.contains(";") || operators.contains(",")) {
            return new Tuple(){{
                for(SyntaxNode child : getChildren())
                    addIndex(child);
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
