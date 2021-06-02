public class linkedlist {
    private class Node {
        int data = 0;
        Node next = null;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node curr = this.head;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null)
                sb.append(", ");

            curr = curr.next;
        }
        sb.append("]");

        return sb.toString();
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private void addFirstNode(Node node) {
        if (this.size == 0)
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
        if (this.size == 0)
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

    private void addNodeAt(Node node, int idx) {
        if (idx == 0)
            addFirstNode(node);
        else if (idx == this.size)
            addLastNode(node);
        else {
            Node prevNode = getNodeAt(idx - 1);
            Node forwNode = prevNode.next;

            prevNode.next = node;
            node.next = forwNode;
            this.size++;
        }
    }

    public void addAt(int idx, int data) {
        if (idx < 0 || idx > this.size)
            return;

        Node node = new Node(data);
        addNodeAt(node, idx);
    }

    // =======================================================

    private Node removeFirstNode() {
        Node node = this.head;
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            this.head = this.head.next;
            node.next = null;
        }

        this.size--;
        return node;
    }

    public int removeFirst() {
        if (this.size == 0)
            return -1;

        Node node = removeFirstNode();
        return node.data;
    }

    private Node removeLastNode() {
        Node node = this.tail;
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            Node secondLast = getNodeAt(this.size - 2);
            secondLast.next = null;
            this.tail = secondLast;
        }
        this.size--;
        return node;
    }

    public int removeLast() {
        if (this.size == 0)
            return -1;

        return removeLastNode().data;
    }

    private Node removeNodeAt(int idx) {
        if (idx == 0)
            return removeFirstNode();
        else if (idx == this.size - 1)
            return removeLastNode();
        else {
            Node prevNode = getNodeAt(idx - 1);
            Node node = prevNode.next;
            Node forwNode = node.next;

            node.next = null;
            prevNode.next = forwNode;
            this.size--;

            return node;
        }

    }

    public int removeAt(int idx) {
        if (idx < 0 || idx >= this.size)
            return -1;

        return removeNodeAt(idx).data;
    }

    // =======================================================

    private Node getFirstNode() {
        return this.head;
    }

    public int getFirst() {
        if (this.size == 0)
            return -1;

        return getFirstNode().data;
    }

    private Node getLastNode() {
        return this.tail;
    }

    public int getLast() {
        if (this.size == 0)
            return -1;

        return getLastNode().data;
    }

    private Node getNodeAt(int idx) {
        Node curr = this.head;
        while (idx-- > 0) {
            curr = curr.next;
        }

        return curr;
    }

    public int getAt(int idx) {
        if (idx < 0 || idx >= this.size)
            return -1;

        return getNodeAt(idx).data;
    }

    // Questions
    public void oddEven() {
        if (this.size == 0 || this.size == 1)
            return;
        Node even = new Node(-1); // Dummy Node
        Node ep = even;

        Node odd = new Node(-1); // Dummy Node
        Node op = odd;

        Node curr = this.head;
        while (curr != null) {
            if (curr.data % 2 == 0) {
                ep.next = curr;
                ep = ep.next;
            } else {
                op.next = curr;
                op = op.next;
            }

            curr = curr.next;
        }

        op.next = even.next;
        ep.next = null;

        this.head = odd.next;
        if (even.next != null)
            this.tail = ep;
        else
            this.tail = op;
    }

    public void reversePI() {
        if (this.head == null || this.head.next == null)
            return;

        Node prev = null;
        Node curr = this.head;
        while (curr != null) {
            Node forw = curr.next; // backup

            curr.next = prev; // link

            prev = curr; // move
            curr = forw;
        }

        this.tail = this.head;
        this.head = prev;
    }

    private Node reversePRHelper(Node node) {
        if (node.next == null)
            return node;

        Node reverseNode = reversePRHelper(node.next);
        reverseNode.next = node;

        return node;
    }

    public void reversePR() {

        Node reverseNode = reversePRHelper(head);
        reverseNode.next = null;
        head = tail;
        tail = reverseNode;
    }

    private void reversePRHelper(Node node) {
        if (node.next == null)
            return;

        reversePRHelper(node.next);
        Node forw = node.next;
        forw.next = node;
    }

    public void reversePR() {

        reversePRHelper(head);
        head.next = null;
        Node temp = head;
        head = tail;
        tail = temp;
    }

    private void displayReverseHelper(Node node) {
        if (node == null)
            return;

        displayReverseHelper(node.next);
        System.out.print(node.data + " ");
    }

    public void displayReverse() {
        displayReverseHelper(head);
        System.out.println();
    }

    public Node midNode(Node node) {
        if (node == null || node.next == null)
            return node;
        Node slow = node, fast = node;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public Node reverse(Node node) {
        if (node == null || node.next == null)
            return node;

        Node curr = node, prev = null;
        while (curr != null) {
            Node forw = curr.next;

            curr.next = prev;

            prev = curr;
            curr = forw;
        }

        return prev;
    }

    public void fold() {
        Node mid = midNode(head);
        Node nhead = mid.next;
        mid.next = null;

        nhead = reverse(nhead);

        Node c1 = head, c2 = nhead;
        while (c2 != null) {
            Node f1 = c1.next, f2 = c2.next;

            c1.next = c2;
            c2.next = f1;

            c1 = f1;
            c2 = f2;
        }

        if (size() % 2 != 0)
            tail = mid;
        else
            tail = mid.next;
    }

    private int lengthOfLL(Node node) {
        if (node == null)
            return 0;

        Node curr = node;
        int len = 0;
        while (curr != null) {
            curr = curr.next;
            len++;
        }

        return len;
    }

    private int findIntersection(Node one, Node two) {
        int a = lengthOfLL(one);
        int b = lengthOfLL(two);

        Node biggerListHead = a > b ? one : two;
        Node smallerListHead = b < a ? two : one;
        int diff = Math.abs(a - b);

        while (diff-- > 0)
            biggerListHead = biggerListHead.next;

        while (biggerListHead != smallerListHead) {
            biggerListHead = biggerListHead.next;
            smallerListHead = smallerListHead.next;
        }

        return smallerListHead != null ? smallerListHead.data : -1;
    }

    public int findIntersection(linkedlist one, linkedlist two) {
        return findIntersection(one.head, two.head);
    }

    public boolean IsPalindrome() {
        Node mid = midNode(head);
        Node nHead = mid.next;
        mid.next = null;

        nHead = reverse(nHead);
        Node c1 = head, c2 = nHead;
        boolean isPalindrome = true;
        while (c2 != null) {
            if (c1.data != c2.data) {
                isPalindrome = false;
                break;
            }
            c1 = c1.next;
            c2 = c2.next;
        }

        nHead = reverse(nHead);
        mid.next = nHead;

        return isPalindrome;
    }

    Node ptr;
    public boolean IsPalindrome(Node node) {
        if (node == null) {
            return true;
        }

        if (!IsPalindrome(node.next))
            return false;
        if (node.data != ptr.data)
            return false;

        ptr = ptr.next;
        return true;
    }

    public boolean IsPalindrome2() {
        ptr = head;
        return IsPalindrome(head);
    }
}
