public class queue {

    // class variables
    private int[] arr;
    private int front = 0, back = 0;
    private int sizeOfArray = 0;
    private int sizeOfQueue = 0;

    // constructor
    public queue() {
        this.initilize(5);
    }

    public queue(int size) {
        this.initilize(size);
    }

    public void initilize(int size) {
        this.arr = new int[size];
        this.front = this.back = 0;
        this.sizeOfArray = size;
        this.sizeOfQueue = 0;
    }

    // exceptions
    private void queueIsFullException() throws Exception {
        if (this.sizeOfArray == this.sizeOfQueue)
            throw new Exception("queueIsFull");

    }

    private void queueIsEmptyException() throws Exception {
        if (this.sizeOfQueue == 0)
            throw new Exception("queueIsEmpty");
    }

    // basic
    public boolean isEmpty() {
        return this.sizeOfQueue == 0;
    }

    public int size() {
        return this.sizeOfQueue;
    }

    public void display() {
        for (int i = 0; i < this.sizeOfQueue; i++) {
            int idx = (this.front + i) % this.sizeOfArray;
            System.out.print(this.arr[this.idx] + " ");
        }
    }

    // functions

    public void push(int val) throws Exception {
        this.arr[this.back] = val;
        this.sizeOfQueue++;
        this.back = (this.back + 1) % this.sizeOfArray;
    }

    public int pop() throws Exception {
        int val = this.front();
        this.arr[this.front] = 0;
        this.sizeOfQueue--;
        this.front = (this.front + 1) % this.sizeOfArray;

        return val;
    }

    public int front() throws Exception {
        return this.arr[this.front];
    }
}