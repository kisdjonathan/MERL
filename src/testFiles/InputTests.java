package testFiles;

import baseAST.*;
import baseTypes.Int;
import baseTypes.Str;
import compiler.TokenReader;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;

public class InputTests {
    @Test (timeout = 5000)
    public void operatorTest() {
        Literal l1 = new Literal("1", new Int()),
                l2 = new Literal("2", new Int()),
                l3 = new Literal("3", new Int()),
                l4 = new Literal("4", new Int()),
                l5 = new Literal("3", new Int()),
                l6 = new Literal("2", new Int());
        Operator o1 = new Operator("+"),
                o2 = new Operator("-"),
                o3 = new Operator("/"),
                o4 = new Operator("-"),
                o5 = new Operator("^");
        Group g1 = new Group("()");
        Group expected = new Group("EOF");

        o1.addChild(l1); o1.addChild(l2);
        o4.addChild(l4); o4.addChild(l5);
        o5.addChild(g1); o5.addChild(l6);
        o3.addChild(l3); o3.addChild(o5);
        o2.addChild(o1); o2.addChild(o3);
        g1.setBody(o4);
        expected.setBody(o2);

        TokenReader reader = new TokenReader(new File("src/testFiles/OperatorTest.txt"));
        Group read = reader.readGroup("");

        assertEquals("operator case failed", expected, read);
    }

    @Test (timeout = 5000)
    public void chainOperatorTest() {
        TokenReader reader = new TokenReader(new File("src/testFiles/ChainedOperatorTest.txt"));
        Literal l1 = new Literal("1", new Int());
        Literal l2 = new Literal("5", new Int());
        ChainedOperator o1 = new ChainedOperator();
        Group g1 = new Group("(]");
        Group expected = new Group("EOF");

        o1.addChild(l1); o1.addChild(",", l2); o1.addChild(",", l1);
        g1.setBody(o1);
        expected.setBody(g1);

        Group read = reader.readGroup("");

        assertEquals("chain case failed", expected, read);
    }

    @Test (timeout = 5000)
    public void assignTest() {
        TokenReader reader = new TokenReader(new File("src/testFiles/AssignTest.txt"));

        Operator line1 = new Operator("="),
                line2 = new Operator("="),
                line3 = new Operator("=");

        Identifier i1 = new Identifier("a"),
                i2 = new Identifier("b"),
                i3 = new Identifier("c");

        SyntaxNode val1 = new Operator("+") {{addChild(new Literal("1", new Int())); addChild(new Literal("2", new Int()));}},
                val2 = i1,
                val3 = new Literal("word", new Str());

        line1.addChild(i1); line1.addChild(val1);
        line2.addChild(i2); line2.addChild(val2);
        line3.addChild(i3); line3.addChild(val3);

        ChainedOperator c1 = new ChainedOperator();
        c1.addChild(line1); c1.addChild(";", line2); c1.addChild(";", line3); c1.addOperator(";");

        Group expected = new Group("EOF");  expected.setBody(c1);
        Group read = reader.readGroup("");

        assertEquals("assign case failed", expected, read);
    }

    @Test (timeout = 5000)
    public void controlTest() {
        TokenReader reader = new TokenReader(new File("src/testFiles/ControlTest.txt"));

        Operator line1 = new Operator("=");
        Control line2 = new Control("while");

        Identifier i1 = new Identifier("a");

        Literal l1 = new Literal("3", new Int()),
                l2 = new Literal("1", new Int()),
                l3 = new Literal("999", new Int());

        Operator o1 = new Operator("-");
        o1.addChild(i1);    o1.addChild(l2);
        Operator o2 = new Operator("=");
        o2.addChild(i1); o2.addChild(o1);
        Operator o3 = new Operator("=");
        o3.addChild(i1); o3.addChild(l3);

        line1.addChild(i1); line1.addChild(l1);
        line2.setControl(i1); line2.setBody(o2);
        line2.addNelse(null, o3);

        Operator c1 = new Operator(";");
        c1.addChild(line1); c1.addChild(line2);

        Group expected = new Group("EOF");  expected.setBody(c1);
        Group read = reader.readGroup("");

        assertEquals("control case failed", expected, read);
    }

    @Test (timeout = 5000)
    public void fieldTest() {
        TokenReader reader = new TokenReader(new File("src/testFiles/FieldTest.txt"));

        Literal l1 = new Literal("12345", new Str());
        Identifier i1 = new Identifier("upper");
        Group g1 = new Group("()");

        Consecutive f1 = new Consecutive(i1, g1);
        Consecutive f2 = new Consecutive(l1, f1);

        Group expected = new Group("EOF");  expected.setBody(f2);
        Group read = reader.readGroup("");

        assertEquals("field case failed", expected, read);
    }
}
