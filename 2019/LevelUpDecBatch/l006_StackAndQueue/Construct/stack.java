public class stack {
    private int[] arr;
    private int tos;
    private int NoOfElements;
    private int MaxCapacity;

    protected void intialize(int size) {
        this.arr = new int[size];
        this.tos = -1;
        this.NoOfElements = 0;
        this.MaxCapacity = size;
    }

    public stack() {
        intialize(10);
    }

    public stack(int size) {
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

    protected void StackEmptyException() throws Exception {
        if (this.NoOfElements == 0)
            throw new Exception("StackISEmpty");
    }

    protected void StackOverflowException() throws Exception {
        if (this.NoOfElements == this.MaxCapacity)
            throw new Exception("StackOverflow");
    }

    protected void push_(int data){
        this.arr[++this.tos] = data;
        this.NoOfElements++;
    }

    public void push(int data) throws Exception{
        StackOverflowException();
        push_(data);
    }

    protected int top_() {
        return this.arr[this.tos];
    }

    public int top() throws Exception {
        StackEmptyException();
        return top_();
    }

    protected int pop_(){
        int rv = this.arr[this.tos];
        this.arr[this.tos] = 0;
        this.tos--;
        this.NoOfElements--;

        return rv;
    }

    public int pop() throws Exception{
        StackEmptyException();
        return pop_();
    }
}