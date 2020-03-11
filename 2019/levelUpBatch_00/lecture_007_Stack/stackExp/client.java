public class client {

    public static void main(String[] args) throws Exception {
        simpleStack();
    }

    public static void simpleStack() throws Exception {
        dynamicStack st = new dynamicStack(3);
        st.push(10);
        st.push(10);
        st.push(10);
        st.push(10);
        System.out.println(st.top());
    }
}