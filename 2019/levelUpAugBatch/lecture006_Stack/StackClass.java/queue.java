public class stack{
    private int[] arr;
    private int front = 0;   // top of stack
    private int back = 0;
    private int elementCount; // total elements present in stack.

    private void intializeValues(int size){
        this.arr = new int[size];
        this.elementCount = 0;
        this.front = 0
        this.back = 0;
    }

    public stack(){
        intializeValues(10);
    }

    public stack(int size){
        intializeValues(size);
    }

    public int size(){
        return this.elementCount;
    }

    public boolean isEmpty(){
        return this.elementCount == 0;
    }

    private int capacity(){
        return this.arr.length;
    }

    public void push(int data)throws Exception{
        if(this.size() == this.capacity()){
            throw new Exception("QueueIsFull");
        }

        this.arr[this.back] = data;
        this.back = (this.back + 1) % this.capacity();
        this.elementCount++;

    }

    public int front()throws Exception{
        if(this.isEmpty()){
            throw new Exception("QueueIsEmpty");
        }

        return this.arr[this.front];
    }

    public int pop() throws Exception{
        if(this.isEmpty()){
            throw new Exception("QueueIsEmpty");
        }

        int rv = this.arr[this.front];
        this.arr[this.front] = 0;
        this.front = (this.front + 1) % this.capacity();
        this.elementCount--;

        return rv;
    }
}
