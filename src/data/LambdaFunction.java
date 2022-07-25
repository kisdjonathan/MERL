package data;

import baseAST.SyntaxNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//TODO complete
public class LambdaFunction extends Function{
    private class PartialFunction {
        public int index = 0;
        public Function conversion = null;
        public PartialFunction(){}
        public PartialFunction(int index, Function conversion) {
            this.index = index;
            this.conversion = conversion;
        }
    }

    private List<SyntaxNode> components = new ArrayList<>();

    public void putComponent(SyntaxNode component) {
        components.add(component);
    }
}
