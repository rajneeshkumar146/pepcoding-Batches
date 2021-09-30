public class stack {
    private class Node {
        int data = 0;
        Node next = null;

        public Node(int data) {
            this.data = data;
        }
    }

    private int size = 0;
    private Node head = null;
    private Node tail = null;

    public void addFirst(Node node) {
        if (this.head == null)
            this.head = this.tail = node;
        else {
            node.next = this.head;
            this.head = node;
        }

        this.size++;
    }

    public Node removeFirst() {
        Node node = this.head;
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            this.head = node.next;
        }

        node.next = null;
        this.size--;
        return node;
    }

    public int size() {
        return this.size;
    }

    public void push(int data) {
        addFirst(new Node(data));
    }

    public int peek() {
        return this.head.data;
    }

    public int pop(){
        return removeFirst().data;
    }

}