public class queue {
    protected int[] que;
    protected int size = 0;
    protected int front = 0;
    protected int last = 0;

    int b=20;

    protected void resize(int size) {
        this.que = new int[size];
    }

    protected int Max_size() {
        return this.que.length;
    }

    public queue() {
        resize(10);
    }

    public queue(int size) {
        resize(size);
    }

    public queue(int[] arr) {
        resize(2 * arr.length);

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(que[(front + i) % que.length] + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]\n");

        return sb.toString();
    }

    // Api.=======================

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    protected void push_(int data) {
        que[last] = data;
        last = (last + 1) % que.length;
        size++;
    }

    public void push(int data) throws Exception {
        if (this.size == que.length) {
            throw new Exception("QueueOverflow");
        }

        push_(data);
    }

    protected int pop__() {

        int rv = que[front];
        que[front] = 0;
        front = (front + 1) % que.length;
        size--;

        return rv;

    }

    public int pop_() throws Exception {
        if (size == 0) {
            throw new Exception("QueueIsEmpty");
        }

        return pop__();
    }

    public int pop() throws Exception {
        int rv = -1;
        try {
            rv = pop_();
        } catch (Exception e) {
            System.out.println("Bye!");
        }

        return rv;
    }

    public int front() throws Exception {
        if (size == 0) {
            throw new Exception("QueueIsEmpty");
        }

        return que[front];
    }
}