//Literal stores the type and name of a literal (numbers and strings)
public class Literal extends SyntaxNode{
    private String name = null;
    private Type type = null;

    public Literal(String name, Type type){
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public Type getType() {return type;}
    public Usage getUsage() {
        return Usage.LITERAL;
    }
}
