package derivedAST;

import baseAST.Group;
import baseAST.SyntaxNode;
import data.*;
import static data.Type.TypeConversion;

import java.util.*;

//derivedAST.Local contains localized variables
//TODO complete
public class Local extends Group {
    private Map<String, Variable> variables = new HashMap<>();
    private Map<String, Collection<Function>> functions = new HashMap<>();
    private SyntaxNode process = null;

    public Local(SyntaxNode definition) {
        process = definition;
    }

    public SyntaxNode getProcess() {
        return process;
    }

    //true if source or dest are null
    public TypeConversion match(Type source, Type dest) {
        //no conversion needed (0)
        if(source == null || dest == null || source.equals(dest))
            return new TypeConversion(0, null);

        //direct conversion (1)
        Function directConversion = getFunction(new Signature(dest, source));
        if(directConversion != null)
            return new TypeConversion(1, directConversion);


        //TODO determine if source can be converted to dest and return MAX/2/3 for no/expand/series convertion
        //TODO implement for structures and function signatures

        return new TypeConversion();
    }

    public Variable getVariable(String name) {
        return variables.get(name);
    }
    public void putVariable(String name, Variable value) {
        variables.put(name, value);
    }

    public TypeConversion getFunctionWithConversion(Signature name) {
        TypeConversion ret = new TypeConversion();
        for(Function func : functions.get(name.getName())) {
            TypeConversion conversion = match(name, func.getType());
            if(conversion.distance <= ret.distance) //<= because later-defined conversions with the same distance hold more significance
                ret = conversion;
        }

        if(getParent() != null) {
            TypeConversion alternate = getParent().getFunctionWithConversion(name);
            if(alternate.distance < ret.distance)
                return alternate;
        }
        return ret;
    }
    public void putFunction(String name, Function value) {
        if(!functions.containsKey(name))
            functions.put(name, new ArrayList<>());
    }
}
