import java.util.HashMap;
import java.util.Map;

//FunctionDefinition stores the definition of a function and makes the parameters and child declarations local to it
//TODO make parameters locally available
public class FunctionDefinition extends Local {
    private Tuple param = null, ret = null;
    private boolean complete = false, constant = false;

    public FunctionDefinition(Tuple param, Tuple ret, SyntaxNode definition){
        super(definition);
        this.param = param;
        this.ret = ret;
    }

    public String getName() {
        return String.format("{%s}(%s)", ret.getType().toString(), param.getType().toString());
    }
    public Usage getUsage() {
        return Usage.LOCALIZATION;
    }


    public Tuple getParam() {
        return param;
    }
    public Tuple getRet() {
        return ret;
    }


    public boolean isConstant() {
        return constant;
    }
    public void setConstant(boolean v) {
        constant = v;
    }

    public boolean isComplete() {
        return complete;
    }

    public Type getType() {
        Type tparam = new Type(), tret = new Type();
        int i = 0;for(SyntaxNode param : param)
            tparam.putComponent(String.valueOf(i++), param.getType());
        int j = 0;for(SyntaxNode ret : ret)
            tret.putComponent(String.valueOf(j++), ret.getType());
        Map<String, Type> fcomp = new HashMap<>(); fcomp.put("parameter", tparam); fcomp.put("return", tret);
        return new Type("func", fcomp, true);
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}