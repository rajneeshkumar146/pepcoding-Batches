import java.util.LinkedList;

class queueUsingStackPop {

    LinkedList<Integer> st = new LinkedList<>();
    LinkedList<Integer> temp = new LinkedList<>();

    public queueUsingStackPop() {

    }

    private void transfer(LinkedList<Integer> st1, LinkedList<Integer> st2) {
        while (st1.size() != 0) {
            st2.addFirst(st1.removeFirst());
        }
    }

    // O(n)
    public void push(int x) {
        transfer(st, temp);
        st.addFirst(x);
        transfer(temp, st);
    }

    // O(1)
    public int pop() {
        return st.removeFirst();
    }

    public int peek() {
        return st.getFirst();
    }

    public boolean empty() {
        return st.size() == 0;
    }
}
