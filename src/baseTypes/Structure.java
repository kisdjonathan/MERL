package baseTypes;

import derivedAST.FinalSyntaxNode;

import java.util.*;

//TODO Tuple, except with named fields
public class Structure extends Tuple {
    private Map<String, Integer> namePositions = new HashMap<>();

    public FinalSyntaxNode removeChild(String name) {
        return removeIndex(namePositions.get(name));
    }
    public void addChild(String name, FinalSyntaxNode child) {
        addIndex(child);
        namePositions.put(name, namePositions.size());
    }
    public void addIndex(FinalSyntaxNode child) {
        addChild(String.valueOf(namePositions.size()), child);
    }

    public FinalSyntaxNode getChild(String name) {
        return getIndex(namePositions.get(name));
    }
    public FinalSyntaxNode setChild(String name, FinalSyntaxNode val) {
        return setIndex(namePositions.get(name), val);
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
