package avrel.collections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueExample { // LinkedList, ArrayDeque, PriorityQueue
    public static void main(String[] args)
    {
        record Customer(String name, Integer age) {
            public String getName() {
                return this.name;
            }

            public Integer getAge() {
                return this.age;
            }
        }

        Queue<Customer> queueLL = new LinkedList<>();
        queueLL.offer(new Customer("aurel", 27));
        queueLL.offer(new Customer("dimi", 5));

//        System.out.println(queueLL);
//        queueLL.poll();
//        System.out.println(queueLL);
//        queueLL.poll();
//        System.out.println(queueLL);

        System.out.println("Peek: " + queueLL.peek());

        queueLL.forEach(System.out::println);

        for (Customer boy: queueLL) {
            System.out.println(boy);
        }
    }
}
