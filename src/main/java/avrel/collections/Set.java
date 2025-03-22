package avrel.collections;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Set {
    public static void main(String[] args) {
        // 2. Set
        java.util.Set<String> set = new HashSet<>();

        boolean added = set.add("Hello");
        boolean added2 = set.add("Hello");

        System.out.println("Added should be true: " + added);
        System.out.println("Added should be false: " + added2);

        java.util.Set<String> set2 = java.util.Set.of("Salut", "Aurel");

//        Iterator<String> iterator = set2.iterator();
//        while (iterator.hasNext()) {
//            String next = iterator.next();
//            System.out.println(next);
//        }

//        for (String setItem : set2) {
//            System.out.println(setItem);
//        }

        java.util.Set<String> treeSet = new TreeSet<>();
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
