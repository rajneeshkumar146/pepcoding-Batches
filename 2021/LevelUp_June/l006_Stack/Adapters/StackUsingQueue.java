import java.util.LinkedList;

public class StackUsingQueue {
    LinkedList<Integer> queue = new LinkedList<>();
    LinkedList<Integer> temp = new LinkedList<>();
    public int topEle = 0;

    public StackUsingQueue() {
    }

    public void push(int x) {
        queue.addLast(x);
        this.topEle = x;
    }

    public void swapData(LinkedList<Integer> q1, LinkedList<Integer> q2) {
        while (q1.size() != 1) {
            q2.addLast(q1.removeFirst());
        }
    }

    public int pop() {
        swapData(queue, temp);
        int rv = queue.removeFirst();
        if (temp.size() != 0) {
            swapData(temp, queue);
            this.topEle = temp.getFirst();
            queue.addLast(temp.removeFirst());
        }

        return rv;
    }

    public int top() {
        return this.topEle;
    }

    public boolean empty() {
        return this.queue.size() == 0;
    }

    }

    

    

    

    