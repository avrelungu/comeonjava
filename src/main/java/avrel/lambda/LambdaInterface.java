package avrel.lambda;

public interface LambdaInterface {
    public int calculate(int[] numbers);

    default public void displaySomething() {
        System.out.println("avrel");
    }

    default public String sayByToSomeone(String someone) {
        return "Bye im gonna miss you " + someone;
    }

    public static String sayHiToPeople() {
        return "Hi, colegi de planeta, this is me, your boy.";
    }
}
