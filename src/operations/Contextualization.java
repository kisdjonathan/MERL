package operations;

import baseTypes.Function;
import derivedAST.FinalSyntaxNode;
import derivedAST.Variable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//derivedAST.Contextualization simply makes the fields of a variable available to its children
//TODO complete
public class Contextualization extends BuiltinOperation {
    private Map<String, Variable> loadedVariables = new HashMap<>();
    private Map<String, List<Function>> loadedFunctions = new HashMap<>();

    public Contextualization() {

    }

    public Contextualization(FinalSyntaxNode context, FinalSyntaxNode body) {
        //TODO
    }

    public void evaluate(){

    }

    public Variable getVariable(String name) {
        if(loadedVariables.containsKey(name))
            return loadedVariables.get(name);
        return super.getVariable(name);
    }

    public List<Function> getFunction(String name) {
        List<Function> ret = super.getFunction(name);
        if(loadedFunctions.containsKey(name))
            ret.addAll(loadedFunctions.get(name));
        return ret;
    }

}
