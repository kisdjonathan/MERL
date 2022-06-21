import javax.lang.model.element.TypeElement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Type represents the types in the program, and includes the basic types as well as structures derived from it
public class Type {
    public static Type
            INT     = new Type("int",   null, true),
            INTL    = new Type("intl",  null, true),
            CHAR    = new Type("char",  null, true),
            CHARL   = new Type("charl", null, true),
            FLOAT   = new Type("float", null, true),
            FLOATL  = new Type("floatl",null, true),
            STR     = new Type("str",   null, true),
            STRL    = new Type("strl",  null, true);

    private String name = null;
    private Map<String, Type> composition = new HashMap();
    private boolean builtin = false;

    public Type() {}
    public Type(String name) {
        this.name = name;
    }
    public Type(String name, Map<String, Type> fields, boolean builtin) {
        composition = fields;
        this.name = name;
        this.builtin = builtin;
    }

    public void putComponent(String name, Type type) {
        composition.put(name, type);
    }

    public boolean equals(Type other) {
        return composition.equals(other.composition);
    }
}
