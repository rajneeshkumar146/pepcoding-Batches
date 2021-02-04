import java.util.*
class LRUCache {
    private class Node {
        int key = 0; // app name
        int value = 0; // state of app

        Node prev = null;
        Node next = null;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Key , Node
    private Node[] map;
    private int maximumSize = 0;
    private int currSize = 0;

    private Node head = null;
    private Node tail = null;

    public LRUCache(int capacity) {
        this.maximumSize = capacity;
        map = new Node[3000 + 1];
    }

    public void removeTailNode() {
        if (this.currSize == 1) {
            this.head = null;
            this.tail = null;
        } else {
            Node next = this.tail.next;

            this.tail.next = null;
            next.prev = null;

            this.tail = next;
        }
        this.currSize--;
    }

    public void removeNode(Node node) {
        if (this.currSize == 1) {
            this.head = null;
            this.tail = null;
        } else if (node == this.tail) {
            removeTailNode();
            return;
        } else {
            Node prev = node.prev;
            Node next = node.next;

            node.prev = null;
            node.next = null;

            prev.next = next;
            next.prev = prev;
        }

        this.currSize--;
    }

    public void addFirstNode(Node node) {
        if (head == null) {
            this.head = node;
            this.tail = node;
        } else {
            this.head.next = node;
            node.prev = this.head;
            this.head = node;
        }

        this.currSize++;
    }

    public void makeMostRecent(Node node) {
        if (node == this.head)
            return;
        removeNode(node);
        addFirstNode(node);
    }

    public int get(int key) {
        if (map[key] == null)
            return -1;

        Node node = map[key];
        makeMostRecent(node);
        return node.value;
    }

    // appp name, app state
    public void put(int key, int value) {
        if (map[key] != null) {
            Node node = map[key];
            node.value = value;
            get(key);
        } else {
            if (this.currSize == this.maximumSize) {
                map[this.tail.key] = null;
                removeTailNode();

            }

            Node node = new Node(key, value);
            addFirstNode(node);
            map[key] = node;
        }

    }
}
