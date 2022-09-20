package operations;

import data.Usage;
import derivedAST.Field;
import derivedAST.FinalSyntaxNode;
import operations.arithmetic.*;
import operations.comparison.*;
import operations.bool.*;

//TODO
public abstract class BuiltinOperation extends Field {
    /**
     * returns a new instance of the operation corresponding to name
     */
    public static FinalSyntaxNode infix(String name, FinalSyntaxNode origin, FinalSyntaxNode vector) {
        return switch (name) {
            case "+"    ->new Add       (origin, vector);
            case "-"    ->new Subtract  (origin, vector);
            case "*"    ->new Multiply  (origin, vector);
            case "/"    ->new Divide    (origin, vector);
            case "^"    ->new Exponent  (origin, vector);
            case "and"  ->new And       (origin, vector);
            case "or"   ->new Or        (origin, vector);
            case "nor"  ->new Nor       (origin, vector);
            case "xor"  ->new Xor       (origin, vector);
            case "xnor" ->new Xnor      (origin, vector);
            case "<="   ->new NotGreater(origin, vector);
            case "!="   ->new NotEqual  (origin, vector);
            case ">="   ->new NotLesser (origin, vector);
            case "<"    ->new Lesser    (origin, vector);
            case ">"    ->new Greater   (origin, vector);
            case "="    ->new Assign    (origin, vector);   //TODO possibly compare, check if origin is undeclared
            case "<<"   ->new Assign    (origin, vector);
            case "->"   ->new Cast      (origin, vector);
            case "with" ->new With      (origin, vector);
            case "of"   ->new Without   (origin, vector);
            //TODO
            default -> null;
        };
    }
    public Usage getUsage() {
        return Usage.OPERATOR;
    }


    public FinalSyntaxNode getDeclaredType() {
        return getOrigin().getDeclaredType();
    }

    public void evaluate(){}

    public String toString() {
        return super.toString() +
                "[" + getOrigin() +
                ", " + getVector() +
                ']';
    }
}
