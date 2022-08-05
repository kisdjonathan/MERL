package baseAST;

import data.Signature;
import data.Type;
import data.Usage;
import derivedAST.*;
import operations.*;

import java.util.*;

//Operator represents an operation between children
//TODO operators: list all, list prefixes, list infixes, list postfixes, list precedence, add functions to access
//TODO chained operators
public class Operator extends SyntaxNode implements Iterable<SyntaxNode>{
    private static class PrecedenceLevel {
        public static int SPACING = 16, MIN_VALUE = Integer.MIN_VALUE;
        public PrecedenceLevel(double pos, String prev, String nxt) {
            position = pos;
            previous = prev;
            next = nxt;
        }
        public String previous = null, next = null;
        public double position;
    }
    private static final String[][] builtinOperators = new String[][]{  //sorted by low to high precedence
            {")", "}", "]", ":"},
            {";"},
            {"if", "while", "repeat", "for", "else", "nelse"},
            {"with"},
            {"<<", ">>"},   //TODO make eval left to right
            {"="},
            {"!="},
            {"<", ">", "<=", ">="},
            {","},
            {"+", "-"},
            {"%"},
            {"*", "/"},
            {"^"},
            {"->"}
            //TODO complete
    };
    private static final Map<String, String> builtinOperatorNames = new HashMap<>(){{
        put("+", "add");
        put("-", "sub");
        put("*", "mul");
        put("/", "div");
        put("^", "exp");
        put("->", "cast");
        //TODO complete
    }};
    private static final Set<String> infixes = new HashSet<>(Arrays.asList(
            "else", "nelse",
            "with",
            ";",
            "<<", ">>", "=", "!=", "<", ">", "<=", ">=",
            "+", "-", "*", "/", "^",
            "->"
            //TODO complete
    ));
    private static final Set<String> prefixes = new HashSet<>(Arrays.asList(
            "if", "for", "while", "repeat",
            "+", "-"
            //TODO complete
    ));
    private static final Set<String> postfixes = new HashSet<>(Arrays.asList(
            ";", "%"
    ));
    private static final List<Set<String>> chainGroups = Arrays.asList(
            new HashSet<>(Arrays.asList(
                    "<", "<=", "="
            )),
            new HashSet<>(Arrays.asList(
                    ">", ">=", "="
            )),
            new HashSet<>(Arrays.asList(
                    "else", "nelse"
            )),
            new HashSet<>(List.of(
                    ","
            )),
            new HashSet<>(List.of(
                    ";"
            ))
            //TODO complete chaining
    );
    private static final Map<String, PrecedenceLevel> precedences = new HashMap<>();
    static {
        int levelNum = PrecedenceLevel.MIN_VALUE;
        String prevRepr = null;
        PrecedenceLevel prevLevel = null;
        for(String[] oplevel : builtinOperators) {
            PrecedenceLevel level = new PrecedenceLevel(levelNum, prevRepr, null);

            for(String op : oplevel)
                precedences.put(op, level);

            if(prevLevel != null)
                prevLevel.next = oplevel[0];
            prevRepr = oplevel[0];
            prevLevel = level;
            levelNum += PrecedenceLevel.SPACING;
        }
    }
    public static boolean isOperator(String op) {
        return precedences.containsKey(op);
    }
    public static boolean isInfix(String op) {
        return infixes.contains(op);
    }
    public static boolean isPrefix(String op) {
        return prefixes.contains(op);
    }
    public static boolean isPostfix(String op) {
        return postfixes.contains(op);
    }
    private static double indexOf(String op) {
        return precedences.get(op).position;
    }

    private static int compareTo(String a, String b) {
        double t = indexOf(a);
        double o = indexOf(b);
        return Double.compare(t, o);
    }

    private static boolean isRightToLeft(String op) {
        return false;   //TODO complete
    }
    private static boolean isLeftToRight(String op) {
        return !isRightToLeft(op);
    }
    //pred comes after ref in the line (eg 1+2*3 -> pred = *, ref = +)
    public static boolean isBefore(String pred, String ref) {
        int cmp = compareTo(pred, ref);
        return cmp > 0 || cmp == 0 && isRightToLeft(pred);
    }
    //post comes after ref in the line (eg 1*2+3 -> ref = *, post = +)
    public static boolean isAfter(String post, String ref) {
        return !isBefore(post, ref);
    }

    public static void addOperatorBefore(String ref, String op) {
        PrecedenceLevel topLevel = precedences.get(ref);
        PrecedenceLevel lowLevel = precedences.get(topLevel.previous);
        PrecedenceLevel curLevel = new PrecedenceLevel((topLevel.position + lowLevel.position)/2, topLevel.previous, lowLevel.next);
        lowLevel.next = topLevel.previous = op;
        precedences.put(op, curLevel);
    }
    public static void addOperatorAfter(String ref, String op) {
        addOperatorBefore(precedences.get(ref).next, op);
    }
    public static void addOperatorAt(String ref, String op) {
        precedences.put(op, precedences.get(ref));
    }
    public static void addInfix(String op) {
        infixes.add(op);
    }
    public static void addPrefix(String op) {
        prefixes.add(op);
    }
    public static void addPostfix(String op) {
        postfixes.add(op);
    }

