public class dynamicQueue extends queue {
    int b=30;
    public dynamicQueue() {
        super();
    }

    public dynamicQueue(int size) {
        super(size);
    }

    public dynamicQueue(int[] arr) {
        super(arr);
    }

    @Override
    public void push(int data) throws Exception {
        if (size == que.length) {
            int[] temp = this.que;
            resize(2 * temp.length);

            for (int i = 0; i < size; i++) {
                que[i] = temp[(front + i) % temp.length];
            }

            front = 0;
            last = temp.length;
        }

        super.push(data);
    }

}