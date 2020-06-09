public class queue{
    
    protected int[] arr;
    protected int size=0;
    protected int front=0;
    protected int back=0;

    public queue(){
        reassign(10);
    }

    public queue(int size){
        reassign(size);
    }

    public void reassign(int size){
        this.arr=new int[size];
        this.size=0;
        this.front=0;
        this.back=0;
    }

    public int size(){
        return this.size();
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    public void display(){
        for(int i=0;i<this.size;i++){
            int idx=(i+this.front)%this.arr.length;
            System.out.print(arr[idx]+ " ");
        }

        System.out.println();
    }

    protected void push_(int val){
        this.arr[this.back]=val;
        this.back=(this.back+1)%this.arr.length;
        this.size++;
    
    }

    public void push(int val) throws Exception{
        if(this.size==this.arr.length){
            throw new Exception("QueueSizeFull");
        }
         push_(val);
     }

    public int pop() throws Exception{
        if(this.size==0){
            throw new Exception("QueueEmpty");
        }

        int rv=this.arr[this.front];
        this.arr[this.front]=0;
        this.front=(this.front+1)%this.arr.length;
        this.size--;

        return rv;
    }

    public int front() throws Exception{
        if(this.size==0){
            throw new Exception("QueueEmpty");
        }

        return this.arr[this.front];
    }
    
}