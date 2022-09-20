package data;

import baseTypes.BasicType;
import derivedAST.FinalSyntaxNode;

public class TypeSize implements Comparable{
    public TypeSize() {
        //TODO bytes defaults to pointer
    }
    public TypeSize(int bytes) {
        //TODO
    }
    public TypeSize(FinalSyntaxNode bytes) {
        //TODO
    }

    public int compareTo(Object o) {
        if(o instanceof Integer)
            return 0;   //TODO
        else if(o instanceof TypeSize)
            return 0;   //TODO
        else if(o instanceof BasicType)
            return 0;   //TODO
        throw new Error("TypeSize can not be compared to " + o);
    }
}
