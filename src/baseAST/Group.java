package baseAST;

import data.Type;
import data.Usage;

//baseAST.Group serves only as a placeholder for the body in order to simulate precedence
//TODO group suffixes & custom
public class Group extends SyntaxNode{
    private String name = null;
    private Type type = null;
    private SyntaxNode body = null;
    private boolean constant = false;
    private boolean complete = false;

    public Group(){

    }
    public Group(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Usage getUsage() {
        return Usage.GROUP;
    }

    public boolean isEmpty() {
        return false;
    }
    public int size() {
        return 1;
    }

    public SyntaxNode getBody() {
        return body;
    }
    public void setBody(SyntaxNode body) {
        this.body = body;
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

    public static boolean isStartDelimiter(String s) {
        return s.length() == 1 && switch(s.charAt(0)){
            case '[', '{', '(', '|' ->true;
            default -> false;
        };
    }
    public static boolean isEndDelimiter(String s) {
        return s.length() == 1 && switch(s.charAt(0)){
            case ']', '}', ')', '|' ->true;
            default -> false;
        };
    }

    public String toString() {
        return super.toString() + "[" + body + "]";
    }
}
