package derivedAST;

import baseAST.SyntaxNode;
import data.Type;
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

    public SyntaxNode getProcess() {
        return process;
    }

    public String getName() {
        return "local";
    }
    public Usage getUsage() {
        return Usage.GROUP;
    }
    public Type getType() {
        return ((FinalSyntaxNode)process).getType();
    }

    public Variable getVariable(String name) {
        return variables.get(name);
    }
    public void putVariable(String name, Variable value) {
        variables.put(name, value);
    }

    public void setBody(FinalSyntaxNode body) {
        process = body;
        body.setParent(this);
    }
    public FinalSyntaxNode getBody() {
        return process;
    }

    public List<Function> getFunction(String name) {
        ArrayList<Function> ret = new ArrayList<>();
        if(getParent() != null)
            ret.addAll(getParent().getFunction(name));
        ret.addAll(functions.get(name));
        return ret;
    }

    public void putFunction(String name, Function value) {
        if(!functions.containsKey(name))
            functions.put(name, new ArrayList<>());
        functions.get(name).add(value);
    }
}
