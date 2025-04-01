package avrel.streamsv2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

class StreamsV2Test {

    List<StreamsV2.Person> employees;

    List<String> words;

    List<Integer> numbers;

    @BeforeEach
    void createNewListOfEmployees() {
        employees = List.of(
                new StreamsV2.Person("Alice", 30, "Engineering"),
                new StreamsV2.Person("Bob", 25, "Marketing"),
                new StreamsV2.Person("Charlie", 35, "Engineering"),
                new StreamsV2.Person("Diana", 28, "HR"),
                new StreamsV2.Person("Eve", 40, "Marketing"),
                new StreamsV2.Person("Frank", 22, "Engineering")
        );

        words = List.of("banana", "apple", "cherry", "date", "elderberry", "fig", "grape");

        numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    void filtering_by_word_length() {
        Assertions.assertEquals(
                3,
                StreamsV2.demonstrateFiltering(words, 5)
        );
    }

    @Test
    @Tag("long-word")
    void first_long_word() {
        Assertions.assertEquals(
                "banana",
                StreamsV2.demonstrateFirstWordByLength(words, 5)
        );
    }

    @Test
    @DisplayName("😱")
    @Tag("long-word")
    void first_long_word_return_null(TestInfo testInfo) {
        Assertions.assertNull(
                StreamsV2.demonstrateFirstWordByLength(words, 10)
        );

        Assertions.assertEquals("😱", testInfo.getDisplayName());
        Assertions.assertTrue(testInfo.getTags().contains("long-word"));
    }

    @ParameterizedTest()
    @ValueSource(strings = {"Engineering", "Marketing"})
    @DisplayName("Filter Collection by department name")
    void filter_by_department(String department)
    {
        int result = StreamsV2.demonstrateCustomCollector(employees, department);

        Assertions.assertNotEquals(0, result);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Filter Collection by department name not found")
    void no_department_found(String department)
    {
        int result = StreamsV2.demonstrateCustomCollector(employees, department);

        Assertions.assertEquals(0, result);
    }

    @ParameterizedTest()
    @ValueSource(strings = {"a", "g"})
    @DisplayName("Find first word that start with letter")
    void first_word_that_starts_with(String firstLetter)
    {
        var result = StreamsV2.demonstrateOptionalWithStream(words, firstLetter);

        Assertions.assertNotNull(result);
    }

    @ParameterizedTest()
    @ValueSource(strings = {"z", "y"})
    @DisplayName("No word found that start with letter")
    void no_first_word_that_starts_with(String firstLetter)
    {
        var result = StreamsV2.demonstrateOptionalWithStream(words, firstLetter);

        Assertions.assertNull(result);
    }


    @ParameterizedTest()
    @CsvFileSource(resources = "/departments.csv", numLinesToSkip = 1)
    void check_departments_from_csv_file(String department) {
        int result = StreamsV2.demonstrateCustomCollector(employees, department);

        Assertions.assertNotEquals(0, result);
    }
}