package baseTypes;

import baseAST.Literal;
import data.TypeSize;
import derivedAST.*;

import java.util.*;
import java.util.List;

//TODO complete
public interface BasicType{
    /**
     * checks if the type of other equals this
     * the criteria for equality is defined as having homogenous properties
     * i.e. both have the same number of indices and the same fields
     **/
    default boolean typeEquals(FinalSyntaxNode other) {
        BasicType otherType = other.getBaseType();
        return indexCount() == otherType.indexCount() &&
                ((getMethods() != null && otherType.getMethods() != null && new HashSet<>(getMethods()).equals(new HashSet<>(otherType.getMethods()))) ||
                        getMethods() == otherType.getMethods()) &&
                ((getMethods() != null && otherType.getMethods() != null && new HashSet<>(getFields()).equals(new HashSet<>(otherType.getFields()))) ||
                        getMethods() == otherType.getMethods());
    }
    /**
     * checks if the type of other is strictly equals this
     * the criteria for equality is defined as having the exact same properties
     * i.e. both have the same name, the same types, the same number of indices, the same fields
     **/
    default boolean typeStrictEquals(FinalSyntaxNode other) {
        return other.getBaseType().getName().equals(getName());
    }
    /**
     * checks if the type of other is contained within this
     * the criteria for contains is defined as having all properties of other defined
     * i.e. if other is indexed, this must also be indexed, but if other is not, this still can be indexed
     **/
    default boolean typeContains(FinalSyntaxNode other) {
        BasicType otherType = other.getBaseType();
        return indexCount() >= otherType.indexCount() &&
                ((getMethods() != null && otherType.getMethods() != null && new HashSet<>(getMethods()).containsAll(otherType.getMethods())) ||
                        getMethods() == otherType.getMethods()) &&
                ((getMethods() != null && otherType.getMethods() != null && new HashSet<>(getFields()).containsAll(otherType.getFields())) ||
                        getMethods() == otherType.getMethods());
    }


    /**
     * returns the name of the type
     * comparing names will not suffice to check for equality
     **/
    String getName();

    /**
     * returns the maximum number of indices that this can contain
     * return in [0, int max]
     * returns 0 if not indexable
     **/
    int indexCount();
    /**
     * returns the variable associated with index i
     * if none exists, returns null
     * used by compiler
     **/
    Variable getIndex(int i);

    /**
     * returns all fields of this
     * if there are no fields, returns null
     **/
    List<Variable> getFields();
    /**
     * returns the variable associated with field name
     * if none exists, returns null
     * used by compiler
     **/
    Variable getField(String name);

    /**
     * returns all methods of this
     * if there are no methods, returns null
     **/
    List<Function> getMethods();
    /**
     * returns the function associated with method signature
     * if none exists, returns null
     * used by compiler
     **/
    Function getMethod(Function signature);

    /**
     * returns the size of this
     **/
    TypeSize getByteSize();

    //TODO L allow custom suffixes
    static HashSet<String> suffixes = new HashSet<>(Arrays.asList(
            "d", "ud", "ld", "uld",
            "c", "uc", "lc", "ulc",
            "f", "uf", "lf", "ulf",

            "cl", "ucl", "lcl", "ulcl", //dynamic string
            "cv", "ucv", "lcv", "ulcv", //static string

            "l", "v",
            "us", "s",
            "m", "um",
            "r"
    ));
    /**
     * used for parsing
     **/
    static boolean isSuffix(String suffix) {
        return suffixes.contains(suffix);
    }
    /**
     * used for parsing
     **/
    static FinalSyntaxNode decode(String suffix) {
        //TODO L make exhaustive
        return switch (suffix.toLowerCase()) {
            case "b" -> new Bool();
            case "c" -> new Char();
            case "lc" -> new Char();
            case "d" -> new Int();
            case "ld" -> new Int();
            case "f" -> new Float();
            case "lf" -> new Float();
            case "s" -> new Str();
            case "ls" -> new Str();
            default -> null;
        };
    }
}
