package baseAST;

import baseTypes.Bool;
import derivedAST.FinalSyntaxNode;
import data.Usage;
import operations.control.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

//Control represents all control structures
//TODO L list comprehension with controls
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

        public String toString() {
            return name + "(" + control + "){" + body + "}";
        }
        public boolean equals(Object other) {
            return other instanceof Case cObject &&
                    (cObject.control != null && cObject.control.equals(control) || cObject.control == control) &&
                    cObject.body.equals(body);
        }
    }

    private String name = null;
    /**
     * the condition of the control structure
     */
    private SyntaxNode control = null;
    /**
     * the first body of the control structure
     */
    private SyntaxNode body = null;
    private List<Case> chained = new ArrayList<>();

    public Control(){}
    public Control(String name) {
        this.name = name;
    }

    /**
     * additional chained options to the control structure
     * pass null for control to specify default
     */
    public void addChild(String name, SyntaxNode control, SyntaxNode body) {
        chained.add(new Case(name, control, body));
    }
    public void addElse(SyntaxNode control, SyntaxNode body) {
        chained.add(new Case("else", control, body));
    }
    public void addNelse(SyntaxNode control, SyntaxNode body) {
        chained.add(new Case("nelse", control, body));
    }
    public int size() {
        return chained.size();
    }


    public SyntaxNode getControl() {
        return control;
    }
    public void setControl(SyntaxNode control) {
        this.control = control;
    }

    public SyntaxNode getBody() {
        return body;
    }
    public void setBody(SyntaxNode body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }
    public Usage getUsage() {
        return Usage.CONTROL;
    }

    public FinalSyntaxNode getReplacement() {
        ControlStructure ret;
        switch (name) {
            case "if" -> ret = new If(getControl(), getBody(), getParent());
            case "repeat" -> ret = new Repeat(getControl(), getBody(), getParent());
            case "while" -> ret = new While(getControl(), getBody(), getParent());
            case "for" -> {
                SyntaxNode control = getControl();
                if (!control.equals(Usage.OPERATOR, "in"))
                    throw new Error("for loop must have an 'in' statement as control");
                ret = new For(((Operator) control).getChild(0),
                        ((Operator) control).getChild(1),
                        getBody(),
                        getParent());
            }
            default -> throw new Error("Control structure " + getName() + " does not exist");
        }
        for (Case child : chained) {
            if(child.name.equals("else"))
                ret.addElse(child.control == null?new Bool(true):child.control, child.body);
            else
                ret.addNelse(child.control == null?new Bool(true):child.control, child.body);
        }
        return ret;
    }

    public String toString() {
        return super.toString() + "(" + control + "){" + body + "}" + chained;
    }
    public boolean equals(Object other) {
        if(!(super.equals(other) && other instanceof Control gOther &&
                gOther.getControl().equals(getControl()) &&
                gOther.getBody().equals(getBody()) &&
                gOther.size() == size() &&
                gOther.size() == size()))
            return false;
        for(int i = 0; i < size(); ++i)
            if(!gOther.chained.get(i).equals(chained.get(i)))
                return false;
        return true;
    }
}
