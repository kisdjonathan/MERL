package baseTypes;

import baseAST.SyntaxNode;
import baseTypes.BasicType;
import baseTypes.Pair;
import data.Usage;
import derivedAST.*;
import operations.Call;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

//TODO L allow functions to have fields as static members (fields are currently not supported)
public class Function extends Local implements BasicType {
    private String name;
    private Tuple args = null, rets = null;

    public Function(String name) {
        this.name = name;
    }
    public Function(String name, Tuple args, Tuple rets) {
        this(name);
        setArgs(args);
        setRets(rets);
    }

    public String getName() {
        return name + "("+ args.getName() + "){" + rets.getName() +"}";
    }
    public Usage getUsage() {
        return Usage.IDENTIFIER;
    }

    public Tuple getArgs() {
        return args;
    }
    public void setArgs(FinalSyntaxNode first) {
        args = Tuple.asTuple(first);
        args.setParent(this);
    }
    public void setArgs(SyntaxNode first) {
        first.setParent(this);
        setArgs(first.getEvaluatedReplacement());
    }

    public Tuple getRets() {
        return rets;
    }
    public void setRets(FinalSyntaxNode second) {
        rets = Tuple.asTuple(second);
        rets.setParent(this);
    }
    public void setRets(SyntaxNode second) {
        second.setParent(this);
        setRets(second.getEvaluatedReplacement());
    }

    public boolean typeEquals(FinalSyntaxNode other) {
        if(!(other instanceof Function fother))
            return false;
        return args.typeEquals(fother.getArgs()) && rets.typeEquals(fother.getRets());
    }

    public boolean isIndexed() {
        return false;
    }
    public boolean isFielded() {
        return false;
    }
    public void assertField(String name, FinalSyntaxNode t) {
        throw new IllegalAccessError("unable to access field " + name + " in function " + getName());
    }
    public FinalSyntaxNode getField(String name) {
        return null;
    }

    public List<ExternalVariable> getFields() {
        return null;
    }
    public List<ExternalFunction> getFunctions() {
        return null;
    }
//    protected FinalSyntaxNode typeConvert(FinalSyntaxNode self, FinalSyntaxNode other, FinalSyntaxNode context) {
//        FinalSyntaxNode ret = super.typeConvert(self, other, context);
//        if(ret != null)
//            return ret;
//
//        if(other instanceof Function fother) {
//            FinalSyntaxNode aconv = args.typeConvert(fother.getArgs(), context);
//            FinalSyntaxNode rconv = rets.typeConvert(fother.getRets(), context);
//            if(aconv != null & rconv != null)
//                return new KappaFunctionDefinition(args,
//                        new Call(getFunction("convert", rets, fother.getRets()),
//                                new Call(self, fother.getArgs())));
//        }
//        return null;
//    }
}
