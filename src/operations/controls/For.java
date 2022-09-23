package operations.controls;

import baseAST.SyntaxNode;
import baseTypes.Tuple;
import derivedAST.FinalSyntaxNode;
import derivedAST.Variable;

import java.util.ArrayList;
import java.util.List;

public class For extends ControlStructure {
    List<Variable> variables = new ArrayList<>();

    /**
     * do not use
     */
    public For(FinalSyntaxNode iterationVariables, FinalSyntaxNode iterableVariable, FinalSyntaxNode body) {
        iterableVariable.setParent(this);
        for(FinalSyntaxNode node : Tuple.asTuple(iterationVariables)) {
            node.setParent(this);
            variables.add((Variable) node.evaluated());   //TODO L potentially erroneous
        }
        setBase(iterableVariable, body);
    }

    /**
     * formatted as such:
     *  for iterationVariables in iterableVariable:
     *      body
     */
    public For(SyntaxNode iterationVariables, SyntaxNode iterableVariable, SyntaxNode body) {
        iterationVariables.setParent(this);
        for(FinalSyntaxNode node : Tuple.asTuple(iterableVariable.getEvaluatedReplacement())){
            node.setParent(this);
            variables.add((Variable) node.evaluated());   //TODO L potentially erroneous
        }
        setBase(iterableVariable, body);
    }
}
