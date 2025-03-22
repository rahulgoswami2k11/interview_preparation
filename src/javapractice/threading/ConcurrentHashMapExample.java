package javapractice.threading;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("UP", "Lucknow");
        map.put("Karnataka", "Bangalore");


        for(String state: map.keySet()) {
            System.out.println("State " + state  + " capital is " + map.get(state));
            map.remove(state);
        }
    }
}
