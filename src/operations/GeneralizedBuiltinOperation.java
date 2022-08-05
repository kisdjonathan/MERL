package operations;

public abstract class GeneralizedBuiltinOperation extends BuiltinOperation{
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