    public static boolean isChainable(String op, String with) {
        for(var v : chainGroups) {
            if(v.contains(op) && v.contains(with))
                return true;
        }
        return false;
    }


    private String name = null;
    private List<SyntaxNode> children = new ArrayList<>();
    private boolean constant = false;
    private boolean prefix = false;

    public Operator(){}
    public Operator(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }
    public Usage getUsage() {
        return Usage.OPERATOR;
    }

    public boolean isEmpty() {
        return children.isEmpty();
    }
    public int size() {
        return children.size();
    }

    public SyntaxNode getChild(int index) {
        return children.get(index);
    }
    public List<SyntaxNode> getChildren() {
        return children;
    }
    public SyntaxNode setChild(int index, SyntaxNode val) {
        val.setParent(this);
        return children.set(index, val);
    }
    public void addChildren(Collection<SyntaxNode> collection) {
        children.addAll(collection);
    }

    public SyntaxNode removeChild(int index) {
        SyntaxNode ret = children.remove(index);
        ret.setParent(null);
        return ret;
    }
    public void addChild(int index, SyntaxNode child) {
        child.setParent(this);
        children.add(index, child);
    }
    public void addChild(SyntaxNode child) {
        child.setParent(this);
        children.add(child);
    }

    public boolean isChained(){
        return false;
    }

    public boolean isPrefix() {
        return prefix;
    }
    public void setPrefix(boolean v) {
        prefix = v;
    }

    public boolean isConstant() {
        return constant;
    }
    public void setConstant(boolean v) {
        constant = v;
    }

    public FinalSyntaxNode getReplacement() {
        if(name.equals("<<") || name.equals("=")) {
            //TODO variable declaration
            //TODO function declaration
            SyntaxNode origin = getChild(0);
            if(origin.equals(Usage.IDENTIFIER) && !hasVariable(origin.getName())) {
                return new Assign(origin.getReplacement(), getChild(1).getReplacement()); //TODO L tuple assignment, structure assignment
            }
            if(origin.equals(Usage.FIELD) && ((Consecutive)origin).isFunction()) {
                //function
                //there are no assignments to function returns however,
                //TODO implement comparison on function calls (rn a(b)=c will always set a(b) to c, and not compare a(b) to c)

                FunctionDefinition ret = new FunctionDefinition();

                SyntaxNode temp = ((Consecutive)origin).getVector(); temp.setParent(ret);
                ret.setParam(Tuple.asTuple(temp.getReplacement())); ret.getParam().evaluate();

                temp = getChild(1); temp.setParent(ret);
                ret.setBody(Tuple.asTuple(temp.getReplacement()));  ret.getBody().evaluate();  //TODO L as of now, only return statements can give a return; add assign returns and lambda returns

                ret.evaluate();
                return ret;
            }
        }
        else if(name.equals(">>")) {
            Contextualization ret = new Contextualization();
            FinalSyntaxNode context = getChild(0).getReplacement();
            for(Type v : context.getType())
                ret.putVariable(v.getName(), v.getVariable());
            SyntaxNode temp = getChild(1); temp.setParent(ret);
            ret.setBody(temp.getReplacement());

            ret.evaluate();
            return ret;
        }
        FinalSyntaxNode origin = getChild(0).getReplacement(), vector = getChild(1).getReplacement();
        Function f = getFunction(new Signature(builtinOperatorNames.get(name), origin.getType(), vector.getType()));
        if(f != null)
            return new Call(f, new Tuple(Arrays.asList(origin, vector)));
        FinalSyntaxNode ret =
        switch (name) {
            case "<<" ->
                    new Assign(origin, vector);
            case "=", "<", ">", "<=", ">=" ->
                    new ComparisonInfix(name, origin, vector);
            case "and", "or", "nor", "xor", "xnor" ->
                    new BooleanInfix(name, origin, vector);
            case "not" ->
                    null;

            case "$and", "$or", "$nor", "$xor", "$xnor", "$up", "$down", "$left", "$right" ->
                    new BitwiseInfix(name, origin, vector);
            case "$not" ->
                    null;

            case "+", "-", "*", "/", "^", "modulo", "remainder" ->
                    new ArithmeticInfix(name, origin, vector);
            case "->" ->
                    new Cast(origin, vector.getType());
            case "with" ->
                    null;   //TODO L
            case "of" ->  //aka without
                    null;   //TODO L
            case "in" ->
                    null;   //TODO L

            //TODO
            default ->
                null;    //TODO
        };
        ret.evaluate();
        return ret;
    }

    public boolean isBefore(Operator other) {
        return isBefore(other.getName());
    }
    public boolean isBefore(String other) {
        return compareTo(getName(), other) <= 0;
    }

    public boolean after(Operator other) {
        return after(other.getName());
    }
    public boolean after(String other) {
        return compareTo(getName(), other) > 0;
    }

    public String toString() {
        return super.toString() + children;
    }

    public Iterator<SyntaxNode> iterator() {
        return children.listIterator();
    }
}
