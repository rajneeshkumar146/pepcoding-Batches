public class dynamicStack extends stack {

    public dynamicStack() {
        resize(10);
    }

    public dynamicStack(int size) {
        resize(size);
    }

    @Override
    public void push(int val) throws Exception {
        if (this.size() == maxSize()) {
            int[] temp = new int[this.size()];
            for (int i = temp.length - 1; i >= 0; i--) {
                temp[i] = top_();
                pop_();
            }

            resize(2 * temp.length);
            for (int i = 0; i < temp.length; i++) {
                push_(temp[i]);
            }
        }

        super.push(val);
    }

}