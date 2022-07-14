package data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//data.Usage represents how a baseAST.SyntaxNode is going to be used
public enum Usage {
    IDENTIFIER, LITERAL, PRAGMA, SELF, CALL, FIELD, OPERATOR, GROUP, LOCALIZATION, CONTROL, TYPE, TUPLE, CASE;
}
