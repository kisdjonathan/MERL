package baseAST;

import data.Type;
import data.Usage;

//used only in the non-final AST in chained operators
public class ChainedOperator extends Operator {
    public class OperatorPlaceholder extends SyntaxNode {
        public OperatorPlaceholder(){}
        public OperatorPlaceholder(String name){this.name = name;}

        private String name = null;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public Usage getUsage() {
            return null;
        }

        public Type getType() {
            return null;
        }
    }

    public ChainedOperator(){}
    public ChainedOperator(String name){
        super(name);
    }

    public boolean isChained() {
        return true;
    }

    public void addOperator(String op) {
        addChild(new OperatorPlaceholder(op));
    }
}
