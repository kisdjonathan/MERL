//Case represents the cases of chained operators (ie </=/<=, else/nelse, etc) in control structures
//TODO complete
public class Case extends Group{

    public Case(String name, SyntaxNode value) {
        super(name);
        setBody(value);
    }

    public Usage getUsage() {
        return Usage.CASE;
    }
}
