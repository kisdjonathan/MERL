import java.util.ArrayList;
import java.util.List;

public class Token {
    private String name = null;
    private Usage usage = null;
    private Token parent = null;
    private List<Token> children = new ArrayList<>();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Usage getUsage() {
        return usage;
    }
    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public Token getParent() {
        return parent;
    }

    public Token getChild(int index) {
        return children.get(index);
    }
    public Token setChild(int index, Token val) {
        val.parent = this;
        return children.set(index, val);
    }

    public Token removeChild(int index) {
        Token ret = children.remove(index);
        ret.parent = null;
        return ret;
    }
    public void addChild(int index, Token child) {
        child.parent = this;
        children.add(index, child);
    }
    public void addChild(Token child) {
        child.parent = this;
        children.add(child);
    }

    public Variable getVariable(String name) {
        return parent.getVariable(name);
    }
    public void putVariable(String name, Variable value) {
        parent.putVariable(name, value);
    }

    public boolean isConstant() {
        return false;   //for subclass use
    }

    public boolean equals(Usage usage) {
        return this.usage == usage;
    }
    public boolean equals(Usage usage, String name) {
        return this.usage == usage && (this.name == name || this.name.equals(name));
    }
    public boolean equals(Token other) {
        return this == other;
    }
}
