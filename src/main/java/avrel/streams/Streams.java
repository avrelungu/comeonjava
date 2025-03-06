package avrel.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {
        List<String> things = new ArrayList<>();

        things.add("car");
        things.add("plane");

        Stream<String> streamString = things.stream();


        List<Integer> numbers = new ArrayList<>();

        numbers.add(21);
        numbers.add(8);

        Stream<Integer> streamInteger = numbers.stream();


        streamString.map(String::toUpperCase).forEach(System.out::println);

        int[] digits = {1, 2, 3, 4};
        IntStream digitsStream = Arrays.stream(digits);
        System.out.println(digitsStream.average());

        Integer[] digits2 = {1, 1, 2, 2, 3, 4};
        Stream<Integer> digitsStream2 = Arrays.stream(digits2);
        digitsStream2.map((digit) -> Math.pow(digit, 2)).distinct().limit(3).forEach(System.out::println);

        String[] names = {"Aurel", "Lungu"};
        Stream<String> namesStream = Arrays.stream(names);
        Stream<String> namesStream2 = namesStream.map(String::toLowerCase);

        namesStream2.filter((name) -> name.startsWith("a")).forEach(System.out::println);





        List<String> stringList = new ArrayList<String>();

        stringList.add("One flew over the cuckoo's nest");
        stringList.add("To kill a muckingbird");
        stringList.add("Gone with the wind");

        Stream<String> stream = stringList.stream();

        stream.flatMap((value) -> {
            String[] split = value.split(" ");
            return (Stream<String>) Arrays.stream(split);
        }).forEach(System.out::println);


        List<String> stringPeekList = new ArrayList<String>();

        stringPeekList.add("abc");
        stringPeekList.add("def");

        Stream<String> streamPeek = stringPeekList.stream();

        Stream<String> streamPeeked = streamPeek.peek((value) -> {
            System.out.println("value");
        });

        streamPeeked.forEach(System.out::println);

    }
}
