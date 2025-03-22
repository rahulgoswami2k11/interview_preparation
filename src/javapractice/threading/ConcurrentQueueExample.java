package javapractice.threading;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConcurrentQueueExample {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedBlockingQueue<>();
        queue.add(1);
        queue.add(2);

        for(int val : queue) {
            System.out.println(val);
            queue.poll();
        }
    }
}
