import derivedAST.FunctionDefinition;
import baseTypes.Tuple;

//Program represents the entire program (all system arguments, settings, and all sources)
//TODO complete
public class Program extends FunctionDefinition {
    public Program(){
        super(new Tuple(), new Tuple(), null);  //TODO make args be sysArgs, and return be int, and definition somehow accessible and modifiable
    }
    //TODO settings
}
