package avrel.collections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class DequeExample {

    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();

        deque.add(1);

        deque.offerLast(2);

        deque.offerFirst(3);
        Integer pooled = deque.poll();


        System.out.println("Initial: " + deque);
        System.out.println("First pooled element:" + pooled);

        Integer removedFirst = deque.removeFirst();
        System.out.println("Removed first: " + removedFirst);

        System.out.println(removedFirst);

        deque.add(1);
        deque.addLast(8);
        deque.offer(97);

        System.out.println("Added some more:" + deque);

        boolean containsNumber = deque.contains(97);

        System.out.println("Deque Contains 97: " + containsNumber);

        Deque<Integer> smallerDeque = new ArrayDeque<>();
        smallerDeque.add(1);
        smallerDeque.offerFirst(2);
        smallerDeque.offerLast(8);

        boolean containsSmallerDeque = deque.containsAll(smallerDeque);

        System.out.println("Initial deque contains smaller deque: " + containsSmallerDeque);

        for (Integer item : deque) {
            System.out.println(item);
        }

        deque.stream().filter(
                (item) -> !smallerDeque.contains(item)
        ).forEach(System.out::println);
    }
}
