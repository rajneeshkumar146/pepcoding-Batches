import java.util.HashMap;

public class LRUCache_opti {
    // data Members
    private class ListNode {
        Integer key, value;
        ListNode next = null;
        ListNode prev = null;

        ListNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private ListNode[] map;
    private int capacity;
    private int size;
    private ListNode head = null;
    private ListNode tail = null;

    private void intialize(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.map = new ListNode[(int) 1e4 + 1];
        this.head = this.tail = null;
    }

    public LRUCache(int capacity) {
        intialize(capacity);
    }

    private void addLast(ListNode node) {
        if (this.head == null)
            this.head = this.tail = node;
        else {
            node.prev = this.tail;
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;

    }

    private ListNode removeFirst() {
        ListNode node = this.head;
        if (this.head == this.tail)
            this.head = this.tail = null;
        else {
            this.head = node.next;
            node.next = this.head.prev = null;
        }
        this.size--;
        return node;
    }

    private ListNode removeLast() {
        ListNode node = this.tail;
        if (this.head == this.tail)
            this.head = this.tail = null;
        else {
            this.tail = node.prev;
            node.prev = this.tail.next = null;
        }
        this.size--;
        return node;
    }

    private ListNode remove(ListNode node) {
        if (node == this.head)
            return removeFirst();
        else if (node == this.tail)
            return removeLast();
        else {
            ListNode prev = node.prev, forw = node.next;

            prev.next = forw;
            forw.prev = prev;

            node.next = node.prev = null;
            this.size--;
            return node;
        }
    }

    private void makeRecent(ListNode node) {
        if (node == this.tail)
            return;

        remove(node);
        addLast(node);
    }

    public int get(int key) {
        if (map[key] == null)
            return -1;

        ListNode node = map[key];
        makeRecent(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map[key] != null) {
            ListNode node = map[key];
            node.value = value;
            makeRecent(node);
        } else {
            ListNode node = new ListNode(key, value);
            if (this.size == this.capacity) {
                ListNode rn = removeFirst();
                map[rn.key] = null;
            }

            addLast(node);
            map[key] = node;
        }
    }
}