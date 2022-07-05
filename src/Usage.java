import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Usage represents how a SyntaxNode is going to be used
public enum Usage {
    IDENTIFIER, LITERAL, PRAGMA, SELF, CALL, OPERATOR, GROUP, LOCALIZATION, CONTROL, TYPE, TUPLE, CASE;
}
