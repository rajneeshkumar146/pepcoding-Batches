public class DStack extends Stack{

    DStack(){
      super();
    }

    DStack(int n){
      super(n);
    }

    DStack(int[] arr){
        super(arr.length);
        for(int ele: arr){
            super.push_(ele);
        }
    }

    @Override
    public void push(int val){
        if(super.size() == super.capacity()){
            int[] temp = new int[super.size()];
            int i = super.size() - 1;
            while(super.size()!=0) temp[i--] = super.pop_();

            super.setValues(temp.length * 2);

            for(int ele: temp) super.push_(ele);
        }

        super.push_(val);
    }

}