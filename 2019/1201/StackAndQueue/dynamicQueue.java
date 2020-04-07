public class dynamicQueue extends queue{
    
    public dynamicQueue(){
        super();
    }

    
    public dynamicQueue(int size){
        super(size);
    }

    @Override
    public void push(int val)  throws Exception{
        if(this.size==this.arr.length){         
           int[] temp=this.arr;    //copy old data.

           int fr=this.front;
           reassign(2*temp.length);

           for(int i=0;i<temp.length;i++){
            int idx=(i+fr)%temp.length;
            push(temp[idx]);
           }
        }

        super.push(val);
    }

}