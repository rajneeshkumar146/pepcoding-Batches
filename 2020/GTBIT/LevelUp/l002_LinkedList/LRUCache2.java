class LRUCache {

    private class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private Node[] map;
    private Node head = null;
    private Node tail = null;
    private int capacity = 0;
    private int size = 0;

    public LRUCache(int capacity) {
        map = new Node[(int) 1e4 + 1];
        this.capacity = capacity;
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    private void removeNode(Node node) {
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            Node prev = node.prev;
            Node forw = node.next;

            if (prev == null) {
                this.head = forw;
                forw.prev = null;
            } else if (forw == null) {
                this.tail = prev;
                prev.next = null;
            } else {
                forw.prev = prev;
                prev.next = forw;

            }

            node.next = node.prev = null;
        }

        this.size--;
    }

    private void addLast(Node node) {
        if (this.size == 0)
            this.tail = this.head = node;
        else {
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }

        this.size++;
    }

    private void makeRecent(Node node) {
        if (this.tail == node)
            return;

        removeNode(node);
        addLast(node);
    }

    public int get(int key) {
        if (map[key] == null)
            return -1;

        Node node = map[key];
        makeRecent(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (map[key] != null) {
            Node node = map[key];
            node.value = value;
            makeRecent(node);
        } else {
            if (this.size == this.capacity) {
                int rKey = this.head.key;
                removeNode(this.head);
                map[rKey] = null;
            }
            Node node = new Node(key, value);
            addLast(node);
            map[key] = node;
        }
    }
}
