package data;

import baseAST.Literal;

public class Type {
    public static class TypeConversion {
        public int distance = Integer.MAX_VALUE;
        public Function conversion = null;

        public TypeConversion(){}
        public TypeConversion(int distance, Function conversion) {
            this.distance = distance;
            this.conversion = conversion;
        }
    }

    public static Type
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
    //private boolean builtin = false;

    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    boolean equals(Type other) {
        return getName().equals(other.getName());
    }

    public static boolean isSuffix(String suffix) {
        return Literal.isSuffix(suffix);
    }
    public static Type decode(String suffix) {
        return null;    //TODO implement
    }
}
