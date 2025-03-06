package avrel.lambda;

public class Lambda {
    public static void main(String[] args) {
        int random = 21;

        LambdaInterface lambda = (numbers -> {
            int sum = 0;
            for (int i : numbers) {
                sum += i;
            }

            sum += random;

            return sum;
        });

        int[] digits = {1,2,3,4,5};

        System.out.println(lambda.calculate(digits));

        lambda.displaySomething();

        System.out.println(LambdaInterface.sayHiToPeople());

        System.out.println(lambda.sayByToSomeone("friend"));



        LambdaReferenceInterface lambdaReference = System.out::println; // ce tare e asta

        String display = "display this right now !!!!!";
        lambdaReference.display(display);




        LambdaConstructorReferenceInterface lambda2 = String::new;
        System.out.println("Yo, " + lambda2.word("Aurel"));
    }

//    public static void lambda() {
//        LambdaInterface lambda = (numbers -> {
//            int sum = 0;
//            for (int i : numbers) {
//                sum += i;
//            }
//
//            return sum;
//        });
//
//        int[] digits = {1,2,3,4,5};
//
//        System.out.println(lambda.calculate(digits));
//
//        lambda.displaySomething();
//
//        System.out.println(LambdaInterface.sayHiToPeople());
//
//        System.out.println(lambda.sayByToSomeone("friend"));
//    }
}
