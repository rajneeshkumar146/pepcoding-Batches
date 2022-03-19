public class linkedlist {
    private class Node {
        int data = 0;
        Node next = null;

        Node(int data) {
            this.data = data;
        }

        Node() {

        }
    }

    private Node head = null, tail = null;
    private int size = 0;

    // Exception Functions.==========================================

    public void nullException() throws Exception {
        if (this.size == 0)
            throw new Exception("LinkedList is Empty");
    }

    // basic Functions.==============================================

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    // Add Functions.==============================================

    private void addFirstNode(Node node) {
        if (this.head == null)
            this.head = this.tail = node;
        else {
            node.next = this.head;
            this.head = node;
        }
        this.size++;
    }

    public void addFirst(int data) {
        Node node = new Node(data);
        addFirstNode(node);
    }

    private void addLastNode(Node node) {
        if (this.head == null)
            this.head = this.tail = node;
        else {
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
    }

    public void addLast(int data) {
        Node node = new Node(data);
        addLastNode(node);
    }

    private void addAtNode(Node node, int idx) {
        if (idx == 0)
            addFirstNode(node);
        else if (idx == this.size)
            addLastNode(node);
        else {
            Node prevNode = getAtNode(idx - 1);
            Node currNode = prevNode.next;

            prevNode.next = node;
            node.next = currNode;

            this.size++;
        }
    }

    public void addAt(int data, int idx) throws Exception{
        if (idx < 0 || idx > this.size)
        throw new Exception("IndexOutOfgBound");
        Node node = new Node(data);
        addAtNode(node, idx);
    }

    // get Functions.==============================================

    private Node getFirstNode() {
        return this.head;
    }

    public int getFirst() throws Exception {
        nullException();
        return getFirstNode().data;
    }

    private Node getLastNode() {
        return this.tail;
    }

    public int getLast() throws Exception {
        nullException();
        return getLastNode().data;
    }

    private Node getAtNode(int idx) {
        Node curr = this.head;
        while (idx-- > 0) {
            curr = curr.next;
        }

        return curr;
    }

    public int getAt(int idx) throws Exception {
        nullException();
        return getAtNode(idx).data;
    }

    // remove Functions.==============================================

    private Node removeFirstNode() {
        Node rn = this.head;
        if (this.size == 1) {
            this.head = this.tail = null;
        } else {
            this.head = rn.next;
            rn.next = null;
        }
        this.size--;
        return rn;
    }

    public int removeFirst() throws Exception {
        nullException();
        return removeFirstNode().data;
    }

    private Node removeLastNode() {
        Node rn = this.head;
        if (this.size == 1) {
            this.head = this.tail = null;
        } else {
            Node prevNode = getAtNode(this.size - 2);
            this.tail = prevNode;
            prevNode.next = null;
        }
        this.size--;
        return rn;
    }

    public int removeLast() throws Exception {
        nullException();
        return removeLastNode().data;
    }

    private Node removeAtNode(int idx) {
        if (idx == 0)
            return removeFirstNode();
        else if (idx == this.size - 1)
            return removeLastNode();
        else {
            Node prevNode = getAtNode(idx - 1);
            Node rn = prevNode.next;

            prevNode.next = rn.next;
            rn.next = null;

            this.size--;
            return rn;
        }
    }

    public void removeAt(int idx) throws Exception{
        if (idx < 0 || idx >= this.size)
         throw new Exception("IndexOutOfgBound");
        removeAtNode(idx);
    }

}