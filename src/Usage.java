import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Usage {
    CUSTOM, LITERAL, PRAGMA, SELF, OPERATOR, GROUP, LOCALIZATION, CONTROL, TYPE;

    private static Set<String> operators = new HashSet<>(Arrays.asList(
                    "not" ,
                    "and" ,
                    "nand",
                    "or"  ,
                    "nor" ,
                    "xor" ,
                    "eq"  ,
                    "eqeq",
                    "neq" ,
                    "cmp" ,
                    "gt"  ,
                    "gteq",
                    "\tOP_LT,\n" +
                    "\tOP_LTEQ,\n",
                    "\tOP_CONTEXT,\n" +
                    "\tOP_ASSIGN,\n" +
                    "\tOP_COPY,\n" +
                    "\n" +
                    "\tOP_POS,\n" +
                    "\tOP_NEG,\n" +
                    "\tOP_ADD,\n" +
                    "\tOP_SUB,\n" +
                    "\tOP_MUL,\n" +
                    "\tOP_DIV,\n" +
                    "\tOP_REM,\n" +
                    "\tOP_MOD,\n" +
                    "\tOP_EXP,\n" +
                    "\tOP_PARALLEL,\n" +
                    "\n" +
                    "\tOP_BNOT,\n" +
                    "\tOP_BAND,\n" +
                    "\tOP_BNAND,\n" +
                    "\tOP_BOR,\n" +
                    "\tOP_BNOR,\n" +
                    "\tOP_BXOR,\n" +
                    "\tOP_BUP,\n" +
                    "\tOP_BDOWN,\n" +
                    "\tOP_BLEFT,\n" +
                    "\tOP_BRIGHT,\n" +
                    "\n" +
                    "\tOP_CARDINAL,\n" +
                    "\tOP_PERCENT,\n" +
                    "\tOP_FIELD,\n" +
                    "\n" +
                    "\tOP_IN,\n" +
                    "\tOP_WITH,\n" +
                    "\n" +
                    "\tOP_COMBINE,\n" +
                    "\tOP_CAST,\n" +
                    "\n" +
                    "\tOP_CALL,\n" +
                    "\tOP_INDEX,\n" +
                    "\tOP_STRUCTURE,\n" +
                    "\n" +
                    "\tOP_TERMINATE,\n" +
                    "\tOP_SEPARATE,\n" +
                    "\tOP_CONNECT,\n" +
                    "\tOP_COMPILER"
    ));
    private static Set<String> groupings;
    private static Set<String> controls;
    private static Set<String> types;
}
