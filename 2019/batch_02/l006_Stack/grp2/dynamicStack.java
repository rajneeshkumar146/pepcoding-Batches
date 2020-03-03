public class dynamicStack extends stack {

    public dynamicStack() {
        super();
    }

    public dynamicStack(int size) {
        super(size);
    }

    public dynamicStack(int[] arr) {
        super(arr);
    }

    @Override
    public void push(int data) {
        if (size() == Max_size()) {
            int[] temp = new int[size()];
            for (int i = temp.length - 1; i >= 0; i--) {
                temp[i] = pop();
            }

            resize(2 * temp.length);
            for (int val : temp) {
                push_(val);
            }
        }

        super.push(data);
    }

}