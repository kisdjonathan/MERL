import java.io.File;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.*;

//TokenReader reads in the source file as raw tokens (only Groups, Tuples from semicolons, loops, id, Literals, and function calls are returned)
//TODO complete
//TODO delete commented code once complete
public class TokenReader {
    public static void main(String[] args) {
        TokenReader reader = new TokenReader(new File("test.txt"));
        SyntaxNode x = reader.getIdentifier();
        SyntaxNode eqn = reader.getOperator(x);
        System.out.println(eqn);
//        System.out.println(reader.getIdentifier());
//        System.out.println(reader.getLiteral());
    }


    private SymbolReader source;
    private Queue<SyntaxNode> queue = new LinkedList<>();

    public TokenReader(File source) {
        this.source = new SymbolReader(source);
    }

    public SyntaxNode get() {
        String next = source.get();
        if(Group.isStartDelimiter(next))
            return readGroup(next);
        else if(Literal.isLiteral(next))
            return readLiteral(next);
        else
            return readIdentifier(next);//TODO complete get based on switch of symbol
    }

    //takes in first part of a literal
    public Literal readLiteral(String value) {
        if(value.equals("\"")){ //string
            value = source.get();
            if(Type.isSuffix(source.peek()))
                return new Literal(value, Type.decode(source.get()));
            else
                return new Literal(value, Type.STR);
        }
        else if(Character.isDigit(value.charAt(0))){    //number
            ParsePosition pos = new ParsePosition(0);
            Number n = NumberFormat.getInstance().parse(value, pos);    //TODO implement hexadecimal numbers (issue with in-number letters, consider 0x)
            String suffix = value.substring(pos.getIndex());

            return new Literal(n.toString(), Type.decode(suffix));
        }
        else
            return null;
    }
    public SyntaxNode getLiteral() {
        return readLiteral(source.get());
    }

    public SyntaxNode readIdentifier(String id) {
        if(Group.isStartDelimiter(source.peek())) {
            Group mod1 = getGroup();
            if(mod1.equals("()"))   //TODO complete
                if(Group.isStartDelimiter(source.peek()))
                    ;
            else if(mod1.equals("[]"))
                ;
            else if(mod1.equals("{}"))
                if(Group.isStartDelimiter(source.peek()))
                    ;
        }
        else
            return new Identifier(id);
        return null;
    }
    public SyntaxNode getIdentifier() {
        return readIdentifier(source.get());
    }

    //takes in a SymbolReader (starting right after the operator) and generates nodes with consideration to chaining and precedence
    private Operator readOperator(SyntaxNode predecessor, String oper) {
        Operator ret = new Operator(oper); ret.addChild(predecessor);

        SyntaxNode next = get();
        if(Operator.isOperator(source.peek()) && Operator.compareTo(oper, source.peek()) > 0) { //next comes before previous
            ret.addChild(readOperator(next, source.get()));
        }
        else if (Operator.isOperator(source.peek()) && Operator.isChainable(oper, source.peek())) {
            while(Operator.isChainable(oper, source.peek())) {
                //TODO complete chaining
            }
        }
        else {
            ret.addChild(next);
        }
        return ret;
    }
    public Operator getOperator(SyntaxNode predecessor) {
        return readOperator(predecessor, source.get());
    }

    //takes in a SymbolReader (starting right after the starting delimiter) and generates nodes for which it constructs a local up to the next delimiter
    private Group readGroup(String startDelim) {
        List<SyntaxNode> nodes = new ArrayList<>();
        while(!Group.isEndDelimiter(source.peek()))
            nodes.add(get());
        return null;//TODO complete
    }
    public Group getGroup() {
        return readGroup(source.get());
    }

//
//    public SyntaxNode get() {
//        String name = source.get();
//
//        if (isStartDelimiter(name)){
//            Group ret = new Group();
//            Tuple children = new Tuple();
//            while(!isEndDelimiter(source.peek())) {
//                children.addChild(get());
//            }
//            String start = name, end = source.get();
//            name = start + end;
//            ret.setName(name);
//
//            if (isSuffix(source.peek()))
//                return constructGroup(ret, source.get());
//            else
//                return ret;
//        }
//        else if(source.peek().equals("\"")) {
//            source.get();
//            String val = source.get();
//            if(isSuffix(source.peek()))
//                return constructString(val, source.get());
//            else
//                return constructString(val, "");
//        }
//        else if(isNumberLiteral(source.peek())) {
//
//        }
//
//        return null;    //TODO implement get
//    }
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
//
//    public SyntaxNode peek() {
//        if(queue.isEmpty())
//            queue.add(get());
//        return queue.peek();
//    }

    private static HashSet<String> controlStructures = new HashSet<>(Arrays.asList(
            "if", "repeat", "while", "for"
    ));
    private boolean isControlStructure(String s) {
        return controlStructures.contains(s);
    }
    private boolean isSeparator(String s) {
        return s.equals(":");
    }

    public boolean eof() {
        return queue.isEmpty() && source.eof();
    }
}
