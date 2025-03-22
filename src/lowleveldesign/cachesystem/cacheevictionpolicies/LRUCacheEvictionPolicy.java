package lowleveldesign.cachesystem.cacheevictionpolicies;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
class Node<Key> {
    private Key key;
    private Node<Key> next;
    private Node<Key> prev;

    public Node(Key key) {
        this.key = key;
        this.next = null;
        this.prev = null;
    }
}


public class LRUCacheEvictionPolicy<Key> implements CacheEvictionPolicy<Key> {

    private Map<Key, Node<Key>> map;
    private Node<Key> head;
    private Node<Key> tail;

    public LRUCacheEvictionPolicy() {
        this.map = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    @Override
    public void accessKey(Key key) {
        if(map.containsKey(key)) {
            Node<Key> node = map.get(key);
            removeNode(node);
            makeNodeAsHead(node);
        } else {
            Node<Key> node = new Node<>(key);
            map.put(key, node);
            makeNodeAsHead(node);
        }
    }

    @Override
    public Key evict() {
        Key key = tail.getKey();
        removeNode(tail);
        return key;
    }

    private void removeNode(Node<Key> node) {
        map.remove(node.getKey());
        Node<Key> prev = node.getPrev();
        Node<Key> next = node.getNext();

        node.setNext(null);
        node.setPrev(null);

        if(prev != null) {
            prev.setNext(next);
        } else {
            head = next;
        }

        if(next != null) {
            next.setPrev(prev);
        } else {
            tail = prev;
        }
    }

    private void makeNodeAsHead(Node<Key> node) {
        map.put(node.getKey(), node);
        node.setNext(head);
        if(head != null) {
            head.setPrev(node);
        } else {
            tail = node;
        }
        head = node;
    }
}
