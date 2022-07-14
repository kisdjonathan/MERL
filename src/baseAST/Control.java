package baseAST;

import java.util.Arrays;
import java.util.HashSet;

//baseAST.Control represents all control structures
//TODO complete
public class Control extends Operator {
    private static final HashSet<String> controls = new HashSet<>(Arrays.asList(
            "if", "repeat", "while", "for"
    ));
    private static final HashSet<String> cases = new HashSet<>(Arrays.asList(
            "else", "nelse"
    ));
    public static boolean isControl(String s) {
        return controls.contains(s);
    }
    public static boolean isCase(String s) {
        return cases.contains(s);
    }

    private SyntaxNode control = null;

    public Control(){}
    public Control(String name, SyntaxNode control, SyntaxNode body) {
        super(name);
        setControl(control);
        addChild(body);
    }

    public SyntaxNode getControl() {
        return control;
    }
    public void setControl(SyntaxNode control) {
        this.control = control;
    }

    public String toString() {
        return "(" + control + ")" + super.toString();
    }
}
