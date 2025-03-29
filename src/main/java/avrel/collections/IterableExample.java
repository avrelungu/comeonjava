package avrel.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

public class IterableExample {

    public static void main(String[] args) {
        List<Integer> list1 = List.of(1, 2, 3, 4, 5);

        Collection<Integer> collection = list1;
        Iterable<Integer> iterable = collection;

        for (Integer item : iterable) {
            System.out.println("Enhanced for: " + item);
        }

        Iterator<Integer> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            System.out.println("Iterator: " + iterator.next());
        }

        iterable.forEach(element -> System.out.println("For Each: " + element));

        MyIterable customIterable = new MyIterable();
        customIterable.add("Aurel");
        customIterable.add("Dimi");

        Iterator<String> iteratorFromCustomIterable = customIterable.iterator();
        iteratorFromCustomIterable.forEachRemaining(System.out::println);

        for (String item : customIterable) {
            System.out.println("from custom: " + item);
        }

        for (int i = 0; i <= list1.size() - 1; i++) {
            System.out.println("Classic for: " + list1.get(i));
        }
    }

}
