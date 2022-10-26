package operations.control;

import baseAST.SyntaxNode;
import baseTypes.Bool;
import derivedAST.FinalSyntaxNode;
import derivedAST.Local;
import operations.BuiltinOperation;

import java.util.HashSet;
import java.util.Set;

public class ControlStructure extends BuiltinOperation {
    protected class Node extends Local {
        public final FinalSyntaxNode condition, body;
        public Node executionFalse = null, executionTrue = null;

        public Node(SyntaxNode condition, SyntaxNode body) {
            setParent(ControlStructure.this);
            condition.setParent(ControlStructure.this);
            body.setParent(this);
            this.condition = condition.getEvaluatedReplacement();
            this.body = body.getEvaluatedReplacement();
        }
        public Node(FinalSyntaxNode condition, FinalSyntaxNode body) {
            setParent(ControlStructure.this);
            condition.setParent(ControlStructure.this);
            body.setParent(this);
            this.condition = condition;
            this.body = body;
        }
    }
    private Node base;
    private Set<Node> nodes = new HashSet<>();

    public void addElse(SyntaxNode condition, SyntaxNode body) {
        Node newNode = new Node(condition, body);
        for(Node n : nodes)
            if(n.executionFalse == null)
                n.executionFalse = newNode;
        nodes.add(newNode);
    }
    public void addElse(FinalSyntaxNode condition, FinalSyntaxNode body) {
        Node newNode = new Node(condition, body);
        for(Node n : nodes)
            if(n.executionFalse == null)
                n.executionFalse = newNode;
        nodes.add(newNode);
    }
    public void addElse(SyntaxNode body) {
        addElse(new Bool(true), body);
    }
    public void addElse(FinalSyntaxNode body) {
        addElse(new Bool(true), body);
    }
    public void addNelse(SyntaxNode condition, SyntaxNode body) {
        Node newNode = new Node(condition, body);
        for(Node n : nodes)
            if(n.executionTrue == null)
                n.executionTrue = newNode;
        nodes.add(newNode);
    }
    public void addNelse(FinalSyntaxNode condition, FinalSyntaxNode body) {
        Node newNode = new Node(condition, body);
        for(Node n : nodes)
            if(n.executionTrue == null)
                n.executionTrue = newNode;
        nodes.add(newNode);
    }
    public void addNelse(SyntaxNode body) {
        addNelse(new Bool(true), body);
    }
    public void addNelse(FinalSyntaxNode body) {
        addNelse(new Bool(true), body);
    }

    public Node getBase() {
        return base;
    }
    public void setBase(FinalSyntaxNode condition, FinalSyntaxNode body) {
        this.base = new Node(condition, body);
    }
    public void setBase(SyntaxNode condition, SyntaxNode body) {
        this.base = new Node(condition, body);
    }
}
