package data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//data.Usage represents how a baseAST.SyntaxNode is going to be used
public enum Usage {
    /**
     * each of the following corresponds to a baseAST class
     */
    IDENTIFIER,
    LITERAL,
    GROUP,

    /**
     * each of the following corresponds to both a baseAST and derived class
     * OPERATOR = Operator, BuiltinOperation
     * FIELD = Consecutive, Field
     * CONTROL = Control, ControlStructure
     */
    OPERATOR,
    FIELD,
    CONTROL,
    /**
     * each of the following corresponds to derived classes
     */
    CALL,
    TYPE,
    TUPLE,
    VARIABLE,
    /**
     * the following are TODO
     */
    PRAGMA,
    SELF,
    LOCALIZATION,
    CASE;
}
