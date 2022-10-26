package baseAST;

import baseTypes.Signature;
import derivedAST.Function;
import baseTypes.Tuple;
import derivedAST.FinalSyntaxNode;
import data.Usage;
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
            {")", "}", "]"},
            {";", ":"},
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

    /**
     * returns the spelled-out form of opName
     */
    private String getBuiltin(String opName) {
        String ret = builtinOperatorNames.get(opName);
        if(ret == null)
            return opName;
        return ret;
    }


    private static final Set<String> infixes = new HashSet<>(Arrays.asList(
            "else", "nelse",
            "with",
            ";",
            "<<", ">>", "=", "!=", "<", ">", "<=", ">=",
            "+", "-", "*", "/", "^",
            "->",
            ":"
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
            new HashSet<>(Arrays.asList("<", "<=", "=")),
            new HashSet<>(Arrays.asList(">", ">=", "=")),
            new HashSet<>(Arrays.asList("else", "nelse")),
            new HashSet<>(List.of(",")),
            new HashSet<>(List.of(";"))
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

    /**
     * pred comes before ref in the line (eg 1+2*3 -> pred = *, ref = +)
     */
    public static boolean isBefore(String pred, String ref) {
        int cmp = compareTo(pred, ref);
        return cmp > 0 || (cmp == 0 && isRightToLeft(pred));
    }

    /**
     * post comes after ref in the line (eg 1*2+3 -> ref = *, post = +)
     */
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

    public int size() {
        return children.size();
    }

    public void addChildren(Collection<SyntaxNode> collection) {
        children.addAll(collection);
    }
    public List<SyntaxNode> getChildren() {
        return children;
    }

    public SyntaxNode getChild(int index) {
        return children.get(index);
    }
    public void addChild(int index, SyntaxNode child) {
        child.setParent(this);
        children.add(index, child);
    }
    public void addChild(SyntaxNode child) {
        child.setParent(this);
        children.add(child);
    }
    public SyntaxNode setChild(int index, SyntaxNode val) {
        val.setParent(this);
        return children.set(index, val);
    }
    public SyntaxNode removeChild(int index) {
        SyntaxNode ret = children.remove(index);
        ret.setParent(null);
        return ret;
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

    /**
     * val must be an identifier or a structured identifier
     */
    protected boolean isUndefinedVariable(SyntaxNode val) {
        if(val.equals(Usage.IDENTIFIER) && getVariable(val.getName()) == null)
            return true;
        if(val.getUsage() == Usage.FIELD && ((Consecutive)val).isStructure())
            return true;
        return false;
    }

    /**
     * cval must be function
     */
    protected boolean isUndefinedFunction(Consecutive cval) {
        if(cval.isInferredFunction())
            return getFunction(cval.getOrigin().getName()) == null;
        if(cval.isTypedFunction())
            return getFunction(((Consecutive)cval.getOrigin()).getOrigin().getName()) == null;
        throw new Error(cval + " is not a function");
    }

    public FinalSyntaxNode getReplacement() {
        if(size() == 1) {   //prefixes and suffixes
            FinalSyntaxNode origin = getChild(0).getEvaluatedReplacement();
            Tuple args = Tuple.asTuple(origin);
            Signature signature = new Signature(args, null);    //TODO rets
            Function f = getFunction(getBuiltin(name), signature);
            if(f != null)
                return new Call(f, args);
            if(prefix)
                return BuiltinOperation.prefix(name, origin);
            else
                return BuiltinOperation.suffix(name, origin);
        }

        //size is 2
        SyntaxNode dest = getChild(0);
        SyntaxNode source = getChild(1);

        //function definition
        if(dest.equals(Usage.FIELD) && ((Consecutive)dest).isCall() &&
                ((name.equals("=") && isUndefinedFunction((Consecutive) dest)) ||
                name.equals("<<"))) {
            Consecutive cval = (Consecutive) dest;

            String funcName;
            SyntaxNode param, rets;
            if(cval.isInferredFunction()) {
                funcName = cval.getOrigin().getName();
                param = cval.getVector();
                rets = new Group();
            } else if(cval.isTypedFunction()) {
                funcName = ((Consecutive)cval.getOrigin()).getOrigin().getName();
                rets = ((Consecutive)cval.getOrigin()).getVector();
                param = cval.getVector();
            } else {
                throw new UnsupportedOperationException("can not assign to function return");
            }

            Function ret = new Function(funcName);
            ret.getDeclaredType().setArgs(param);
            ret.getDeclaredType().setRets(rets);

            putFunction(ret);

            ret.setBody(source);
            return ret;
        }
        //variable assignment
        if((name.equals("=") && isUndefinedVariable(dest)) ||
                name.equals("<<")) {
            Assign ret = new Assign();
            ret.setParent(getParent());
            ret.setVector(source);
            ret.setOrigin(dest);

            return ret;
        }
        //contextualization
        if(name.equals(">>")) {
            return new Contextualization(dest, source);
        }

        //general overridable operations
        //TODO L restructure to perform with return type overloading, see below in signature
        FinalSyntaxNode origin = dest.getEvaluatedReplacement();
        FinalSyntaxNode vector = source.getEvaluatedReplacement();

        Tuple args = new Tuple(Arrays.asList(origin, vector));
        Signature signature = new Signature(args, null /*TODO L here*/);
        Function f = getFunction(getBuiltin(name), signature);
        if (f != null)
            return new Call(f, args);
        FinalSyntaxNode ret = BuiltinOperation.infix(name, origin, vector);
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

    public boolean equals(Object other) {
        if(!(Operator.super.equals(other) &&
                size() == ((SyntaxNode)other).size() &&
                isChained() == ((Operator) other).isChained()))
            return false;
        for(int i = 0; i < size(); ++i)
            if(!((Operator)other).getChild(i).equals(getChild(i)))
                return false;
        return true;
    }
}
