package data;

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
    FUNCTION,
    /**
     * the following are TODO
     */
    PRAGMA,
    SELF,
    LOCALIZATION,
    CASE
}
