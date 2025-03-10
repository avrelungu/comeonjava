package avrel.streamsv2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsV2 {

    record Person (String name, int age, String department) {}

    public static void main (String[] args) {
        // Sample data
        List<String> words = List.of("apple", "banana", "cherry", "date", "elderberry", "fig", "grape");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Person> employees = List.of(
                new Person("Alice", 30, "Engineering"),
                new Person("Bob", 25, "Marketing"),
                new Person("Charlie", 35, "Engineering"),
                new Person("Diana", 28, "HR"),
                new Person("Eve", 40, "Marketing"),
                new Person("Frank", 22, "Engineering")
        );

        // 1. Basic Stream Creation
        Stream<String> wordsStreamFromCollection = words.stream();
        Stream<String> wordsStreamFromParallel = words.parallelStream();
        Stream<Integer> streamFromInteger = Arrays.stream(new Integer[]{1, 2, 3});
        Stream<String> streamFromStringValues = Stream.of("a", "b");
        Stream<Integer> emptyStream = Stream.empty();

        // 2. Infinite Streams
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 1);
        Stream<Integer> limitedInfiniteStream = infiniteStream.limit(10);
        Stream<Double> randomStream = Stream.generate(Math::random).limit(5);

        // 3. Basic Operations
        long count = words.stream().count();
        System.out.println("Count words stream: " + count);

        List<String> uppercaseWords = words.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("Uppercase words " + uppercaseWords);

        // 4. Filtering
        List<String> longWords = words.stream()
                .filter(word -> word.length() > 5)
                .toList();
        System.out.println("Long words" + longWords);

        // 5. Filtering
        Optional<String> firstLongWord = words.stream()
                .filter(word -> word.length() > 5)
                .findFirst();

        System.out.println("First long word: " + firstLongWord);

        Optional<String> anyLongWord = words.stream()
                .filter(word -> word.length() > 5)
                .findAny();
        System.out.println("Any long word: " + firstLongWord);

        // 6. Matching operations
        boolean allLongerThanTwo = words.stream()
                        .allMatch(word -> word.length() > 5);
        System.out.println("All words Longer than two: " + allLongerThanTwo);

        boolean anyStartsWithA = words.stream()
                .anyMatch(word -> word.startsWith("A") || word.startsWith("a"));
        System.out.println("Any word starts with A/a: " + anyStartsWithA);

        boolean noneStartWithZ = words.stream()
                .noneMatch(word -> word.startsWith("Z") || word.startsWith("z"));
        System.out.println("Not a word starts with Z/z: " + noneStartWithZ);

        // 7. Reducing
        Optional<Integer> sum = numbers.stream()
                .reduce(Integer::sum);
        System.out.println("Sum of numbers: " + sum.orElse(0));

        int sumWithIdentity = numbers.stream()
                .reduce(10, Integer::sum);;
        System.out.println("Sum of numbers with identity: " + sumWithIdentity);

         String concatenated = words.stream()
                .reduce("", (a, b) -> a + "," + b );
         System.out.println("Concatenated words: " + concatenated);

        // 8. Collecting results
//        Set<String> wordSet = new HashSet<String>(words);
        Set<String> wordSet = words.stream().collect(Collectors.toSet());

        System.out.println("Set of words: " + wordSet);

        var joinedSet = words.stream()
                .collect(Collectors.joining(", "));

        System.out.println("Joined words: " + joinedSet);

        // 9. Grouping and partitioning
        Map<Integer, List<String>> groupedByLength = words.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Words grouped by length: " + groupedByLength);

        Map<Boolean, List<String>> partitionedByLength = words.stream()
                .collect(Collectors.partitioningBy(word -> word.length() > 5));
        System.out.println("Words partitioned by length: " + partitionedByLength);

        // 10. Statistics
        IntSummaryStatistics stats = numbers.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        System.out.println("Stats: " + stats);
    }
}
