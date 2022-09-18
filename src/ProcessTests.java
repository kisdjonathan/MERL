import baseAST.Group;
import baseTypes.Int;
import baseTypes.RangeEI;
import baseTypes.Str;
import baseTypes.Tuple;
import compiler.*;
import derivedAST.Field;
import operations.BuiltinOperation;
import operations.controls.ControlStructure;
import operations.controls.While;
import derivedAST.FinalSyntaxNode;
import derivedAST.Variable;
import operations.arithmetic.Add;
import operations.arithmetic.Divide;
import operations.arithmetic.Exponent;
import operations.arithmetic.Subtract;
import operations.Assign;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;

//TODO
public class ProcessTests {

    @Test (timeout = 5000)
    public void operatorTest() {
        SourceFile expected = new SourceFile();

        Int n1 = new Int(1),
                n2 = new Int(2),
                n3 = new Int(3),
                n4 = new Int(4),
                n5 = new Int(3),
                n6 = new Int(2);

        BuiltinOperation op1 = new Add(),
                op2 = new Subtract(),
                op3 = new Divide(),
                op4 = new Subtract(),
                op5 = new Exponent();

        op1.setOrigin(n1);  op1.setVector(n2);
        op4.setOrigin(n4);  op4.setVector(n5);
        op5.setOrigin(op4); op5.setVector(n6);
        op3.setOrigin(n3);  op3.setVector(op5);
        op2.setOrigin(op1); op2.setVector(op3);

        expected.setBody(op2);

        TokenReader reader = new TokenReader(new File("src/InputTest/OperatorTest.txt"));
        Group read = reader.readGroup("");
        FinalSyntaxNode actual = read.getEvaluatedReplacement();

        assertEquals("operator case failed", expected, actual);
    }

    @Test (timeout = 5000)
    public void chainOperatorTest() {
        SourceFile expected = new SourceFile();

        Int n1 = new Int(1),
                n2 = new Int(1),
                n3 = new Int(1);

        FinalSyntaxNode body = new RangeEI(1, 1, 1);
        expected.setBody(body);

        TokenReader reader = new TokenReader(new File("src/InputTest/ChainedOperatorTest.txt"));
        Group read = reader.readGroup("");
        FinalSyntaxNode actual = read.getEvaluatedReplacement();

        assertEquals("chained operator case failed", expected, actual);
    }

    @Test (timeout = 5000)
    public void assignTest() {
        SourceFile expected = new SourceFile();

        Int n1 = new Int(1),
                n2 = new Int(2);
        Str s1 = new Str("word");

        BuiltinOperation op1 = new Assign(),
                op2 = new Add(),
                op3 = new Assign(),
                op4 = new Assign();

        Tuple body = new Tuple();

        Variable v1 = expected.putVariable("a"),
                v2 = expected.putVariable("b"),
                v3 = expected.putVariable("c");

        v1.setDeclaredType(new Int());
        v2.setDeclaredType(v1);
        v3.setDeclaredType(new Str());

        op2.setOrigin(n1);  op2.setVector(n2);
        op1.setOrigin(v1);  op1.setVector(op2);
        op3.setOrigin(v2); op3.setVector(v1);
        op4.setOrigin(v3);  op3.setVector(s1);

        body.addIndex(op1);
        body.addIndex(op3);
        body.addIndex(op4);

        expected.setBody(body);

        TokenReader reader = new TokenReader(new File("src/InputTest/AssignTest.txt"));
        Group read = reader.readGroup("");
        FinalSyntaxNode actual = read.getEvaluatedReplacement();

        assertEquals("assign case failed", expected, actual);
    }

    @Test (timeout = 5000)
    public void controlTest() {
        SourceFile expected = new SourceFile();

        Int n1 = new Int(3),
                n2 = new Int(1),
                n3 = new Int(-999);

        BuiltinOperation op1 = new Assign(),
                op2 = new Assign(),
                op3 = new Subtract(),
                op4 = new Assign();

        ControlStructure c1 = new While();

        Tuple body = new Tuple();

        Variable v1 = expected.putVariable("a");
        v1.setDeclaredType(new Int());

        op1.setOrigin(v1);  op1.setVector(n1);
        op3.setOrigin(v1);  op3.setVector(n2);
        op2.setOrigin(v1);  op2.setVector(op2);
        op4.setOrigin(v1); op4.setVector(n3);

        c1.setOrigin(v1);
        c1.setVector(op2);
        c1.addNelse(null, op4);

        body.addIndex(op1);
        body.addIndex(c1);

        expected.setBody(body);

        TokenReader reader = new TokenReader(new File("src/InputTest/ControlTest.txt"));
        Group read = reader.readGroup("");
        FinalSyntaxNode actual = read.getEvaluatedReplacement();

        assertEquals("control case failed", expected, actual);
    }

    @Test (timeout = 5000)
    public void fieldTest() {
        SourceFile expected = new SourceFile();

        Str s1 = new Str("12345");
        FinalSyntaxNode f1 = s1.getBaseType().getField("length");

        Field op1 = new Field(s1, f1);

        expected.setBody(op1);

        TokenReader reader = new TokenReader(new File("src/InputTest/FieldTest.txt"));
        Group read = reader.readGroup("");
        FinalSyntaxNode actual = read.getEvaluatedReplacement();

        assertEquals("assign case failed", expected, actual);
    }
}
