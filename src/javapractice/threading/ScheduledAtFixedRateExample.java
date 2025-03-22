package javapractice.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledAtFixedRateExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
        ExecutorService workerPool = Executors.newFixedThreadPool(2); // For running long tasks

        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("I am inside Run at " + System.currentTimeMillis());
            workerPool.submit(() -> {
                try {
                    Thread.sleep(10000); // Simulating long-running task
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }, 0, 1, TimeUnit.SECONDS);
    }
}
