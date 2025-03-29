package avrel.collections;

import java.util.*;

public class IteratorExample {
    public static void main(String[] args) {
        Set<String> set1 = new TreeSet<String>();
        set1.add("aurel");
        set1.add("aurel2");
        set1.add("aurel3");

//        for (String item : set1) {
//            set1.add(item.toUpperCase());
//            System.out.println(item);
//        }

        Iterator<String> iterator1 = set1.iterator();

        while(iterator1.hasNext()) {
            String item = iterator1.next();
            System.out.println(item.toUpperCase());
            iterator1.remove();
        }


        List<Integer> list = List.of(1, 2, 3, 4, 5);
        ListIterator<Integer> listIterator = list.listIterator();

        while(listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }

        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }

        // Custom iterator
        MyListIterator<Integer> customIterator = new MyListIterator<>(list);

        while(customIterator.hasNext()) {
            System.out.println("From custom: " + customIterator.next());
        }

//        customIterator.forEachRemaining(element -> System.out.println("From remaining:" + element));
    }
}
