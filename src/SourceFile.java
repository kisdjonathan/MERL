import java.io.File;
import java.util.HashMap;
import java.util.Map;

//SourceFile represents a single file of the program
//TODO complete
public class SourceFile extends FunctionDefinition {
    private Map<String, Identifier> exports = new HashMap<>();
    private Map<String, Identifier> imports = new HashMap<>();

    public SourceFile(File sourceFile) {
        super(new Tuple(), new Tuple(), null); //TODO this is a placeholder, so find a better constructor or better superClass
        //TODO get all exports, imports, settings, and some literals and operations
    }

    public void analyze() {
        //TODO get all variables, determine usage of groups
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
