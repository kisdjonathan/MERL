package operations;

import baseTypes.Tuple;
import data.Usage;
import derivedAST.Field;
import derivedAST.FinalSyntaxNode;
import operations.arithmetic.*;
import operations.bitwise.*;
import operations.comparison.*;
import operations.bool.*;
import operations.unifix.*;

import java.util.Arrays;

public abstract class BuiltinOperation extends Field {
    /**
     * returns a new instance of the operation corresponding to name
     * = is equal
     * >> is not included
     */
    public static FinalSyntaxNode infix(String name, FinalSyntaxNode origin, FinalSyntaxNode vector) {
        return switch (name) {
            //Arithmetic
            case "+"    ->new Add       (origin, vector);
            case "-"    ->new Subtract  (origin, vector);
            case "*"    ->new Multiply  (origin, vector);
            case "/"    ->new Divide    (origin, vector);
            case "^"    ->new Exponent  (origin, vector);
            //boolean
            case "and"  ->new And       (origin, vector);
            case "or"   ->new Or        (origin, vector);
            case "nor"  ->new Nor       (origin, vector);
            case "xor"  ->new Xor       (origin, vector);
            case "xnor" ->new Xnor      (origin, vector);
            //comparison
            case "<="   ->new NotGreater(origin, vector);
            case "!="   ->new NotEqual  (origin, vector);
            case "="    ->new Equal     (origin, vector);
            case ">="   ->new NotLesser (origin, vector);
            case "<"    ->new Lesser    (origin, vector);
            case ">"    ->new Greater   (origin, vector);
            //bitwise
            case "$and" ->new BitAnd    (origin, vector);
            case "$or"  ->new BitOr     (origin, vector);
            case "$nor" ->new BitNor    (origin, vector);
            case "$xor" ->new BitXor    (origin, vector);
            case "$xnor"->new BitXnor   (origin, vector);
            //other
            case "<<"   ->new Assign    (origin, vector);
            case "->"   ->new Cast      (origin, vector);
            case "with" ->new With      (origin, vector);
            case "of"   ->new Without   (origin, vector);

            case ";",","->new Tuple     (Arrays.asList(origin, vector));

            default -> throw new Error("unable to find infix " + name);
        };
    }
    public static FinalSyntaxNode prefix(String name, FinalSyntaxNode origin) {
        return switch (name) {
            case "+"        ->origin;
            case "-"        ->new Negative(origin);
            case "not","!"  ->new Not(origin);
            case "$not"     ->new BitNot(origin);
            case "#"        ->new Cardinal(origin);
            default -> throw new Error("unable to find prefix " + name);
        };
    }
    public static FinalSyntaxNode suffix(String name, FinalSyntaxNode origin) {
        return switch (name) {
            case "!"    ->new Factorial(origin);
            default -> throw new Error("unable to find prefix " + name);
        };
    }

    public Usage getUsage() {
        return Usage.OPERATOR;
    }


    public FinalSyntaxNode getDeclaredType() {
        return getOrigin().getDeclaredType();
    }

    public void evaluate(){}
}
