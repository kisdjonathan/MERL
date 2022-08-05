package derivedAST;

import baseAST.SyntaxNode;
import data.*;

//derivedAST.FunctionDefinition stores the definition of a function and makes the parameters and child declarations local to it
//TODO make parameters locally available
public class FunctionDefinition extends Local {
    private Tuple param = null, ret = null;
    private boolean complete = false, constant = false;

    public FunctionDefinition(){}
    public FunctionDefinition(Tuple param, Tuple ret, Tuple definition){
        super(definition);
        this.param = param;
        param.setParent(this);
        this.ret = ret;
        ret.setParent(this);
    }
    public FunctionDefinition(Tuple param, Tuple ret){
        this(param, ret, null);
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
    public void setParam(Tuple param) {
        this.param = param;
    }
    public Tuple getReturn() {
        return ret;
    }
    public void setReturn(Tuple ret) {
        this.ret = ret;
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
        for(SyntaxNode param : param)
            tparam.putComponent(((FinalSyntaxNode)param).getType());
        for(SyntaxNode ret : ret)
            tret.putComponent(((FinalSyntaxNode)ret).getType());
        return new Signature("func", tret, tparam);
    }

    public void addComponent(FinalSyntaxNode component) {
        ((Tuple)getBody()).addChild(component);
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public FinalSyntaxNode getReplacement() {
        setComplete(true);
        param = param.getReplacement();
        ret = ret.getReplacement();
        return super.getReplacement();
    }
}
