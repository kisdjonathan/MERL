package data;

import baseAST.Identifier;
import baseAST.Literal;
import derivedAST.LambdaFunctionDefinition;
import derivedAST.Tuple;
import derivedAST.Variable;

import java.util.*;

public class Type implements Iterable<Type>{
    public static class SplitPair {
        public int referencePosition, destinationPosition;
        public Type referenceType, destinationType;
        public SplitPair(int refi, Type reft, int desti, Type destt) {
            referencePosition = refi;
            destinationPosition = desti;
            referenceType = reft;
            destinationType = destt;
        }
    }

    public static Type  //TODO L make exhaustive
            BOOL    = new Type("bool"),
            INT     = new Type("int"),
            INTL    = new Type("intl"),
            CHAR    = new Type("char"),
            CHARL   = new Type("charl"),
            FLOAT   = new Type("float"),
            FLOATL  = new Type("floatl"),
            STR     = new Type("str"),
            STRL    = new Type("strl");

    private String name = null;
    private List<Type> composition = new ArrayList<>();
    private Map<String, Integer> namePositions = new HashMap<>();
    private Variable value = null;  //TODO use

    public Type(){}
    public Type(String name){setName(name);}

    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public Variable getVariable() {
        return value;
    }
    public void setVariable(Variable value) {
        this.value = value;
    }


    public void putComponent(Type type) {
        composition.add(type);
    }
    public void putComponent(int index, Type type) {
        composition.set(index, type);
    }
    public void putComponent(String name, Type type) {
        namePositions.put(name, namePositions.size());
        putComponent(type);
    }
    public Type getComponent(int index) {
        return composition.get(index);
    }
    public Type getComponent(String name) {
        return getComponent(namePositions.get(name));
    }

    public boolean isNamed() {
        return !namePositions.isEmpty();
    }

    public Collection<SplitPair> splitMatchWith(Type dest) {
        ArrayList<SplitPair> ret = new ArrayList<>();
        for(String field : dest.namePositions.keySet()) {
            if(!namePositions.containsKey(field)) {
                ret.clear();
                break;
            }
            int ri = namePositions.get(field), di = dest.namePositions.get(field);
            ret.add(new SplitPair(ri, getComponent(ri), di, dest.getComponent(di)));
        }
        if(ret.isEmpty() && dest.composition.size() <= composition.size()) {
            for(int i = 0; i < dest.composition.size(); ++i) {
                ret.add(new SplitPair(i, getComponent(i), i, dest.getComponent(i)));
            }
        }
        return ret;
    }

    public LambdaFunctionDefinition collectConversion(Type from) {
        Variable onlyArg = new Variable("arg", from);
        Variable onlyRet = new Variable("ret", this);
        LambdaFunctionDefinition ret = new LambdaFunctionDefinition(new Tuple(){{addChild(onlyArg);}}, new Tuple(){{addChild(onlyRet);}});
        //TODO
        return ret;
    }

    public Type scatter() {
        //TODO
        return null;
    }

    public boolean equals(Type o) {
        //TODO make equals return false when either o or this is a collection and the other is not, even if they both have the same composition
        return composition.equals(o.composition) && namePositions.equals(o.namePositions);
    }

    public Iterator<Type> iterator() {
        return composition.listIterator();
    }

    public static boolean isSuffix(String suffix) {
        return Literal.isSuffix(suffix);
    }
    public static Type decode(String suffix) {
        //TODO L make exhaustive
        return switch (suffix.toLowerCase()) {
            case "b" -> BOOL;
            case "c" -> CHAR;
            case "lc" -> CHARL;
            case "d" -> INT;
            case "ld" -> INTL;
            case "f" -> FLOAT;
            case "lf" -> FLOATL;
            case "s" -> STR;
            case "ls" -> STRL;
            default -> null;
        };
    }
}
