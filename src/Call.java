//Call stores information of a function call
//TODO complete
public class Call extends Operator{
    private String func = null;

    public Call(){
        super("call");
    }
    public Call(String name){
        this();
        func = name;
    }

    public Usage getUsage() {
        return Usage.CALL;
    }
}
