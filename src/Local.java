import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Local contains localized variables
public class Local extends Group{
    private Map<String, Identifier> variables = new HashMap<>();
    private SyntaxNode process = null;

    public Local(SyntaxNode definition) {
        process = definition;
    }

    public SyntaxNode getProcess() {
        return process;
    }

    public Identifier getVariable(String name) {
        return variables.get(name);
    }
    public void putVariable(String name, Identifier value) {
        variables.put(name, value);
    }
}
