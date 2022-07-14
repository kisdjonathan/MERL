package data;

//Function signature
//TODO use composition for ret and a tuple for args
public class Signature extends Type {
    Type functionReturn = null;
    Type functionParameter = null;

    public Signature(Type ret, Type param){
        functionReturn = ret;
        functionParameter = param;
    }
    public Signature(String name, Type ret, Type param){
        setName(name);
        functionReturn = ret;
        functionParameter = param;
    }

    public void setReturn(Type v) {
        functionReturn = v;
    }
    public void setParameter(Type v) {
        functionParameter = v;
    }

    public Type getReturn() {
        return functionReturn;
    }
    public Type getParameter() {
        return functionParameter;
    }
}
