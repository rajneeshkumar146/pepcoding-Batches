import java.util.LinkedList;

public class stack {
    private LinkedList<Integer> ll = new LinkedList();

    public int size() {
        return ll.size();
    }

    public boolean isEmpty() {
        return ll.size() == 0;
    }

    public void push(int data) {
        ll.addFirst(data);
    }

    public int top() {
        ll.getFirst();
    }

    public int pop() {
        return ll.removeFirst();
    }
}