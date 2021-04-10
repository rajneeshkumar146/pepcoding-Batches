public class queue {
    private int[] arr;
    private int NoOfElements;
    private int MaxCapacity;

    protected int Capacity() {
        return this.MaxCapacity;
    }

    public int size() {
        return this.NoOfElements;
    }

    public boolean isEmpty() {
        return this.NoOfElements == 0;
    }

    public void display(){
        
    }

    protected void QueueEmptyException() throws Exception {
        if (this.NoOfElements == 0)
            throw new Exception("QueueISEmpty");
    }

    protected void QueueOverflowException() throws Exception {
        if (this.NoOfElements == this.MaxCapacity)
            throw new Exception("QueueOverflow");
    }

    protected void push_(int data){
    }

    public void push(int data) throws Exception{
        QueueOverflowException();
        push_(data);
    }

    protected int front_() {
        return -1;
    }

    public int front() throws Exception {
        QueueEmptyException();
        return front_();
    }

    protected int pop_(){
        return -1;
    }

    public int pop() throws Exception{
        QueueEmptyException();
        return pop_();
    }

}