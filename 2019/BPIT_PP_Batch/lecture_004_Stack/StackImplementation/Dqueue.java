public class Dqueue extends queue{
    Dqueue(){
        super();
    }

    Dqueue(int n){
        super(n);
    }

    @Override
    public void push(int val){
        if(super.size() == super.maxSize()){
            int[] temp = new int[super.size()];
            int i = 0;
            while(super.size()!=0) temp[i++] = super.remove_();

            super.setValues(temp.length*2);
            
            for(int ele: temp) super.push_(ele);
        }

        super.push_(ele);
    }

}