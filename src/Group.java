import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//Group serves only as a placeholder for the body in order to simulate precedence
public class Group extends SyntaxNode{
    private Type type = null;
    private SyntaxNode body = null;
    private boolean constant = false;
    private boolean complete = false;

    public Group(){}


    public String getName() {
        return null;
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
}
