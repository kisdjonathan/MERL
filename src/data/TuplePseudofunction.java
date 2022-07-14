package data;

import java.util.Collection;

public class TuplePseudofunction extends Function{
    private class PartialFunction {
        public int index = 0;
        public Function conversion = null;
        public PartialFunction(){}
        public PartialFunction(int index, Function conversion) {
            this.index = index;
            this.conversion = conversion;
        }
    }

    private Collection<PartialFunction> conversions;
}
