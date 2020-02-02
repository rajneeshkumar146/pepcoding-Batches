public class client {
    public static void main(String[] args) {
        DynamicStack st = new DynamicStack();
        // MyQueue qu = new MyQueue();
        for (int i = 0; i < 30; i++) {
            st.push(i + 100);
        }

        st.print();

    }
}