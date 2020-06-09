public class dynamicQueue extends queue {

    dynamicQueue() {
        super();
    }

    dynamicQueue(int size) {
        super(size);
    }

    @Override
    public void push(int data) {
        if (this.size() == que.length) {
            int[] temp = st;
            st = new int[2 * temp.length];

            for (int i = 0; i < this.size; i++) {
                st[i] = temp[(this.head + i) % temp.length];
            }
            this.head = 0;
            this.tail = temp.length - 1;
        }
        super.push(data);
    }
}