public class stack {
    protected int[] arr = null;
    protected int capacity = 0; // maximum element that array can hold in it.
    protected int elementCount = 0; // No of elements present in stack.
    protected int tos = -1;

    // constructor.=============================================

    protected void intializeVariables(int capacity) {
        this.capacity = capacity;
        this.arr = new int[this.capacity];
        this.elementCount = 0;
        this.tos = -1;
    }

    public stack() {
        intializeVariables(10); // default capacity.
    }

    public stack(int size) {
        intializeVariables(size);
    }

    // basic Function.=========================================

    public int size() {
        return this.elementCount;
    }

    public boolean isEmpty() {
        return this.elementCount == 0;
    }

    public void display() {

    }

    // Exceptions.=============================================

    private void OverflowException() throws Exception {
        if (this.capacity == this.elementCount) {
            throw new Exception("StackIsFull");
        }
    }

    private void underFlowException() throws Exception {
        if (this.elementCount == 0) {
            throw new Exception("StackIsEmpty");
        }
    }

    // stack functions=========================================
    protected void push_(int data) {
        this.arr[++this.tos] = data;
        this.elementCount++;
    }

    public void push(int data) throws Exception {
        OverflowException();
        push_(data);
    }

    public int top() throws Exception {
        underFlowException();
        return this.arr[this.tos];
    }

    protected int pop_() {
        int rv = this.arr[this.tos];
        this.arr[this.tos--] = 0;
        this.elementCount--;
        return rv;
    }

    public int pop() throws Exception {
        underFlowException();
        return pop_();
    }
}