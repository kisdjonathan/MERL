package derivedAST;

import baseTypes.Signature;
import baseTypes.Tuple;
import data.Usage;
import operations.BuiltinOperation;

//TODO L allow functions to have fields as static members (fields are currently not supported)
public class Function extends Local {
    private String name;
    private Signature type;

    public Function(String name) {
        this.name = name;
        type = new Signature();
    }

    /**
     * use null for rets if undeclared
     */
    public Function(String label, Tuple args, Tuple rets) {
        this.name = name;
        setDeclaredType(new Signature(args, rets));
    }

    public String getName() {
        return type.getName();
    }
    public Usage getUsage() {
        return Usage.FUNCTION;
    }

    public Signature getDeclaredType() {
        return type;
    }
    public void setDeclaredType(FinalSyntaxNode t) {
        type = (Signature) t;
        if(t == null)
            return;

        for(FinalSyntaxNode arg : type.getArgs()) {
            putVariable(arg.getName());
            //TODO L support inline definition of argument type
        }
        if(type.getRets() != null)
            for(FinalSyntaxNode ret : type.getRets()) {
                putVariable(ret.getName());
                //TODO L support inline definition of return types
            }
    }

    /**
     * returns true if body has a return statement
     * returns false otherwise
     */
    private boolean iterateForReturn(FinalSyntaxNode level) {
        if(level.equals(Usage.OPERATOR)) {
            if(level.equals("return")) {
                type.setRets(((BuiltinOperation) level).getOrigin());
                return true;
            }
            BuiltinOperation boper = (BuiltinOperation) level;
            if(iterateForReturn(boper.getOrigin()))
                return true;
            if(boper.size() > 1 && iterateForReturn(boper.getVector()))
                return true;
        }
        else if(level.equals(Usage.TUPLE)) {
            for(FinalSyntaxNode child : (Tuple)level)
                if(iterateForReturn(child))
                    return true;
        }
        return false;
    }

    public FinalSyntaxNode evaluated() {
        if(type.getRets() == null)
            if(iterateForReturn(getBody()))
                setDeclaredType(getBody());
        return super.evaluated();
    }
}
