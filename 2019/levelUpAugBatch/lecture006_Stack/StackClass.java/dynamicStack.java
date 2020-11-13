public class dynamicStack extends stack{

    dynamicStack(){
        super();
    }

    dynamicStack(int size){
        super(size);
    }

    
    @Override
    public void push(int data) throw Exception {
        if(super.size() == super.capacity()){
            int cap = super.capacity();
            
            int[] temp = new int[cap];
            int idx = cap - 1;

            while(super.size() != 0) temp[idx--] = super.pop(); 

            super.intializeValues(cap * 2);
            
            idx = 0;
            while(idx < cap) super.push(temp[idx++]);
        }

        super.push(data);
    }
}