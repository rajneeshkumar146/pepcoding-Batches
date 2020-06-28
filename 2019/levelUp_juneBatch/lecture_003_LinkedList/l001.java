public class l001 {
    public static class Linkedlist {
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

        // Basic functions.===================================

        public int size() {
            return this.size;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        private Node getNodeAt(int idx) {
            Node curr = this.head;
            while (idx-- > 0) {

                curr = curr.next;
            }
            return curr;

        }

        private String display() {
            Node curr = this.head;
            String str = "[";
            while (curr != null) {
                str += (curr.data + ", ");
                curr = curr.next;
            }
            return (str + "]\n");
        }

        private void display_(Node node) {
            if (node == null)
                return;
            System.out.println(node.data);
            display_(node.next);
        }

        public void display2() {
            display_(this.head);
        }

        @Override
        public String toString() {
            return display();
        }

        // AddFunctions.========================================

        private void addFirstNode(Node node) {
            if (this.size == 0) {
                this.head = node;
                this.tail = node;
            } else {
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
            if (this.size == 0) {
                this.head = node;
                this.tail = node;
            } else {
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
                Node prev = getNodeAt(idx - 1);
                Node forw = prev.next;

                prev.next = node;
                node.next = forw;
                this.size++;
            }
        }

        public void addAt(int data, int idx) {
            if (this.size == 0) {
                System.out.println("ListIsEmpty");
                return;
            }

            if (idx < 0 || idx > this.size) {
                System.out.println("NullPointException");
                return;
            }

            Node node = new Node(data);
            addAtNode(node, idx);
        }

        // removeFunctions.=====================================

        public Node removeFirstNode() {
            Node rn = this.head;
            if (this.size == 1) {
                this.head = null;
                this.tail = null;
            } else
                this.head = this.head.next;

            rn.next = null;
            this.size--;

            return rn;
        }

        public int removeFirst() {
            if (this.size == 0) {
                System.out.println("ListIsEmpty");
                return -1;
            }

            Node rn = removeFirstNode();
            int rd = rn.data;
            // delete rn; // for c++
            return rd;
        }

        public Node removeLastNode() {
            Node rn = this.tail;
            if (this.size == 1) {
                this.head = null;
                this.tail = null;
            } else {
                Node prev = getNodeAt(this.size - 2);
                this.tail = prev;
                prev.next = null;
            }

            this.size--;
            return rn;
        }

        public int removeLast() {
            if (this.size == 0) {
                System.out.println("ListIsEmpty");
                return -1;
            }

            Node rn = removeLastNode();
            int rd = rn.data;
            // delete rn; // for c++
            return rd;
        }

        public Node removeAtNode(int idx) {
            if (idx == 0)
                return removeFirstNode();
            else if (idx == this.size - 1)
                return removeLastNode();
            else {
                Node prev = getNodeAt(idx - 1);
                Node rn = prev.next;

                prev.next = prev.next.next;
                rn.next = null;
                this.size--;
                return rn;
            }
        }

        public int removeAt(int idx) {
            if (this.size == 0) {
                System.out.println("ListIsEmpty");
                return -1;
            }

            if (idx < 0 || idx >= this.size) {
                System.out.println("NullPointException");
                return -1;
            }

            Node rn = removeAtNode(idx);
            int rd = rn.data;
            // delete rn; // for c++
            return rd;
        }

        // getFunctions.========================================

        public int getFirst() {
            if (this.size == 0) {
                System.out.println("ListIsEmpty");
                return -1;
            }

            return this.head.data;

        }

        public int getLast() {
            if (this.size == 0) {
                System.out.println("ListIsEmpty");
                return -1;
            }

            return this.tail.data;
        }

        public int getAt(int idx) {
            if (this.size == 0) {
                System.out.println("ListIsEmpty");
                return -1;
            }

            if (idx < 0 || idx >= this.size) {
                System.out.println("NullPointException");
                return -1;
            }

            return getNodeAt(idx).data;
        }

    }

    public static void main(String[] args) {
        // Linkedlist ll = new Linkedlist();
        // for (int i = 1; i <= 10; i++) {
        // ll.addFirst(i * 10);
        // }

        // for (int i = 1; i <= 10; i++) {
        // ll.addLast(i * 10);
        // }

        // System.out.println(ll);
        // ll.display2();
    }

}