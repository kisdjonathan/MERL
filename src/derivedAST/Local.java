package derivedAST;

import baseAST.SyntaxNode;
import data.Usage;

import java.util.*;

//derivedAST.Local contains localized variables
//TODO complete
public class Local extends FinalSyntaxNode {
    private Map<String, Variable> variables = new HashMap<>();
    private Map<String, List<Function>> functions = new HashMap<>();
    private FinalSyntaxNode process = null;

    public Local(){}
    public Local(FinalSyntaxNode definition) {
        setBody(definition);
    }

    public String getName() {
        return "local";
    }
    public Usage getUsage() {
        return Usage.GROUP;
    }
    public FinalSyntaxNode getDeclaredType() {
        return process.getDeclaredType();
    }

    public void setBody(FinalSyntaxNode body) {
        process = body;
        body.setParent(this);
    }
    public void setBody(SyntaxNode body) {
        body.setParent(this);
        setBody(body.getEvaluatedReplacement());
    }
    public FinalSyntaxNode getBody() {
        return process;
    }

    public Variable getVariable(String name) {
        return variables.get(name);
    }
    public Variable putVariable(Variable value) {
        variables.put(value.getName(), value);
        return value;
    }

    public List<Function> getFunction(String name) {
        ArrayList<Function> ret = new ArrayList<>();
        if(getParent() != null)
            ret.addAll(getParent().getFunction(name));
        if(functions.containsKey(name))
            ret.addAll(functions.get(name));
        return ret;
    }
    public Function putFunction(Function value) {
        if(!functions.containsKey(value.getName()))
            functions.put(value.getName(), new ArrayList<>());
        functions.get(value.getName()).add(value);
        return value;
    }
}
