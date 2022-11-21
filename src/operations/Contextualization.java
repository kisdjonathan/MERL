package operations;

import baseAST.SyntaxNode;
import baseTypes.Signature;
import derivedAST.Function;
import derivedAST.FinalSyntaxNode;
import derivedAST.Variable;

import java.util.ArrayList;
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
    public Contextualization(SyntaxNode context, SyntaxNode body) {
        setOrigin(context);
        setVector(body);
    }
    public Contextualization(FinalSyntaxNode context, FinalSyntaxNode body) {
        setOrigin(context);
        setVector(body);
    }

    public void setOrigin(FinalSyntaxNode origin) {
        super.setVector(origin);
        for(FinalSyntaxNode field : origin.getBaseType().getFields()) {
            loadedVariables.put(field.getName(), (Variable) field);  //TODO L: potentially erroneous for read only variables
        }
        for(Function field : origin.getBaseType().getMethods()) {
            if(!loadedFunctions.containsKey(field.getName()))
                loadedFunctions.put(field.getName(), new ArrayList<>());
            loadedFunctions.get(field.getName()).add(field);
        }
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
