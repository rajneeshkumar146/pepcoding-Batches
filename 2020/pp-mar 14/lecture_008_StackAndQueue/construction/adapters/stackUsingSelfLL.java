public class stackUsingSelfLL {

    public static class stack {
        private class Node {
            int data;
            Node next;

            Node(int data) {
                this.data = data;
            }
        }

        private Node head = null;
        private Node tail = null;
        private int size = 0;

        private void addFirst(int data) {
            Node node = new Node(data);
            if (this.size == 0)
                this.head = this.tail = node;
            else {
                node.next = this.head;
                this.head = node;
            }

            this.size++;
        }

        private void addLast(int data) {
            Node node = new Node(data);
            if (this.size == 0)
                this.head = this.tail = node;
            else {
                this.tail.next = node;
                this.tail = node;
            }

            this.size++;
        }

        private Node removeFirst() {
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

        public boolean isEmpty() {
            return this.size == 0;
        }

        public void add(int data) {
            addFirst(data);
        }

        public int peek() {
            return this.head.data;
        }

        public int remove() {
            return removeFirst().data;
        }

    }

    public static void main(String[] args) {
        stack que = new stack();

        que.add(10);
        que.add(20);
        que.remove();
        que.add(30);
        que.remove();
        que.add(40);
        que.add(50);
        que.add(60);

        while (que.size() != 0) {
            System.out.println(que.remove());
        }
    }

}