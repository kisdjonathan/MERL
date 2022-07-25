package data;

import baseAST.SyntaxNode;

import java.util.HashMap;
import java.util.Map;

public class StructureType extends Type {
    private Map<String, Integer> namePositions = new HashMap<>();

    public StructureType() {}
    public StructureType(String name) {
        setName(name);
    }

    public void putComponent(String name, Type type) {
        namePositions.put(name, namePositions.size());
        super.putComponent(type);
    }
    public Type getComponent(String name) {
        return super.getComponent(namePositions.get(name));
    }

    public boolean contains(Type other, SyntaxNode context) {
        if(!(other instanceof StructureType sOther))
            return super.contains(other, context);
        if(equals(other) || context.hasFunction(new Signature(other, this)))
            return true;

        for(String field : sOther.namePositions.keySet()) {
            if(!getComponent(field).contains(sOther.getComponent(field), context))
                return false;
        }
        return true;
    }

    public TypeConversion getConversion(Type dest, SyntaxNode context) {
        if(!(dest instanceof StructureType sDest))
            return super.getConversion(dest, context);

        if(equals(dest))
            return new TypeConversion(0, null);

        Function convertFunc = context.getFunction(new Signature(dest, this));
        if(convertFunc != null)
            return new TypeConversion(1, convertFunc);

        LambdaFunction retFunc = new LambdaFunction();
        int dist = 1;
        for(String field : sDest.namePositions.keySet()) {
            TypeConversion conversion = getComponent(field).getConversion(sDest.getComponent(field), context);
            if (conversion.distance >= Integer.MAX_VALUE)
                return conversion;
            else
                retFunc.putConversion(field, conversion.conversion); //TODO
            dist += conversion.distance;
        }
        return new TypeConversion(dist, retFunc);
    }
}
