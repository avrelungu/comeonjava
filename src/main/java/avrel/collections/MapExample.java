package avrel.collections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapExample {
    public static void main (String[] args) {
        record Customer(String customerId, String customerName) {
            public String getCustomerId() {
                return this.customerId;
            }

            public String getCustomerName() {
                return this.customerName;
            }
        }

        record Order(String customerId, String orderName){
            public String getCustomerId() {
                return this.customerId;
            }

            public String getOrderName() {
                return this.orderName;
            }
        }

        Map<String, Customer> map = new HashMap<>();

        Customer customer1 = new Customer("123", "Aurel Lungu");
        Customer customer2 = new Customer("456", "Dimi");

        map.put(customer1.getCustomerId(), customer1);
        map.put(customer2.getCustomerId(), customer2);

        Order order = new Order(customer1.getCustomerId(), "order for " + customer1.getCustomerName());

        Customer customer = map.get(order.getCustomerId());

        System.out.println(customer);

        // 1. Different implementation of the Map interface
        Map<String, String> hashMap = new HashMap<>();
        Map<String, String> treeMap = new TreeMap<>();

        Map<String, String> hashTable = new Hashtable<>(); // async HashMap
        Map<String, String> concurrentMap = new ConcurrentHashMap<>(); // if i need a Map that needs to be processed by multiple Threads

        SortedMap<String, String> sortedMap = new TreeMap<>();


        // 2. Examples
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("1", "Aurel");

        Map<String, Customer> customerMap = new HashMap<>();
        customerMap.put(customer1.getCustomerId(), customer1);

        // 2.1 putAll
        Map<String, String> stringMap2 = new HashMap<>();
        stringMap2.putAll(stringMap);

        System.out.println(stringMap2);

        // 2.2 containsKey/containsObject
        boolean containsKey = map.containsKey(customer1.getCustomerId());
        System.out.println(containsKey ? "The map contains the key for customer " + customer1.getCustomerName() : "The map does NOT contain the key for the customer " + customer1.getCustomerName());


        Customer newCustomer = new Customer("21", "Avrel");
        boolean containsObject = map.containsValue(newCustomer);
        System.out.println(containsObject ? "The map contains the customer " + newCustomer.getCustomerName() : "The map does NOT contain the customer " + newCustomer.getCustomerName());

        // 2.3 Iterator
        Iterator<String> mapIterator = map.keySet().iterator();

        while (mapIterator.hasNext()) {
            String currentMapKey = mapIterator.next();
            System.out.println(currentMapKey);
            System.out.println(map.get(currentMapKey).getCustomerName());
        }

        for (String mapKey : map.keySet()) {
            System.out.println(mapKey);
            System.out.println(map.get(mapKey).getCustomerName());
        }

        // Streams
        Stream<Map.Entry<String, Customer>> mapStream = map.entrySet().stream();

        Map<String, Customer> mapFromStream = mapStream.collect(Collectors.filtering(
                (Map.Entry<String, Customer> mapEntry) -> mapEntry.getValue().getCustomerName().equals("Dimi"),
                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)
        ));

        System.out.println("Map from stream:" + mapFromStream);


        // HashMap vs TreeMap - same as Hash and Tree logic: Hash does not

    }
}
