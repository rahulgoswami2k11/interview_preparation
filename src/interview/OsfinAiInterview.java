package interview;


import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    String value;

    Node next;
    Node prev;

    Node(int key, String value) {
        this.key = key;
        this.value = value;
        next = null;
        prev = null;
    }
}

class LRU {
    int capacity;
    int count;
    Map<Integer, Node> map;
    Node head;
    Node tail;


    public LRU(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.map = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    void put(int key, String value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            placeAtTheBeginning(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            if(count == capacity) {
                removeNode(tail);
            }
            placeAtTheBeginning(node);
        }
    }

    String get(int key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            removeNode(node);
            placeAtTheBeginning(node);
            return node.value;
        } else {
            return "-1";
        }
    }


    //1 -> 2 -> 3

    void removeNode(Node node) {
        map.remove(node.key);
        Node next = node.next;
        Node prev = node.prev;


        node.next = null;
        node.prev = null;

        if(prev != null) {
            prev.next = next;
        } else {
            head = next;
        }

        if(next != null) {
            next.prev = prev;
        } else {
           tail = prev;
        }
        this.count--;
    }

    void placeAtTheBeginning(Node node) {
        map.put(node.key, node);
        node.next = head;

        if(head != null) {
            head.prev = node;
        } else {
            tail = node;
        }

        head = node;

        this.count++;

    }

    void printNodes() {
        Node curr = head;

        while(curr != null) {
            System.out.println(curr.value);
            curr = curr.next;
        }
    }

}

public class OsfinAiInterview {
    public static void main(String[] args) {
        LRU cache = new LRU(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println(cache.get(2));
        System.out.println(cache.get(4));
        cache.put(4, "D");
        cache.put(3, "E");
        System.out.println(cache.get(4));
        cache.put(1, "A");

        System.out.println("==============");

        cache.printNodes();
    }
}
