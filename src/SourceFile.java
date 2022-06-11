import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SourceFile extends Token{
    private Map<String, Variable> exports = new HashMap<>();
    private Map<String, Variable> imports = new HashMap<>();

    public SourceFile(File sourceFile, Source master) {
        master.addChild(this);
        //TODO get all exports, imports, settings, and some literals and operations
    }

    public void analyze() {
        //TODO get all variables, determine usage of groups
    }

    public Map<String, Variable> getExports() {
        return exports;
    }

    public Map<String, Variable> getImports() {
        return imports;
    }


    public void write(TokenWriter dest) {
        //TODO
    }
}
