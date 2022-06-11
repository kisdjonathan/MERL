import java.util.Map;

public class LocalLevel extends Token{
    private Map<String, Variable> variables;

    public Variable getVariable(String name) {
        return variables.get(name);
    }
    public void putVariable(String name, Variable value) {
        variables.put(name, value);
    }
}
