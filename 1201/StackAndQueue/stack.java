import java.util.EmptyStackException;
public class stack{

    protected int[] arr;
    protected int size=0;
    protected int tos=-1;
    
    public stack(){
        reassign(10);
    }
    
    public stack(int size){
        reassign(size);
    }

    protected void reassign(int size){
        this.arr=new int[size];
        this.size=0;
        this.tos=-1;
    }

    public int size(){
        return this.size();
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    public void display(){
        for(int i=tos;i>=0 ; i--){
          System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    protected void push_(int val){
        this.tos++;
        this.arr[this.tos]=val;
        this.size++;
    }

    public void push(int val) throws Exception{
        if(this.size==this.arr.length){
            throw new Exception("StackIsFull");
        }

        push_(val);
    }

    public int peek(){
        if(this.size==0) throw new EmptyStackException();
        return this.arr[this.tos];
    }

    public int pop(){
        if(this.size==0) throw new EmptyStackException();
        
        int rv=this.arr[this.tos];
        
        this.arr[this.tos]=0;
        this.tos--;
        this.size--;

        return rv;
    }

}