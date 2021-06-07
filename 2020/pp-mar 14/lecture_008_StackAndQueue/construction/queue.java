public class queue {
    private int[] arr;
    private int maxSize;
    private int size;
    private int front;
    private int back;

    protected void initilize(int len) {
        this.arr = new int[len];
        this.maxSize = len;
        this.size = 0;
        this.front = 0;
        this.back = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.size; i++) {
            int idx = (i + this.front) % this.maxSize;
            sb.append(this.arr[idx]);

            if (i != this.size - 1)
                sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    }

    // default size 5
    public queue() {
        initilize(5);
    }

    public queue(int size) {
        initilize(size);
    }

    private void queueIsEmptyException() throws Exception {
        if (this.size == 0)
            throw new Exception("queueIsEmptyException: -1");
    }

    private void queueIsOverflowException() throws Exception {
        if (this.size == this.maxSize)
            throw new Exception("queueIsOverflowException: -1");
    }

    // O(1)
    public int size() {
        return this.size;
    }

    public int maxSize() {
        return this.maxSize;
    }

    // O(1)
    public boolean isEmpty() {
        return this.size == 0;
    }

    private void add_(int data) {
        this.arr[this.back] = data;
        this.back = (this.back + 1) % this.maxSize;
        this.size++;
    }

    // O(1)
    public void add(int data) throws Exception {
        queueIsOverflowException();
        add_(data);
    }

    // O(1)
    public int peek() throws Exception {
        queueIsEmptyException();
        return this.arr[this.front];
    }

    private int remove_() {
        int rv = this.arr[this.front];
        this.arr[this.front] = 0;
        this.front = (this.front + 1) % this.maxSize;
        this.size--;

        return rv;
    }

    // O(1)
    public int remove() throws Exception {
        queueIsEmptyException();
        return remove_();
    }
}