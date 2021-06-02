import java.util.LinkedList;

public class queueUsingJavaLL {
    public static class queue {
        private LinkedList<Integer> ll = new LinkedList<>();
        
        public int size() {
            return this.ll.size();
        }

        public boolean isEmpty() {
            return ll.isEmpty();
        }

        public void add(int data) {
            ll.addLast(data);
        }

        public int peek() {
            return ll.getFirst();
        }

        public int remove() {
            return ll.removeFirst();
        }
    }

    public static void main(String[] args) {
        queue que = new queue();

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