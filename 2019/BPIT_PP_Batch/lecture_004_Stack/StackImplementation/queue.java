public class queue{
    private int[] arr;
    private int size;
    private int head;
    private int tail;
    private int maxSize;

    public queue(){
        setValues(10);
    }

    public queue(int n){
        setValues(n);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < this.size; i++){
            int idx = (this.head + i) % this.maxSize;
            sb.append(this.arr[idx]);
            if(i != 0) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    protected void setValues(int n) {
        this.arr = new int[n];
        this.size = 0;
        this.head = 0;
        this.tail = 0
        this.maxSize = n;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }
    
    public int size(){
        return this.size;
    }

    public int capacity(){
        return this.maxSize;
    }

    protected void push_(int val){
        this.arr[this.tail] = val;
        this.tail = (this.tail + 1 ) % this.maxSize;
        this.size++;
    }

    public void push(int val) throws Exception{
        if(this.size == this.maxSize){
            throw new Exception("QueueIsFull!");
        }

        push_(val);
    }

    protected int peek_(){
        return this.arr[this.head];
    }

    public int peek() throws Exception{
        if(this.size == 0){
            throw new Exception("QueueIsEmpty!");
        }

        return peek_();
    }

    protected int remove_(){
        int rv = peek();
        this.arr[this.head] = 0;
        this.head = (this.head + 1 ) % this.maxSize;
        this.size--;
        return rv;
    }

    public int remove() throws Exception{
        if(this.size == 0){
            throw new Exception("QueueIsEmpty!");
        }

        return remove_();
    }

}