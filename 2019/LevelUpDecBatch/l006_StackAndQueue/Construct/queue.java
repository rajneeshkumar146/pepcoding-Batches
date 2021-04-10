public class queue {
    private int[] arr;
    private int NoOfElements;
    private int MaxCapacity;
    private int front;
    private int back;

    protected void intialize(int size) {
        this.arr = new int[size];
        this.front = this.back = 0;
        this.NoOfElements = 0;
        this.MaxCapacity = size;
    }

    public queue() {
        intialize(10);
    }

    public queue(int size) {
        intialize(size);
    }

    protected int Capacity() {
        return this.MaxCapacity;
    }

    public int size() {
        return this.NoOfElements;
    }

    public boolean isEmpty() {
        return this.NoOfElements == 0;
    }

    public void display() {
        
        for (int i = 0; i < this.NoOfElements; i++) {
            int idx = (this.front + i) % this.MaxCapacity;
            System.out.print(this.arr[idx] + " ");
        }

    }

    protected void QueueEmptyException() throws Exception {
        if (this.NoOfElements == 0)
            throw new Exception("QueueISEmpty");
    }

    protected void QueueOverflowException() throws Exception {
        if (this.NoOfElements == this.MaxCapacity)
            throw new Exception("QueueOverflow");
    }

    protected void push_(int data) {
        this.arr[this.back] = data;
        this.back = (++this.back % this.MaxCapacity);
        this.NoOfElements++;
    }

    public void push(int data) throws Exception {
        QueueOverflowException();
        push_(data);
    }

    protected int front_() {
        return this.arr[this.front];
    }

    public int front() throws Exception {
        QueueEmptyException();
        return front_();
    }

    protected int pop_() {
        int rv = this.arr[this.front];
        this.arr[this.front] = 0;
        this.front = (++this.front % this.MaxCapacity);
        this.NoOfElements--;

        return rv;
    }

    public int pop() throws Exception {
        QueueEmptyException();
        return pop_();
    }

}