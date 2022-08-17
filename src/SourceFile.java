import baseAST.*;
import data.Usage;
import derivedAST.FunctionDefinition;
import baseTypes.Tuple;
import baseAST.Consecutive;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

//SourceFile represents a single file of the program
//TODO complete
public class SourceFile extends FunctionDefinition {
    private Map<String, Identifier> exports = new HashMap<>();
    private Map<String, Identifier> imports = new HashMap<>();

    public SourceFile(File sourceFile, Program build) {
        super(new Tuple(), new Tuple(), null); //TODO this is a placeholder, so find a better constructor or better superClass
        setParent(build);

        TokenReader reader = new TokenReader(sourceFile);
        SyntaxNode body = reader.readGroup("").getBody();
    }

    public SyntaxNode analyze(SyntaxNode node) {
        if(node.isComplete())
            return node;

        //TODO get all exports, imports, settings, and some literals and operations
        //TODO get all variables, determine usage of groups
        switch (node.getUsage()){
            case PRAGMA:
                //TODO add to settings
                break;
            case IDENTIFIER:
                //TODO handle type
                //TODO determine if ~
                break;
            case CALL:
                //TODO add called function signature to vars
                break;
            case FIELD:
                Consecutive fnode = (Consecutive) node;
                if(fnode.getVector().getUsage() == Usage.GROUP){
                    //TODO handle call/structure/index
                }
                else if(fnode.getOrigin().getUsage() == Usage.GROUP && Literal.isSuffix(fnode.getVector().getName())) {
                    //TODO handle group literal
                }
                else {
                    //TODO field
                }

            case OPERATOR:
                assert node instanceof Operator;
                for (SyntaxNode child : (Operator)node)
                    analyze(child);

                if(((Operator) node).isChained()) {
                    assert node instanceof ChainedOperator;
                    //TODO handle specific chaining (eg comparison chains and tuples)
                }
                //TODO handle type based on oper
                break;
            case GROUP:
                //TODO handle type based on value
            case CONTROL:
        }
        return null;
    }
    public void analyze() {
        analyze(getBody());
    }

    public Map<String, Identifier> getExports() {
        return exports;
    }

    public Map<String, Identifier> getImports() {
        return imports;
    }


    public void write(TokenWriter dest) {
        //TODO
    }
}
