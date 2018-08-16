package collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by rocksea on 2018. 8. 10..
 */
public class LRUCache<K, V> {
    private Map<K, Node> cache = new HashMap<>();
    private Node head;
    private Node tail;
    private int size = 0;
    public LRUCache(int size) {
        this.size = size;
    }

    public void remove(Node node) {
        if(node.pre != null){
            node.pre.next = node.next;
        } else {
            head = node.next;
        }

        if(node.next != null) {
            node.next.pre = node.pre;
        } else {
            tail = node.pre;
        }
    }

    private void setHead(Node node) {
        node.next = head;
        node.pre = null;

        if(head != null) {
            head.pre = node;
        }

        head = node;

        if(tail == null) {
            tail = node;
        }
    }

    public void put(K key, V value) {
        if(cache.containsKey(key)) {
            Node node = cache.get(key);
            node.set(value);
            remove(node);
            setHead(node);
        } else {
            Node<K, V> node = new Node(key, value);
            if(cache.size() >= size) {
                cache.remove(tail.key);
                remove(tail);
            }
            setHead(node);
            cache.put(key, node);
        }
    }

    public V get(K key) {
        if(cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            remove(node);
            setHead(node);
            return node.get();
        } else {
            return null;
        }
    }
}


class Node<K,V> {
    K key;
    V value;
    Node pre;
    Node next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public void set(V value) {
        this.value = value;
    }

    public V get() {
        return value;
    }
}
