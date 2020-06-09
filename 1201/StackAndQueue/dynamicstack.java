public class dynamicstack extends stack{

    public dynamicstack(){
    super();
    }

    
    public dynamicstack(int size){
        super(size);
    }

    @Override
    public void push(int val) throws Exception{
        if(this.size==this.arr.length){
            int[] temp=this.arr; //old data copy
            reassign(2*temp.length);

            for(int ele: temp){
                push_(ele);
            }
        }

        super.push(val);
    }
    

}