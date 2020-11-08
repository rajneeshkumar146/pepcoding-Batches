public class stack{
    private int[] arr;
    private int tos;   // top of stack
    private int elementCount; // total elements present in stack.

    protected void intializeValues(int size){   // -> can be protected
        this.arr = new int[size];
        this.tos = -1;
        this.elementCount = 0;
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

    protected int capacity(){   // can be protected
        return this.arr.length;
    }

    public void push(int data)throws Exception{
        if(this.size() == this.capacity()){
            throw new Exception("StackIsFull");
        }
    
        this.arr[++this.tos] = data;
        this.elementCount++;
    }

    // private void CheckException_peek()throws Exception{
    //     if(this.isEmpty()){
    //         throw new Exception("Rajneesh_StackIsEmpty");
    //     }
    // }

    public int peek()throws Exception{
        // try{
        //    CheckException_peek();
        //    return this.arr[this.tos];
        // }catch(Exception e){
        //     System.out.println("handel");
        // }

        if(this.isEmpty()){
            throw new Exception("StackIsEmpty");
        }

        return this.arr[this.tos];
    }

    public int pop() throws Exception{
        if(this.isEmpty()){
            throw new Exception("StackIsEmpty");
        }

        int rv = this.arr[this.tos];
        this.arr[this.tos--] = 0;
        this.elementCount--;

        return rv;
    }
}