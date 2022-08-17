package baseAST;

import derivedAST.FinalSyntaxNode;
import data.Usage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

//Control represents all control structures
//TODO L currently, the Control only allows for return values if the types of all cases are the same
//TODO complete
public class Control extends SyntaxNode {
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

    public static class Case {
        public String name;
        public SyntaxNode control, body;

        public Case(String name, SyntaxNode control, SyntaxNode value) {
            this.name = name;
            this.control = control;
            this.body = value;
        }
    }

    private String name = null;
    private SyntaxNode control = null;
    private SyntaxNode initial = null;
    private List<Case> chained = new ArrayList<>();

    public Control(){}
    public Control(String name, SyntaxNode control, SyntaxNode body) {
        this.name = name;
        setControl(control);
        setInitial(body);
    }

    public void addChild(String name, SyntaxNode control, SyntaxNode body) {
        chained.add(new Case(name, control, body));
    }
    public void addElse(SyntaxNode control, SyntaxNode body) {
        chained.add(new Case("else", control, body));
    }
    public void addNesle(SyntaxNode control, SyntaxNode body) {
        chained.add(new Case("nelse", control, body));
    }

    public SyntaxNode getControl() {
        return control;
    }
    public void setControl(SyntaxNode control) {
        this.control = control;
    }

    public SyntaxNode getInitial() {
        return initial;
    }
    public void setInitial(SyntaxNode initial) {
        this.initial = initial;
    }

    public String getName() {
        return name;
    }
    public Usage getUsage() {
        return Usage.CONTROL;
    }

    public FinalSyntaxNode getReplacement() {
        return null;    //TODO
    }

    public String toString() {
        return "(" + control + ")" + super.toString();
    }
}
