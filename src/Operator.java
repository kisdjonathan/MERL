import java.util.*;

//Operator represents an operation between children
public class Operator extends SyntaxNode implements Comparable<Operator>{
    private static class PrecedenceLevel {
        public PrecedenceLevel(double pos, String prev, String nxt) {
            position = pos;
            previous = prev;
            next = nxt;
        }
        public String previous = null, next = null;
        public double position;
    }
    private static Map<String, PrecedenceLevel> precedences = new HashMap<>();
    static {
        precedences.put(";", new PrecedenceLevel(Long.MAX_VALUE, "=", null));
        precedences.put("=", new PrecedenceLevel(0, "->", ";"));
        precedences.put("->", new PrecedenceLevel(Long.MIN_VALUE, null, "="));

        addOperatorBefore("=", "+");
        addOperatorAt("+", "-");
        addOperatorBefore("+", "*");
        addOperatorAt("*", "/");
    }
    private static double indexOf(String op) {
        return isOperator(op) ? precedences.get(op).position : -1;
    }
    public static boolean isOperator(String op) {
        return precedences.containsKey(op);
    }
    public static int compareTo(String a, String b) {
        double t = indexOf(a);
        double o = indexOf(b);
        return Double.compare(t, o);
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
    private static List<Set<String>> chainGroups = Arrays.asList(
            new HashSet<>(Arrays.asList(
                    "<", "<=", "=", "=="
            )),
            new HashSet<>(Arrays.asList(
                    ">", ">=", "=", "=="
            ))
            //TODO complete chaining
    );
    public static boolean isChainable(String op, String with) {
        //TODO complete chaining
        return false;
//        for(var v : chainGroups) {
//            if(v.contains(op) && v.contains(with))
//                return true;
//        }
//        return false;
    }

    private String name = null;
    private Type type = null;
    private List<SyntaxNode> children = new ArrayList<>();
    private boolean constant = false;
    private boolean complete = false;

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
    public SyntaxNode setChild(int index, SyntaxNode val) {
        val.setParent(this);
        return children.set(index, val);
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

    public boolean isConstant() {
        return constant;
    }
    public void setConstant(boolean v) {
        constant = v;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setType(Type type) {
        this.type = type;
    }
    public Type getType() {
        return type;
    }

    public void setComplete(boolean v) {
        complete = v;
    }

    public int compareTo(Operator other) {
        return compareTo(getName(), other.getName());
    }

    public int compareTo(String other) {
        return compareTo(getName(), other);
    }

    public String toString() {
        return super.toString() + children;
    }
}
