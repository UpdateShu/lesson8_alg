package ru.geekbrains.lessons;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;

public class ChainingHashMap<K, V> {
    private int capacity;
    private int size;
    private LinkedList<Node>[] st;

    public ChainingHashMap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        st = new LinkedList[capacity];
        for (int i = 0; i < st.length; i++) {
            st[i] = new LinkedList<>();
        }
    }

    private class Node {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public boolean contains(K key) {
        return getVal(key) != null;
    }

    public void checkKeyNotNull(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
    }

    public void put(K key, V value) {
        checkKeyNotNull(key);
        int i = hash(key);
        for (Node node : st[i]) {
            if (key.equals(node.key)) {
                node.value = value;
                return;
            }
        }
        st[i].addLast(new Node(key, value));
        size++;
    }

    public boolean remove(K key) {
        checkKeyNotNull(key);
        int i = hash(key);
        Node node = get(key);
        if (node == null)
            return false;
        if (!st[i].remove(node))
            return false;
        size--;
        return true;
    }

    public V getVal(K key) {
        Node node = get(key);
        if (node == null)
            return null;
        return node.value;
    }

    private Node get(K key) {
        checkKeyNotNull(key);
        int i = hash(key);
        for (Node node : st[i]) {
            if (key.equals(node.key)) {
                return node;
            }
        }
        return null;
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            for (Node node : st[i]) {
                sb.append(node.key).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        System.out.println(sb);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            for (Node node : st[i]) {
                sb.append(getFormatNode(node) + "\n");
            }
        }
        return sb.toString();
    }

    private String getFormatNode(Node node) {
        StringBuilder sb = new StringBuilder(node.key.toString());
        int spaceCount = 10 - node.key.toString().length();
        for (int i = 0; i < spaceCount; i++) {
            sb.append(" ");
        }
        sb.append(node.value);
        return sb.toString();
    }
}
