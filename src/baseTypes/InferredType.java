package baseTypes;

import derivedAST.FinalSyntaxNode;
import derivedAST.Function;
import derivedAST.RelativeFunction;
import derivedAST.RelativeVariable;

import java.util.*;

public class InferredType extends BasicType{
    public String getName() {
        return "inferred";
    }

    private Map<Integer, RelativeVariable> indices = null;
    public int indexCount() {
        return Integer.MAX_VALUE;   //allow for all forms of indexing
    }
    public RelativeVariable getIndex(int i) {
        if(indices == null)
            indices = new HashMap<>();
        if(indices.containsKey(i))
            return indices.get(i);
        return indices.put(i, new RelativeVariable("__unnamed__"));
    }

    private Map<String, RelativeVariable> fields = null;
    public RelativeVariable getField(String name) {
        return fields.containsKey(name) ? fields.get(name) : fields.put(name, new RelativeVariable(name));
    }
    public List<FinalSyntaxNode> getFields() {
        return new ArrayList<>(fields.values());
    }

    private Map<String, List<RelativeFunction>> methods = null;
    public RelativeFunction getMethod(Signature signature) {
        if(methods.containsKey(signature.getName())) {
            for (RelativeFunction f : methods.get(signature.getName()))
                if (f.typeEquals(signature))
                    return f;
        }
        else
            methods.put(signature.getName(), new ArrayList<>());

        RelativeFunction ret = new RelativeFunction(signature.getName(), signature.getArgs(), signature.getRets());
        methods.get(signature.getName()).add(ret);
        return ret;
    }
    public Collection<Function> getMethods() {
        Collection<Function> ret = new HashSet<>();
        for(List<RelativeFunction> t : methods.values())
            ret.addAll(t);
        return ret;
    }

    public boolean typeEquals(FinalSyntaxNode other) {
        return other.getBaseType().typeContains(this);
    }

    public TypeSize getByteSize() {
        //TODO L
        TypeSize ret = new TypeSize(0);
        for(RelativeVariable t : indices.values())
            ret = TypeSize.add(ret, t.getBaseType().getByteSize());
        return ret;
    }
    public FinalSyntaxNode newInstance(String s) {
        throw new Error("unable to create new inferred instance as a literal (from " + s + ")");
    }
}
