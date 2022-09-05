package baseTypes;

import derivedAST.Field;
import derivedAST.FinalSyntaxNode;
import operations.Index;

import java.util.*;

//TODO Tuple, except with named fields
public class Structure extends Tuple {
    private Map<String, Integer> namePositions = new HashMap<>();

    public FinalSyntaxNode removeChild(String name) {
        return removeChild(namePositions.get(name));
    }
    public void addChild(String name, FinalSyntaxNode child) {
        addChild(child);
        namePositions.put(name, namePositions.size());
    }
    public void addChild(FinalSyntaxNode child) {
        addChild(String.valueOf(namePositions.size()), child);
    }

    public FinalSyntaxNode getChild(String name) {
        return getChild(namePositions.get(name));
    }
    public FinalSyntaxNode setChild(String name, FinalSyntaxNode val) {
        return setChild(namePositions.get(name), val);
    }

//    public Collection<Pair> pairFieldsIn(FinalSyntaxNode ref, FinalSyntaxNode t) {
//        if(size() > t.size())
//            return null;
//        List<Pair> ret = new ArrayList<>();
//        for(String s : namePositions.keySet())
//            ret.add(new Pair(new Field(ref, s), new Field(t, s)));
//        return  ret;
//    }
}
