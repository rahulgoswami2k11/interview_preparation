package javapractice.threading;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class ConcurrentSkipListExample {
    public static void main(String[] args) {
        Set<String> set = new ConcurrentSkipListSet<>();
        set.add("Rahul");
        set.add("Kittu");
        set.add("Mitthu");

        System.out.println(set);
        for(String name: set) {
            System.out.println(name);
            set.remove(name);
        }
    }
}
