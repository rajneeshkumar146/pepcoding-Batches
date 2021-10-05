public class queue {
    private int[] arr;
    private int front;
    private int back;
    private int NoOfElements;
    private int MaxCapacity;

    queue(int size) {
        initialize(size);
    }

    queue() {
        this(10);
    }

    protected void initialize(int size) {
        this.NoOfElements = 0;
        this.MaxCapacity = size;
        this.arr = new int[this.MaxCapacity];
        this.front = this.back = 0;
    }

    private void overflowException() throws Exception {
        if (this.NoOfElements == this.MaxCapacity)
            throw new Exception("QueueIsOverflow");
    }

    private void underflowException() throws Exception {
        if (this.NoOfElements == 0)
            throw new Exception("QueueIsUnderflow");
    }

    public void display() {
        for (int idx = 0; idx < this.NoOfElements; idx++) {
            int i = (idx + this.front) % this.MaxCapacity;
            System.out.print(this.arr[i] + " ");
        }
    }

    public int capacity() {
        return this.MaxCapacity;
    }

    public int size() {
        return this.NoOfElements;
    }

    public void push(int data) throws Exception {
        overflowException();
        this.arr[this.back] = data;
        this.back = (this.back + 1) % this.MaxCapacity;
        this.NoOfElements++;
    }

    public int top() throws Exception {
        underflowException();
        return this.arr[this.front];
    }

    public int pop() throws Exception {
        underflowException();
        int rv = this.arr[this.front];
        this.front = (this.front + 1) % this.MaxCapacity;
        this.NoOfElements--;
        return rv;
    }
}