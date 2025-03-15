package avrel.streamsv2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.*;

public class StreamsV2 {

    record Person(String name, int age, String department) {
    }

    public static void main(String[] args) {
        // Sample data
        List<String> words = List.of("banana", "apple", "cherry", "date", "elderberry", "fig", "grape");
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
                .reduce(10, Integer::sum);
        ;
        System.out.println("Sum of numbers with identity: " + sumWithIdentity);

        String concatenated = words.stream()
                .reduce("", (a, b) -> a + "," + b);
        System.out.println("Concatenated words: " + concatenated);

        // 8. Collecting results
//        Set<String> wordSet = new HashSet<String>(words);
        Set<String> wordSet = words.stream().collect(Collectors.toSet());

        System.out.println("Set of words: " + wordSet);

        var joinedSet = String.join(", ", words);

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

        // 11. Distinct Elements
        List<Integer> distinctNumbers = numbers.stream().distinct().toList();
        System.out.println("Distinct numbers: " + distinctNumbers);

        // 12. Sorting
        List<String> sortedWords = words.stream().sorted().toList();
        System.out.println("Sorted words: " + sortedWords);

        List<String> customSortedWords = words.stream()
                .sorted(Comparator.comparing(String::length).thenComparing(Function.identity()))
                .toList();
        System.out.println("Custom sorted words: " + customSortedWords);

        // 13. Limit and skip
        List<String> firstThreeWords = words.stream()
                .limit(3)
                .toList();
        System.out.println("First three words: " + firstThreeWords);

        List<String> afterTwoWords = words.stream()
                .skip(2)
                .toList();
        System.out.println("After two words: " + afterTwoWords);

        // 14. Peek (for debugging)
        List<String> peekExample = words.stream()
                .filter(word -> word.length() > 4)
                .peek(word -> System.out.println("Filtered: " + word))
                .map(String::toUpperCase)
                .peek(word -> System.out.println("Mapped: " + word))
                .collect(Collectors.toList());
        System.out.println(peekExample);

        // 15. FlatMap
        List<List<Integer>> nestedNumbers = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );

        List<Integer> flatMap = nestedNumbers.stream()
                .flatMap(Collection::stream)
                .toList();
        System.out.println("Flat mapped numbers from nested: " + flatMap);

        // 16. Convert to primitive streams - e util sa convertesti in stream-uri de primitive pentru ca e mai eficient. practic scapi de un layer de abstractizare si lucrezi cu ceva specific. astfel ai si la indemana metode predefinite precum average(), sum() etc.; si summaryStatistics()
        IntStream intStream = numbers.stream().mapToInt(Integer::intValue);
        DoubleStream doubleStream = numbers.stream().mapToDouble(Integer::doubleValue);
        LongStream longStream = numbers.stream().mapToLong(Integer::longValue);

        intStream.forEach(System.out::println);
        doubleStream.forEach(System.out::println);
        longStream.forEach(System.out::println);

        // 17 Parallel Stream
        List<Integer> parallelStream = numbers.parallelStream()
                .map(n -> n * 2)
//                .peek(n -> System.out.println("Parallel Stream: " + n))
                .toList();
        System.out.println(parallelStream);

        // 18. File operations with stream
        try {
            Stream<String> fileLines = Files.lines(Path.of("src/main/java/avrel/streamsv2/example.txt"));
            Stream<Path> files = Files.list(Path.of("."));
            Stream<Path> recursiveFiles = Files.walk(Path.of("."), 5);
        } catch (IOException e) {
            System.out.println("File operations threw exception: " + e.getMessage());
        }

        // 19. Collectors Group by number of
        Map<String, Long> downstreamCollectors = employees.stream()
                .collect(Collectors.groupingBy(
                        Person::department,
                        Collectors.counting()
                ));

        System.out.println(downstreamCollectors);

        // 20. Group collectors with filter
        Map<String, List<String>> youngByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Person::department,
                        Collectors.filtering(p -> p.age < 35, Collectors.mapping(Person::name, Collectors.toList()))
                ));
        System.out.println(youngByDept);

        // 21. Group Collectors with Mapping
        Map<String, List<String>> namesByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Person::department,
                        Collectors.mapping(Person::name, Collectors.toList())
                ));
        System.out.println(namesByDept);


        // 22. Teeing
        Double meanAndSum = numbers.stream().collect(Collectors.teeing(
                Collectors.averagingDouble(Integer::doubleValue),
                Collectors.summingDouble(Integer::doubleValue),
                Double::sum
        ));
        System.out.println("Avg + Sum =" + meanAndSum);

        // 23. Collector with custom implementation
        Collector<Person, ?, List<String>> allProgrammers = Collectors.filtering(
                (p) -> p.department().equals("Engineering"),
                Collectors.mapping(Person::name, Collectors.toList())
        );

        List<String> engineers = employees.stream().collect(allProgrammers);
        System.out.println("Custom filtered engineers: " + engineers);

        // 24. takeWhile
        List<Integer> takeWhile = numbers.stream()
                .takeWhile(n -> n < 5)
                .toList();
        System.out.println(takeWhile);

        // 25. Stream iterate with predicate
        Stream<Integer> iterateWithPredicate = Stream.iterate(1, n -> n < 10, n -> n * 2);
        System.out.println("Iterate with Predicate: " + iterateWithPredicate.toList());

        // 26. String.lines()
        String multilineString = "Line 1\nLine 2\nLine 3";
        long countLines = multilineString.lines().count();
        System.out.println(countLines);

        // 27. Stream.builder pattern
        Stream<String> builder = Stream.<String>builder()
                .add("Aurel")
                .add("Java Programmer")
                .add("Yessir")
                .build();
        System.out.println("Builder: " + builder.toList());

        // 28. Handling Stream with Optional
        Optional<String> firstWord = words.stream()
                .filter(word -> word.startsWith("b"))
                .findFirst();

        System.out.println(firstWord.isEmpty() ? "Not found" : firstWord);

        // 29. Stream with mapping
        List<String> wordsMapped = employees.stream()
                .collect(Collectors.filtering(
                        (p) -> Objects.toString(p.name).contains("a"),
                        Collectors.mapping(Person::name, Collectors.toList())
                ));
        System.out.println("Words mapped: " + wordsMapped);

        // 30. Streams concat
        Stream<String> concatStreams = Stream.concat(
                Stream.of("Damn", "Before"),
                Stream.of("Brave", "Who else")
        );

        System.out.println("Concat Streams: " + concatStreams.toList());

        // 31. Multi-level reduction with Streams
        Map<String, String> namesByDeptJoined = employees.stream()
                .collect(Collectors.groupingBy(
                        Person::department,
                        Collectors.mapping(
                                Person::name,
                                Collectors.joining(", ")
                        )
                ));
        System.out.println("Names by department joined with ',': " + namesByDeptJoined);

        // 32. Sum
        int total = employees.stream().mapToInt(Person::age).sum();
        System.out.println("Total sum: " + total);

        // 33. Using stream with mixed operations
        List<String> mixedOpsResult = words.stream()
                .filter(word -> word.length() > 5)
                .map(String::toUpperCase)
                .filter(word -> word.toLowerCase().contains("a"))
                .toList();
        System.out.println("Mixed operations:" + mixedOpsResult);

        // 34. Stream with LocalDate
        List<LocalDate> localDate = Stream.of(
                LocalDate.of(2025, Month.AUGUST, 20),
                LocalDate.of(1997, Month.AUGUST, 21)
        ).sorted().toList();

        System.out.println(localDate);
    }
}
