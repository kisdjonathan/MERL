package Interpreter;

import baseTypes.BasicType;
import derivedAST.FinalSyntaxNode;

import java.util.Map;

//TODO
public class Value {
    private FinalSyntaxNode type;
    private Map<String, Value> fields;
    private BasicType value;

    public Value(){}
    public Value(FinalSyntaxNode type) {
        setType(type);
    }
    public Value(BasicType value) {
        this.value = value;
    }

    public BasicType getValue() {
        return value;
    }

    /**
     * sets value to a literal but does not change this' type
     */
    public void setValue(BasicType value) {
        this.value = value;
    }

    public FinalSyntaxNode getType(){
        return type;
    }
    public void setType(FinalSyntaxNode type) {
        this.type = type;
        fields.clear();
        for(FinalSyntaxNode field : type.getBaseType().getFields()) {
            fields.put(field.getName(), new Value(field));
        }
    }
}
