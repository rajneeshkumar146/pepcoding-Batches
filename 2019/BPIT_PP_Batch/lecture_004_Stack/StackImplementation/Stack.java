public class Stack<T>{
    
    private Object[] st;
    private int size;
    private int tos;
    private int maxSize;
   
    public Stack(){
        setValues(10);
    }

    public Stack(int n){
        setValues(n);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = this.tos; i >= 0; i--){
            sb.append(this.st[i]);
            if(i != 0) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    protected void setValues(int n) {
        this.st = new Object[n];
        this.size = 0;
        this.tos = -1;
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

    protected void push_(T val){
        this.st[++this.tos] = (T)val;
        this.size++;
        ++iteratorIdx;
    }

    public void push(T val) throws Exception{
        if(this.size == this.maxSize){
            throw new Exception("StackIsFull!");
        }

        push_(val);
    }

    protected T top_(){
        return (T)this.st[this.tos];
    }

    public T top() throws Exception{
        if(this.size == 0){
            throw new Exception("StackIsEmpty!");
        }

        return top_();
    }

    protected T pop_(){
        T rv = (T)top_();
        this.st[this.tos--] = 0;
        this.size--;
        return rv;
    }

    public T pop() throws Exception{
        if(this.size == 0){
            throw new Exception("StackIsEmpty!");
        }

        return pop_();
    }
   

}