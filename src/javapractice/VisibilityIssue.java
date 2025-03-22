package javapractice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;

@Data
@AllArgsConstructor
public class VisibilityIssue {
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        new Thread(() -> {
            while (!flag) {
                // Busy-wait
            }
            System.out.println("Flag is now true");
        }).start();

        try {
            Thread.sleep(1000); // Simulate some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true; // Main thread updates the flag
        System.out.println("Flag set to true");
    }
}

