public class queue {
    protected int[] que;
    protected int head = 0;
    protected int tail = 0;
    protected int size = 0;

    // constructor's.------------------------
    protected void resize(int size) {
        this.que = new int[size];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    protected int maxSize() {
        return this.que.length;
    }

    public queue() {
        resize(10);
    }

    public queue(int size) {
        resize(size);
    }

    // basic.=========================

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < this.size; i++) {
            int idx = (this.head + i) % maxSize();
            sb.append(this.que[idx] + ",");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }

    // functions.===================

    protected void push_(int val) {
        this.que[this.tail] = val;
        this.tail = (this.tail + 1) % maxSize();
        this.size++;
    }

    public void push(int val) throws Exception {
        if (this.size == maxSize())
            throw new Exception("QueueIsFull");
        push_(val);
    }

    protected void pop_() {
        this.que[this.head] = 0;
        this.head = (this.head + 1) % maxSize();
        this.size--;
    }

    public void pop() throws Exception {
        if (this.size == 0)
            throw new Exception("QueueIsEmpty");
        pop_();
    }

    public int top() throws Exception {
        if (this.size == 0)
            throw new Exception("QueueIsEmpty");

        return this.que[this.head];
    }

}