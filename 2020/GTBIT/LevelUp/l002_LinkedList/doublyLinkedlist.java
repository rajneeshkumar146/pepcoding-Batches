public class doublyLinkedlist {

    private class Node {
        int data = 0;
        Node prev = null;
        Node next = null;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    // Exceptions========================================

    private boolean ListIsEmptyException() {
        if (this.size == 0) {
            System.out.print("ListIsEmpty: ");
            return true;
        }
        return false;
    }

    private boolean indexIsInvalidException(int index, int leftRange, int rightRange) {
        if (index < leftRange || index > rightRange) {
            System.out.print("IndexIsInValid: ");
            return true;
        }
        return false;
    }

    // Add Functions =========================================================

    private void addFirstNode(Node node) {
        if (this.size == 0)
            this.head = this.tail = node;
        else {
            node.next = this.head;
            this.head.prev = node;

            this.head = node;
        }
        this.size++;
    }

    public void addFirst(int data) {
        Node node = new Node(data);
        addFirstNode(node);
    }

    private void addLastNode(Node node) {
        if (this.size == 0)
            this.head = this.tail = node;
        else {
            node.prev = this.tail;
            this.tail.next = node;

            this.tail = node;
        }
        this.size++;
    }

    public void addLast(int data) {
        Node node = new Node(data);
        addLastNode(node);
    }

    private void addBeforeNode(Node refNode, Node node) {
        Node prevNode = refNode.prev;
        if (prevNode != null) {
            prevNode.next = node;
            node.prev = prevNode;

            node.next = refNode;
            refNode.prev = node;

            this.size++;
        } else {
            addFirstNode(node);
        }
    }

    public void addBefore(Node refNode, int data) {
        Node node = new Node(data);
        addBeforeNode(refNode, node);
    }

    // remove Functions ======================================================

    public Node removeFirstNode() {
        Node rNode = this.head;
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            this.head = rNode.next;
            this.head.prev = rNode.next = null;
        }
        this.size--;
        return rNode;
    }

    public int removeFirst() {
        if (ListIsEmptyException())
            return -1;
        Node node = removeFirstNode();
        return node.data;

    }

    public Node removeLastNode() {
        Node rNode = this.tail;
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            this.tail = rNode.prev;
            this.tail.next = rNode.prev = null;
        }
        this.size--;
        return rNode;
    }

    public int removeLast() {
        if (ListIsEmptyException())
            return -1;
        Node node = removeLastNode();
        return node.data;
    }

    public Node removeNode(Node node) {
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
        return node;
    }

    private Node removeBeforeNode(Node refNode) {
        Node prevNode = refNode.prev;
        if (prevNode.prev == null)
            return removeFirstNode();
        else {
            prevNode.prev.next = refNode;
            refNode.prev = prevNode.prev;

            prevNode.next = prevNode.prev = null;
            this.size--;
        }

        return prevNode;
    }

    public int removeBefore(Node refNode) {
        if (refNode.prev == null) {
            System.out.println("LocationIsInvalid: ");
            return -1;
        }

        return removeBeforeNode(refNode).data;
    }

    public int removeAfter(Node refNode) {

    }

    // get Functions =========================================================

}