public class stack {
    private int[] arr;
    private int tos;
    private int NoOfElements;
    private int MaxCapacity;

    stack(int size) {
        initialize(size);
    }

    stack() {
        this(10);
    }

    protected void initialize(int size) {
        this.NoOfElements = 0;
        this.MaxCapacity = size;
        this.arr = new int[this.MaxCapacity];
        this.tos = -1;
    }

    private void overflowException() throws Exception {
        if (this.NoOfElements == this.MaxCapacity)
            throw new Exception("StackIsOverflow");
    }

    private void underflowException() throws Exception {
        if (this.NoOfElements == 0)
            throw new Exception("StackIsUnderflow");
    }

    public void push(int data) throws Exception {
        overflowException();
        this.arr[++this.tos] = data;
        this.NoOfElements++;
    }

    public int peek() throws Exception {
        underflowException();
        return this.arr[this.tos];
    }

    public int pop() throws Exception {
        underflowException();
        int rv = this.arr[this.tos--];
        this.NoOfElements--;
        return rv;
    }
}