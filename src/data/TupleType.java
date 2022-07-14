package data;

import java.util.ArrayList;
import java.util.List;

public class TupleType extends Type{
    private List<Type> composition = new ArrayList<>();

    public TupleType(){}
    public TupleType(String name){setName(name);}

    public void putComponent(Type type) {
        composition.add(type);
    }
    public void putComponent(int index, Type type) {
        composition.set(index, type);
    }
    public Type getComponent(int index) {
        return composition.get(index);
    }

    public boolean equals(Type other) {
        return super.equals(other) && other instanceof TupleType && ((TupleType)other).composition.equals(this.composition);
    }
}
