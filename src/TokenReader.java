import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//TokenReader reads in the source file as raw tokens (only Groups, Tuples from semicolons, loops, id, Literals, and function calls are returned)
//TODO complete
//TODO delete commented code once complete
public class TokenReader {
    private SymbolReader source;
    private Queue<SyntaxNode> queue = new LinkedList<>();

    public TokenReader(File source) {
        this.source = new SymbolReader(source);
    }

    public SyntaxNode get() {
        return null;    //TODO implement get
    }
//    public SyntaxNode get() {
//        String name = source.get();
//        SyntaxNode tok = new SyntaxNode(name);
//
//        if (isStartDelimiter(name))
//        {
//            if (!isEndDelimiter(source.peek()))	//not empty
//            {
//                tok.addChild(get());
//                //every arg should be readily chained by comma operators
//            }
//            String start = name, end = source.get();
//            tok.setName(start + end);
//            tok.setUsage(Usage.GROUP);
//
//            if (isSuffix(source.peek()))
//                return constructGroupWithSuffix(tok, source.get());
//            else
//                return tok;
//        }
//
//        if (isOperator(name))
//            tok = getOperator(source, name, get() /*operand*/);
//        else
//        {
//            while (!source.eof() && isStartDelimiter(source.peek()))
//                constructPostgroup(tok, get() /*trailing group*/);
//
//            if (!source.eof() && isOperator(source.peek()))
//                tok = getOperator(source, tok, source.get() /*operator*/);
//            else
//                tok = getOperator(source, tok, "field");
//        }
//
//        return balanceToken(tok);
//    }
//
//    private SyntaxNode balanceToken(SyntaxNode t) {
//        //balance
//        if (!t.getChildren().isEmpty())
//        {
//            SyntaxNode child = t.getChild(t.size() - 1);
//
//            int prec_child = operator_precedence(child.name);
//            int prec_self = operator_precedence(t.name);
//            if (prec_child && prec_child <= prec_self && is_bioper(child.name))
//            {
//                t.children.pop_back();
//
//                t.children.push_back(child.children[0]);
//                child.children[0] = balance_oper(t);
//
//                swap(t, child);
//            }
//        }
//
//        //multiple association chian
//        if (t.name == OP_LT ||
//                t.name == OP_LTEQ)
//        {
//            token child = t.children[0];
//            if (child.name == OP_LT ||
//                    child.name == OP_LTEQ)
//            {
//                t.children[0] = child.children[1];
//                child.children.push_back(t);
//                swap(t, child);
//            }
//        }
//
//        if (t.name == OP_GT ||
//                t.name == OP_GTEQ)
//        {
//            //chain comparators so that the first two children are the current comparison,
//            //and the third child is the child comparison
//            token child = t.children[0];
//            if (child.name == OP_GT ||
//                    child.name == OP_GTEQ)
//            {
//                t.children[0] = child.children[1];
//                child.children.push_back(t);
//                swap(t, child);
//            }
//        }
//
//        //self chain only
//        //self-to-self (because operator precedence, only second child can be the same operator as self)
//        if (t.name == OP_COMBINE ||
//                t.name == OP_CONNECT ||
//                t.name == OP_EQ ||
//                (t.name == OP_TERMINATE &&
//                        t.children.size() > 1))
//        {
//            token child = *t.children.begin();
//            if (child.name == t.name)
//            {
//                for (int i = 1; i < t.children.size(); i++)
//                    child.children.push_back(t.children[i]);
//                t = child;
//            }
//        }
//
//        //chain else statements
//        if (t.name == CL_ELSE || t.name == CL_NELSE)
//        {
//            token child = *t.children.begin();
//            if (child.name == CL_CONDITION ||
//                    child.name == CL_SWITCH ||
//                    child.name == CL_WHILE ||
//                    child.name == CL_FOR ||
//                    child.name == CL_REPEAT)
//            {
//                t.children.erase(t.children.begin());
//                child.children.push_back(t);
//                t = child;
//            }
//        }
//
//        return balance_token(t);
//    }

    public SyntaxNode peek() {
        if(queue.isEmpty())
            queue.add(get());
        return queue.peek();
    }

    private static HashSet<String> suffixes = new HashSet(Arrays.asList(
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
    private static HashSet<String> operators = new HashSet(Arrays.asList(
            "if", "repeat", "while", "for",
            "assign", "context", "morph",
            "connect", "attach", "associate", "discard",
            "with"
    ));
    private boolean isStartDelimiter(String s) {
        return s.length() == 1 && switch(s.charAt(0)){
            case '[', '{', '(', '|' ->true;
            default -> false;
        };
    }
    private boolean isEndDelimiter(String s) {
        return s.length() == 1 && switch(s.charAt(0)){
            case ']', '}', ')', '|' ->true;
            default -> false;
        };
    }
    private boolean isSuffix(String s) {
        return suffixes.contains(s);
    }
    private boolean isOperator(String s) {
        return operators.contains(s);
    }

    public boolean eof() {
        return queue.isEmpty() && source.eof();
    }
}
