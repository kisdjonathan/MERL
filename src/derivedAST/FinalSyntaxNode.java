package derivedAST;

import Interpreter.Context;
import baseAST.SyntaxNode;
import baseTypes.BasicType;

public abstract class FinalSyntaxNode extends SyntaxNode {
    public boolean isComplete() {
        return true;
    }
    /**
     * returns whether the value of this can differ between runs
     * i.e. a literal is constant, but time is not
     * used for analysis
     **/
    public boolean isConstant() {
        return true;
    }

    /**
     * does not do anything as this is already complete
     **/
    public FinalSyntaxNode getReplacement(){return this;}

    /**
     * performs necessary setup procedures for this
     * i.e. checks if an assignment is a declaration, and if so, declares the variable
     * call as soon after getReplacement as possible
     **/
    public FinalSyntaxNode evaluated(){
        return this;
    }



    /**
     * the type that directly precedes this (also the type of the node at run-time)
     * should be set as soon as possible to be used for getReplacement
     * null if this is a basic type
     **/
    //TODO L support return-type overloading
    private FinalSyntaxNode declaredType = null;
    public FinalSyntaxNode getDeclaredType() {
        return declaredType;
    }
    public void setDeclaredType(FinalSyntaxNode dependency) {
        declaredType = dependency;
    }

    /**
     * returns the basic type of this which defines this' properties
     **/
    public BasicType getBaseType() {
        return getDeclaredType().getBaseType();
    }
    /**
     * returns whether the type of this and the type of other are equal
     **/
    public boolean typeEquals(FinalSyntaxNode other) {
        return getDeclaredType().typeEquals(other);
    }
    /**
     * returns whether the type of this and the type of other are strictly equal
     **/
    public boolean typeStrictEquals(FinalSyntaxNode other) {
        return getDeclaredType().typeStrictEquals(other);
    }
    /**
     * returns whether this can be cast to other
     **/
    public boolean typeConvertsTo(FinalSyntaxNode other) {
        //TODO L
        //return typeConvert(other, context) != null;
        return typeEquals(other);
    }

    /**
     * for interpreter
     */
    public FinalSyntaxNode interpret(Context context) {
        return null;    //TODO
    }
}
