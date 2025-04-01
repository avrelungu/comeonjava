import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FirstTest {

    record Customer(Integer customerId, String customerName) {
        public String getCustomerName() {
            return this.customerName;
        }

        public Integer getCustomerId() {
            return this.customerId;
        }
    }

    record Order(Integer orderId, String orderName) {
        public String getCustomerName() {
            return this.orderName;
        }

        public Integer getCustomerId() {
            return this.orderId;
        }
    }

    static final Collection<Customer> getListOfObjects = Arrays.asList(new Customer(1, "aurel"), new Customer(1, "order"));

    @BeforeEach
    void displayAStringBeforeEachTest()
    {
        System.out.println("Before each test");
    }

    @Test
    void test_customer_name() {
        Customer testCustomer = new Customer(1, "Aurel");

        assertEquals("Aurel", testCustomer.getCustomerName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Aurel", "Dimi"})
    void parametrized_test_customer_id(String customer) {
        assertInstanceOf(String.class, customer);
    }

    @ParameterizedTest
    @FieldSource("getListOfObjects")
    @DisplayName("Object is Customer type")
    void test_all_objects_are_customers(Customer object) {
        assertInstanceOf(Customer.class, object);
    }
}
