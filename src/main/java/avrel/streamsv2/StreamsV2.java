package avrel.streamsv2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
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
        List<List<Integer>> nestedNumbers = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );
        String multilineString = "Line 1\nLine 2\nLine 3";

        // 1. Basic Stream Creation
        demonstrateBasicStreamCreation(words, new Integer[]{1, 2, 3}, "a", "b");

        // 3. Basic Operations
        demonstrateBasicOperations(words);

        // 4. Filtering
        demonstrateFiltering(words, 5);

        // 6. Matching operations
        demonstrateMatchingOperations(words, 5, "a", "z");

        // 7. Reducing
        demonstrateReducing(words, numbers, 10, "", ",");

        // 8. Collecting results
        demonstrateCollectingResults(words, ", ");

        // 9. Grouping and partitioning
        demonstrateGroupingAndPartitioning(words, 5);

        // 10. Statistics
        demonstrateStatistics(numbers);

        // 11. Distinct Elements
        demonstrateDistinctElements(numbers);

        // 12. Sorting
        demonstrateSorting(words, Comparator.comparing(String::length).thenComparing(Function.identity()));

        // 13. Limit and skip
        demonstrateLimitAndSkip(words, 3, 2);

        // 14. Peek (for debugging)
        demonstratePeek(words, 4);

        // 15. FlatMap
        demonstrateFlatMap(nestedNumbers);

        // 16. Convert to primitive streams
        demonstratePrimitiveStreams(numbers);

        // 17. Parallel Stream
        demonstrateParallelStream(numbers, n -> n * 2);

        // 18. File operations with stream
        demonstrateFileOperations("src/main/java/avrel/streamsv2/example.txt", ".", 5);

        // 19. Collectors Group by number of
        demonstrateGroupingByCount(employees, Person::department);

        // 20. Group collectors with filter
        demonstrateGroupingWithFiltering(employees, Person::department, p -> p.age < 35, Person::name);

        // 21. Group Collectors with Mapping
        demonstrateGroupingWithMapping(employees, Person::department, Person::name);

        // 22. Teeing
        demonstrateTeeing(numbers, Collectors.averagingDouble(Integer::doubleValue),
                Collectors.summingDouble(Integer::doubleValue), Double::sum);

        // 23. Collector with custom implementation
        demonstrateCustomCollector(employees, "Engineering");

        // 24. takeWhile
        demonstrateTakeWhile(numbers, n -> n < 5);

        // 25. Stream iterate with predicate
        demonstrateIterateWithPredicate(1, n -> n < 10, n -> n * 2);

        // 26. String.lines()
        demonstrateStringLines(multilineString);

        // 27. Stream.builder pattern
        demonstrateStreamBuilder("Aurel", "Java Programmer", "Yessir");

        // 28. Handling Stream with Optional
        demonstrateOptionalWithStream(words, "b");

        // 29. Stream with mapping
        demonstrateStreamWithMapping(employees, p -> Objects.toString(p.name).contains("a"), Person::name);

        // 30. Streams concat
        demonstrateStreamsConcat(
                Stream.of("Damn", "Before"),
                Stream.of("Brave", "Who else")
        );

        // 31. Multi-level reduction with Streams
        demonstrateMultiLevelReduction(employees, Person::department, Person::name, ", ");

        // 32. Sum
        demonstrateSum(employees, Person::age);

        // 33. Using stream with mixed operations
        demonstrateMixedOperations(words, 5, "a");

        // 34. Stream with LocalDate
        demonstrateLocalDateStream(
                LocalDate.of(2025, Month.AUGUST, 20),
                LocalDate.of(1997, Month.AUGUST, 21)
        );
    }

    public static void demonstrateBasicStreamCreation(List<String> words, Integer[] intArray, String str1, String str2) {
        Stream<String> wordsStreamFromCollection = words.stream();
        Stream<String> wordsStreamFromParallel = words.parallelStream();
        Stream<Integer> streamFromInteger = Arrays.stream(intArray);
        Stream<String> streamFromStringValues = Stream.of(str1, str2);
        Stream<Integer> emptyStream = Stream.empty();

        System.out.println("Demonstrated basic stream creation");
    }

    public static void demonstrateBasicOperations(List<String> words) {
        long count = words.stream().count();
        System.out.println("Count words stream: " + count);

        List<String> uppercaseWords = words.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("Uppercase words " + uppercaseWords);
    }

    public static int demonstrateFiltering(List<String> words, int minLength) {
        List<String> longWords = words.stream()
                .filter(word -> word.length() > minLength)
                .toList();
        System.out.println("Long words" + longWords);

        return longWords.size();
    }

    public static String demonstrateFirstWordByLength(List<String> words, int length) {
        String firstLongWord = words.stream()
                .filter(word -> word.length() > length)
                .findFirst()
                .orElse(null);

        System.out.println("First long word: " + firstLongWord);

        return firstLongWord;
    }

    public static String demonstrateFindAnyLongWord(List<String> words, int minLength) {
        String anyLongWord = words.stream()
                .filter(word -> word.length() > minLength)
                .findAny()
                .orElse(null);

        System.out.println("Any long word: " + anyLongWord);

        return anyLongWord;
    }

    public static void demonstrateMatchingOperations(List<String> words, int minLength,
                                                     String startLetter, String absentLetter) {
        boolean allLongerThanMinLength = words.stream()
                .allMatch(word -> word.length() > minLength);
        System.out.println("All words Longer than " + minLength + ": " + allLongerThanMinLength);

        boolean anyStartsWithLetter = words.stream()
                .anyMatch(word -> word.startsWith(startLetter.toUpperCase()) ||
                        word.startsWith(startLetter.toLowerCase()));
        System.out.println("Any word starts with " + startLetter + ": " + anyStartsWithLetter);

        boolean noneStartWithAbsentLetter = words.stream()
                .noneMatch(word -> word.startsWith(absentLetter.toUpperCase()) ||
                        word.startsWith(absentLetter.toLowerCase()));
        System.out.println("Not a word starts with " + absentLetter + ": " + noneStartWithAbsentLetter);
    }

    public static void demonstrateReducing(List<String> words, List<Integer> numbers,
                                           int identity, String initString, String delimiter) {
        Optional<Integer> sum = numbers.stream()
                .reduce(Integer::sum);
        System.out.println("Sum of numbers: " + sum.orElse(0));

        int sumWithIdentity = numbers.stream()
                .reduce(identity, Integer::sum);
        System.out.println("Sum of numbers with identity " + identity + ": " + sumWithIdentity);

        String concatenated = words.stream()
                .reduce(initString, (a, b) -> a + delimiter + b);
        System.out.println("Concatenated words: " + concatenated);
    }

    public static void demonstrateCollectingResults(List<String> words, String delimiter) {
        Set<String> wordSet = words.stream().collect(Collectors.toSet());
        System.out.println("Set of words: " + wordSet);

        var joinedSet = String.join(delimiter, words);
        System.out.println("Joined words: " + joinedSet);
    }

    public static void demonstrateGroupingAndPartitioning(List<String> words, int lengthThreshold) {
        Map<Integer, List<String>> groupedByLength = words.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Words grouped by length: " + groupedByLength);

        Map<Boolean, List<String>> partitionedByLength = words.stream()
                .collect(Collectors.partitioningBy(word -> word.length() > lengthThreshold));
        System.out.println("Words partitioned by length > " + lengthThreshold + ": " + partitionedByLength);
    }

    public static void demonstrateStatistics(List<Integer> numbers) {
        IntSummaryStatistics stats = numbers.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        System.out.println("Stats: " + stats);
    }

    public static void demonstrateDistinctElements(List<Integer> numbers) {
        List<Integer> distinctNumbers = numbers.stream().distinct().toList();
        System.out.println("Distinct numbers: " + distinctNumbers);
    }

    public static void demonstrateSorting(List<String> words, Comparator<String> comparator) {
        List<String> sortedWords = words.stream().sorted().toList();
        System.out.println("Sorted words: " + sortedWords);

        List<String> customSortedWords = words.stream()
                .sorted(comparator)
                .toList();
        System.out.println("Custom sorted words: " + customSortedWords);
    }

    public static void demonstrateLimitAndSkip(List<String> words, int limitCount, int skipCount) {
        List<String> limitedWords = words.stream()
                .limit(limitCount)
                .toList();
        System.out.println("First " + limitCount + " words: " + limitedWords);

        List<String> skippedWords = words.stream()
                .skip(skipCount)
                .toList();
        System.out.println("After " + skipCount + " words: " + skippedWords);
    }

    public static void demonstratePeek(List<String> words, int minLength) {
        List<String> peekExample = words.stream()
                .filter(word -> word.length() > minLength)
                .peek(word -> System.out.println("Filtered: " + word))
                .map(String::toUpperCase)
                .peek(word -> System.out.println("Mapped: " + word))
                .collect(Collectors.toList());
        System.out.println(peekExample);
    }

    public static void demonstrateFlatMap(List<List<Integer>> nestedNumbers) {
        List<Integer> flatMap = nestedNumbers.stream()
                .flatMap(Collection::stream)
                .toList();
        System.out.println("Flat mapped numbers from nested: " + flatMap);
    }

    public static void demonstratePrimitiveStreams(List<Integer> numbers) {
        IntStream intStream = numbers.stream().mapToInt(Integer::intValue);
        DoubleStream doubleStream = numbers.stream().mapToDouble(Integer::doubleValue);
        LongStream longStream = numbers.stream().mapToLong(Integer::longValue);

        intStream.forEach(System.out::println);
        doubleStream.forEach(System.out::println);
        longStream.forEach(System.out::println);
    }

    public static void demonstrateParallelStream(List<Integer> numbers, Function<Integer, Integer> mapper) {
        List<Integer> parallelStream = numbers.parallelStream()
                .map(mapper)
                .toList();
        System.out.println(parallelStream);
    }

    public static void demonstrateFileOperations(String filePath, String directoryPath, int maxDepth) {
        try {
            Stream<String> fileLines = Files.lines(Path.of(filePath));
            Stream<Path> files = Files.list(Path.of(directoryPath));
            Stream<Path> recursiveFiles = Files.walk(Path.of(directoryPath), maxDepth);
        } catch (IOException e) {
            System.out.println("File operations threw exception: " + e.getMessage());
        }
    }

    public static <T, K> void demonstrateGroupingByCount(List<T> items, Function<T, K> classifier) {
        Map<K, Long> downstreamCollectors = items.stream()
                .collect(Collectors.groupingBy(
                        classifier,
                        Collectors.counting()
                ));

        System.out.println(downstreamCollectors);
    }

    public static <T, K, R> void demonstrateGroupingWithFiltering(
            List<T> items,
            Function<T, K> classifier,
            java.util.function.Predicate<T> predicate,
            Function<T, R> mapper) {
        Map<K, List<R>> filteredGroups = items.stream()
                .collect(Collectors.groupingBy(
                        classifier,
                        Collectors.filtering(predicate, Collectors.mapping(mapper, Collectors.toList()))
                ));
        System.out.println(filteredGroups);
    }

    public static <T, K, R> void demonstrateGroupingWithMapping(
            List<T> items,
            Function<T, K> classifier,
            Function<T, R> mapper) {
        Map<K, List<R>> mappedGroups = items.stream()
                .collect(Collectors.groupingBy(
                        classifier,
                        Collectors.mapping(mapper, Collectors.toList())
                ));
        System.out.println(mappedGroups);
    }

    public static <T, A, B, C> void demonstrateTeeing(
            List<T> items,
            Collector<? super T, ?, A> downstream1,
            Collector<? super T, ?, B> downstream2,
            java.util.function.BiFunction<? super A, ? super B, C> merger) {
        C result = items.stream().collect(Collectors.teeing(
                downstream1,
                downstream2,
                merger
        ));
        System.out.println("Teeing result: " + result);
    }

    public static int demonstrateCustomCollector(List<Person> employees, String departmentFilter) {
        Collector<Person, ?, List<String>> customCollector = Collectors.filtering(
                (p) -> p.department().equals(departmentFilter),
                Collectors.mapping(Person::name, Collectors.toList())
        );

        List<String> filteredResults = employees.stream().collect(customCollector);
        System.out.println("Custom filtered " + departmentFilter + ": " + filteredResults + " size: " + filteredResults.size());

        return filteredResults.size();
    }

    public static <T> void demonstrateTakeWhile(List<T> items, java.util.function.Predicate<T> predicate) {
        List<T> takeWhileResults = items.stream()
                .takeWhile(predicate)
                .toList();
        System.out.println("Take while results: " + takeWhileResults);
    }

    public static <T> void demonstrateIterateWithPredicate(
            T seed,
            java.util.function.Predicate<T> hasNext,
            java.util.function.UnaryOperator<T> next) {
        Stream<T> iterateWithPredicate = Stream.iterate(seed, hasNext, next);
        System.out.println("Iterate with Predicate: " + iterateWithPredicate.toList());
    }

    public static void demonstrateStringLines(String multilineString) {
        long countLines = multilineString.lines().count();
        System.out.println("Number of lines: " + countLines);
    }

    public static void demonstrateStreamBuilder(String... elements) {
        Stream.Builder<String> builder = Stream.builder();
        for (String element : elements) {
            builder.add(element);
        }
        Stream<String> builtStream = builder.build();
        System.out.println("Builder: " + builtStream.toList());
    }

    public static String demonstrateOptionalWithStream(List<String> words, String startsWith) {
        String firstWord = words.stream()
                .filter(word -> word.startsWith(startsWith))
                .findFirst()
                .orElse(null);

        System.out.println(firstWord == null ? "Not found" : firstWord);

        return firstWord;
    }

    public static <T, R> void demonstrateStreamWithMapping(
            List<T> items,
            java.util.function.Predicate<T> filter,
            Function<T, R> mapper) {
        List<R> mappedResults = items.stream()
                .collect(Collectors.filtering(
                        filter,
                        Collectors.mapping(mapper, Collectors.toList())
                ));
        System.out.println("Mapping results: " + mappedResults);
    }

    public static void demonstrateStreamsConcat(Stream<String> stream1, Stream<String> stream2) {
        Stream<String> concatStreams = Stream.concat(stream1, stream2);
        System.out.println("Concat Streams: " + concatStreams.toList());
    }

    public static <T, K> void demonstrateMultiLevelReduction(
            List<T> items,
            Function<T, K> classifier,
            Function<T, String> valueMapper,
            String delimiter) {
        Map<K, String> resultMap = items.stream()
                .collect(Collectors.groupingBy(
                        classifier,
                        Collectors.mapping(
                                valueMapper,
                                Collectors.joining(delimiter)
                        )
                ));
        System.out.println("Multi-level reduction results: " + resultMap);
    }

    public static <T> void demonstrateSum(List<T> items, java.util.function.ToIntFunction<T> mapper) {
        int total = items.stream().mapToInt(mapper).sum();
        System.out.println("Total sum: " + total);
    }

    public static void demonstrateMixedOperations(List<String> words, int minLength, String containsChar) {
        List<String> mixedOpsResult = words.stream()
                .filter(word -> word.length() > minLength)
                .map(String::toUpperCase)
                .filter(word -> word.toLowerCase().contains(containsChar))
                .toList();
        System.out.println("Mixed operations:" + mixedOpsResult);
    }

    public static void demonstrateLocalDateStream(LocalDate... dates) {
        List<LocalDate> sortedDates = Stream.of(dates)
                .sorted()
                .toList();
        System.out.println("Sorted dates: " + sortedDates);
    }
}