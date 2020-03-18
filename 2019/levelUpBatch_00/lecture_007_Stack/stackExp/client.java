public class client {

    public static void main(String[] args) throws Exception {
        // simpleStack();
        simpleQueue();
    }

    public static void simpleStack() throws Exception {
        dynamicStack st = new dynamicStack(3);
        st.push(10);
        st.push(10);
        st.push(10);
        st.push(10);
        System.out.println(st.top());
    }

    public static void simpleQueue() throws Exception {
        queue que = new queue();
        que.push(1);

        System.out.println(que);
        que.display();
    }
}