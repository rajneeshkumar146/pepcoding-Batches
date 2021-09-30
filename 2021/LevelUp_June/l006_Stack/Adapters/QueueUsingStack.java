import java.util.LinkedList;

public class QueueUsingStack {
    LinkedList<Integer> st = new LinkedList<>();
    LinkedList<Integer> temp = new LinkedList<>();

    public QueueUsingStack() {

    }

    public void push(int x) {
        st.addFirst(x);
    }

    public void swapData(LinkedList<Integer> st1, LinkedList<Integer> st2) {
        while (st1.size() != 0) {
            st2.addFirst(st1.removeFirst());
        }
    }

    public int pop() {
        swapData(st, temp);
        int rv = temp.removeFirst();
        swapData(temp, st);
        return rv;
    }

    public int peek() {
        swapData(st, temp);
        int rv = temp.getFirst();
        swapData(temp, st);
        return rv;
    }

    public boolean empty() {
        return this.st.size() == 0;
    }
}