import java.io.File;
import java.text.NumberFormat;
import java.text.ParsePosition;

//TokenReader reads in the source file as raw tokens (only Groups, Tuples from semicolons, loops, id, Literals, and function calls are returned)
//read methods take in an additional pre-read argument
//TODO complete
//TODO delete commented code once complete
public class TokenReader {
    public static void main(String[] args) {
        TokenReader reader = new TokenReader(new File("test.txt"));
//        SyntaxNode x = reader.getIdentifier();
//        SyntaxNode eqn = reader.getOperator(x);
//        System.out.println(eqn);
        System.out.println(reader.readGroup(""));
//        System.out.println(reader.get());
    }


    private final SymbolReader source;

    public TokenReader(File source) {
        this.source = new SymbolReader(source);
    }

    public SyntaxNode get() {
        if(eof())
            return null;

        String next = source.get();
        if(Group.isStartDelimiter(next))
            return readGroup(next);
        else if(false/*Operator.isPrefix(next)*/)
            return null;    //TODO prefix
        else if(Control.isControl(next))
            return readControl(next);
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
        return new Identifier(id);
    }
    public SyntaxNode getIdentifier() {
        return readIdentifier(source.get());
    }

    public SyntaxNode readControl(String id) {
        SyntaxNode control = readGroup("", ":").getBody();
        SyntaxNode body = get();
        while(Operator.isOperator(source.peek()) &&
                Operator.isBefore(source.peek(), id))
            getOperator(body);

        Control ret = new Control(id, control, body);

        //else/nelse
        if(Control.isControl(id))
            while(Control.isCase(source.peek()))
                ret.addChild(getControl());

        return ret;
    }
    public SyntaxNode getControl() {
        return readControl(source.get());
    }

    private boolean isPostfix(String oper) {
        return Operator.isPostfix(oper) && (
                eof() ||
                Operator.isInfix(source.peek()) &&
                        (!Operator.isPrefix(source.peek()) || Operator.isBefore(oper, source.peek()))
        );
    }
    private Operator readPostfix(SyntaxNode predecessor, String postfix) {
        Operator ret = new Operator(postfix); ret.addChild(predecessor);
        if(isPostfix(source.peek()))
            return readPostfix(ret, source.get());
        else
            return ret;
    }

    //takes in a SymbolReader (starting right after the operator) and generates nodes with consideration to chaining and precedence
    private Operator readOperator(SyntaxNode predecessor, String oper) {
        Operator ret = new Operator(oper); ret.addChild(predecessor);

        //infix
        if(!isPostfix(oper)) {
            SyntaxNode next = get();

            while (!eof()) {
                if (!Operator.isOperator(source.peek()))
                    next = new Field(next, get());
                else if (Operator.isBefore(oper, source.peek()))
                    next = getOperator(next);
                else if(Operator.isChainable(oper, source.peek())) {
                    ret.addChild(new Case(source.get(), next));
                    if(eof())
                        return ret;
                    else
                        next = get();
                }
                else break;
            }
            ret.addChild(next);
        }
        return ret;
    }
    public Operator getOperator(SyntaxNode predecessor) {
        return readOperator(predecessor, source.get());
    }

    //takes in a SymbolReader (starting right after the starting delimiter) and generates nodes for which it constructs a local up to the next delimiter
    private Group readGroup(String startDelim) {
        Group ret = new Group();
        if(!source.eof() && !Group.isEndDelimiter(source.peek())) {
            SyntaxNode body = get();
            while (!source.eof() && !Group.isEndDelimiter(source.peek())) {
                body = getOperator(body);
            }
            ret.setBody(body);
        }
        String endDelim = source.eof() ? "EOF" : source.get();
        ret.setName(startDelim + endDelim);
        //TODO suffixes
        return ret;
    }
    private Group readGroup(String startDelim, String endDelim) {
        Group ret = new Group();
        if(!source.eof() && !endDelim.equals(source.peek())) {
            SyntaxNode body = get();
            while (!source.eof() && !endDelim.equals(source.peek())) {
                body = getOperator(body);
            }
            ret.setBody(body);
        }
        source.get();   //clear endDelim
        ret.setName(startDelim + endDelim);
        //TODO suffixes
        return ret;
    }
    public Group getGroup() {
        return readGroup(source.get());
    }

    public boolean eof() {
        return source.eof();
    }
}
