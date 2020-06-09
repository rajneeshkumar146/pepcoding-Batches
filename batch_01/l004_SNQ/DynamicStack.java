public class DynamicStack extends Mystack { // A : B

    DynamicStack() {

    }

    DynamicStack(int size) {
        super(size);
        // this.st = new int[size];
    }

    @Override
    public void push(int data) {
        if (this.idx == st.length - 1) {
            int[] temp = new int[this.st.length * 2];
            for (int i = 0; i < st.length; i++) {
                temp[i] = st[i];
            }
            
            this.st = temp;
        }

        super.push(data);
    }
}