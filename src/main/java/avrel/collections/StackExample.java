package avrel.collections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.stream.Collectors;

public class StackExample {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        stack.add("Aurel");
        stack.add("Dimi");

        System.out.println("Peek: " + stack.peek());

        String popped = stack.pop();
        System.out.println("Pooped: " + popped);

        stack.add("Java programmer");

        for (String item : stack){
            System.out.println(item.toLowerCase());
        }

        int indexAurel = stack.search("Aurel");

//        System.out.println(stack.reversed());

        Deque<String> dequeFromStack = new ArrayDeque<>(stack);

        String poppedFromDeque = dequeFromStack.pop();
        System.out.println("Popped from deque: " + poppedFromDeque);
    }
}
