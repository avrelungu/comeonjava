package avrel.trywithresources;

public class Resource implements AutoCloseable{

    private String name;
    private boolean throwException;

    Resource(String name, boolean throwException) {
        this.name = name;
        this.throwException = throwException;
    }

    public void doSome(boolean throwException) throws Exception {
        System.out.println("Resource " + this.name + " doing something");
        if(throwException) {
            throw new Exception("Error when calling doSome() on resource " + this.name);
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println(this.name + " closed.");
        if (this.throwException) {
            throw new Exception(this.name + " throws an error when close.");
        }
    }


}
