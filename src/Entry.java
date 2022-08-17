import derivedAST.Variable;

import java.io.File;
import java.util.*;

//entry point of the program
//TODO complete
public class Entry {
    public static void main(String[] args) {
        Queue<String> paths = new LinkedList<>(Arrays.asList(args));
        //TODO sort out optional args

        List<PrecompiledSource> dependencies = new ArrayList<>();
        List<SourceFile> sources = new ArrayList<>();

        Map<String, Variable> undefinedVariables = new HashMap<>();
        Map<String, Variable> imports = new HashMap<>();
        Map<String, Variable> exports = new HashMap<>();

        Program build = new Program();

        for(String path : paths) {
            SourceFile source = new SourceFile(new File(path), build);
            var thisExports = source.getExports();
            var thisImports = source.getImports();

            //TODO for every export and import of this, add appropriately to global export and imports
        }

        for(var entry : exports.entrySet()) {
            build.putVariable(entry.getValue());    //TODO check if affects existing local entry?
        }
        for(SourceFile source : sources)
            source.analyze();
        //TODO find dependencies based on missing imports

        TokenWriter output = new TokenWriter(new File(build.getName()));
    }
}
