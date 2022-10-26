package operations.unifix;

import derivedAST.FinalSyntaxNode;

public class Not extends BooleanUnifix {
    public Not(){}
    public Not(FinalSyntaxNode value) {
        setOrigin(value);
    }

    public String getName() {
        return "not";
    }
}
