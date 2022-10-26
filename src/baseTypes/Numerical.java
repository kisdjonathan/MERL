package baseTypes;

import derivedAST.FinalSyntaxNode;

public abstract class Numerical extends BasicType {
    private boolean extended = false;

    public boolean isLong() {
        return extended;
    }
    public void setLong(boolean v) {
        extended = v;
    }

    public boolean isNumeric() {
        return true;
    }

    protected abstract int defaultByteSize();

    public TypeSize getByteSize() {
        return new TypeSize((extended ? 2 : 1) * defaultByteSize());
    }
}
