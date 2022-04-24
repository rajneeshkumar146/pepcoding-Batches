class MyLinkedList {

    private class Node {
        int data = 0;
        Node next = null;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head = null, tail = null;
    private int size = 0;

    public MyLinkedList() {

    }

    private Node getNodeAt(int idx) {
        Node curr = this.head;
        while (idx-- > 0) {
            curr = curr.next;
        }

        return curr;
    }

    public int get(int index) {
        if (index < 0 || index >= this.size)
            return -1;

        Node node = getNodeAt(index);
        return node.data;
    }

    public void addAtHead(int val) {
        Node node = new Node(val);
        if (this.head == null)
            this.head = this.tail = node;
        else {
            node.next = this.head;
            this.head = node;
        }
        this.size++;
    }

    public void addAtTail(int val) {
        Node node = new Node(val);
        if (this.head == null)
            this.head = this.tail = node;
        else {
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
    }

    public void addAtIndex(int idx, int val) {
        if (idx < 0 || idx > this.size)
            return;

        if (idx == 0)
            addAtHead(val);
        else if (idx == this.size)
            addAtTail(val);
        else {
            Node prevNode = getNodeAt(idx - 1);
            Node nextNode = prevNode.next;

            Node node = new Node(val);
            prevNode.next = node;
            node.next = nextNode;

            this.size++;
        }
    }

    public void deleteAtIndex(int idx) {
        if (idx < 0 || idx >= this.size)
            return;

        if (this.size == 1) {
            this.head = this.tail = null;
        } else if (idx == 0) {
            Node rn = this.head;
            this.head = rn.next;

            rn.next = null;
        } else if (idx == this.size - 1) {
            Node prevNode = getNodeAt(idx - 1);
            this.tail = prevNode;
            prevNode.next = null;
        } else {
            Node prevNode = getNodeAt(idx - 1);
            Node rn = prevNode.next;
            prevNode.next = rn.next;
            rn.next = null;
        }

        this.size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */