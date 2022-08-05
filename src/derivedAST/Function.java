package derivedAST;

import data.Signature;
import data.Type;

public class Function extends Variable {
    public Function(String name, Signature type) {
        super(name, type);
    }

    public Function(String name) {
        super(name);
    }

    public Signature getType() {
        return (Signature) super.getType();
    }
}
