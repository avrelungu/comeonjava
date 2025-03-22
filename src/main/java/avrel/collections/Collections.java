package avrel.collections;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Collections {

    public static void main(String[] args) {

        // 1. List
        List<String> myList = new ArrayList<>();
        myList.add("Object 1");
        myList.add("Object 2");
        myList.add("Object 3");
        myList.add("avrel");

        String firstObject = myList.getFirst();

        List<String> myListStream = myList.stream()
                .filter(obj -> obj.contains("Object"))
                .toList();
        System.out.println(myListStream);

        for (String next : myList) {
            System.out.println(next);
        }

        System.out.println(myList.size());

        // 2. Set
        Set<String> set = new HashSet<>();

        boolean added = set.add("Hello");
        boolean added2 = set.add("Hello");

        System.out.println("Added should be true: " + added);
        System.out.println("Added should be false: " + added2);

        Set<String> set2 = Set.of("Salut", "Aurel");

//        Iterator<String> iterator = set2.iterator();
//        while (iterator.hasNext()) {
//            String next = iterator.next();
//            System.out.println(next);
//        }

//        for (String setItem : set2) {
//            System.out.println(setItem);
//        }

        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Salut");
        treeSet.add("Aurel");
        treeSet.add("2");
        treeSet.add("3");
        treeSet.add("1");
//        for (String item : treeSet) {
//            System.out.println(item);
//        }

        treeSet.forEach(System.out::println);

        treeSet.retainAll(
                treeSet.stream().
                        filter(
                                item -> item.chars().allMatch(Character::isDigit)
                        )
                        .collect(Collectors.toSet())
        );
        System.out.println(treeSet);

        boolean containsName = treeSet.contains("Aurel");
        System.out.println("Set contains name after retaining only the digits: " + containsName);
    }
}
