package data;

import java.util.HashMap;
import java.util.Map;

public class StructureType extends Type {
    private Map<String, Type> composition = new HashMap();

    public StructureType() {}
    public StructureType(String name) {
        setName(name);
    }
    public StructureType(String name, Map<String, Type> fields) {
        composition = fields;
        setName(name);
    }

    public void putComponent(String name, Type type) {
        composition.put(name, type);
    }
    public Type getComponent(String name) {
        return composition.get(name);
    }

    public boolean equals(Type other) {
        return super.equals(other) && other instanceof StructureType && ((StructureType)other).composition.equals(this.composition);
    }
}
