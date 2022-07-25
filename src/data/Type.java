package data;

import baseAST.Literal;
import baseAST.SyntaxNode;
import operations.Cast;
import operations.Field;

import java.util.*;

public class Type {
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
            BOOL    = new StructureType("bool"),
            INT     = new StructureType("int"),
            INTL    = new StructureType("intl"),
            CHAR    = new StructureType("char"),
            CHARL   = new StructureType("charl"),
            FLOAT   = new StructureType("float"),
            FLOATL  = new StructureType("floatl"),
            STR     = new StructureType("str"),
            STRL    = new StructureType("strl");

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

    public Function directConvert(Type dest) {
        return null;    //TODO
    }

    public Function collectConvert(Type dest) {
        return null;    //TODO
    }

    public Type scatter() {
        return null;    //TODO
    }

    public boolean equals(Type o) {
        return (name == null || o.name == null || name.equals(o.name)) && composition.equals(o.composition);
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
