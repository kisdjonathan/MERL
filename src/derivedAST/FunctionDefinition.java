package derivedAST;

import baseAST.SyntaxNode;
import data.Signature;
import data.TupleType;
import data.Type;
import data.Usage;

import java.util.HashMap;
import java.util.Map;

//derivedAST.FunctionDefinition stores the definition of a function and makes the parameters and child declarations local to it
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
        TupleType tparam = new TupleType(), tret = new TupleType();
        int i = 0;for(SyntaxNode param : param)
            tparam.putComponent(param.getType());
        int j = 0;for(SyntaxNode ret : ret)
            tret.putComponent(ret.getType());
        return new Signature("func", tret, tparam);
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}
