public class DStack extends Stack{

    DStack(){
        super(10);
    }

    DStack(int n){
        super(n);
    }

    DStack(int[] arr) {
        super(arr.length * 2);
        for(int ele: arr) super.push_(ele);
    }

    @Override
    public void push(int val) throws Exception{
        if(super.size() == super.capacity()){
            int[] temp = new int[super.size()];

            int i = temp.length - 1;
            while(super.size()!= 0) temp[i--] = super.pop();
            
            super.assignSize(temp.length * 2);

            for(int ele: temp) super.push_(ele);
        }

        super.push_(val);
    }


}