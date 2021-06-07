import java.util.LinkedList;

class MyStack {

    LinkedList<Integer> que = new LinkedList<>();
    LinkedList<Integer> temp = new LinkedList<>();

    int topEle = 0;

    public MyStack() {

    }

    private void transfer(LinkedList<Integer> st1, LinkedList<Integer> st2) {
        while (st1.size() != 0) {
            st2.addLast(st1.removeFirst());
        }
    }

    // O(n)
    public void push(int x) {
        temp.addLast(x);
        transfer(que, temp);

        LinkedList<Integer> temp1 = que;
        que = temp;
        temp = temp1;
    }

    // O(1)
    public int pop() {
        return que.removeFirst();
    }

    /** Get the top element. */
    public int top() {
        return que.getFirst();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return que.size() == 0;
    }
}
