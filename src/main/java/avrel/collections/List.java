package avrel.collections;

import java.util.*;

public class List {

    public static void main(String[] args) {

        // 1. List
        java.util.List<String> myList = new ArrayList<>();
        myList.add("Object 1");
        myList.add("Object 2");
        myList.add("Object 3");
        myList.add("avrel");

        String firstObject = myList.getFirst();

        java.util.List<String> myListStream = myList.stream()
                .filter(obj -> obj.contains("Object"))
                .toList();
        System.out.println(myListStream);

        for (String next : myList) {
            System.out.println(next);
        }

        System.out.println(myList.size());
    }
}
