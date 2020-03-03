public class client {
    public static void main(String[] args) throws Exception {
        // stackFuntion();
        // stackFuntion2();
        queueFuntion();
    }

    public static void stackFuntion() {
        stack st = new stack(10);
        for (int i = 0; i < 5; i++)
            st.push(i + 13);

        while (!st.isEmpty()) {
            System.out.println(st.pop());
        }
    }

    public static void stackFuntion2() {
        dynamicStack st = new dynamicStack();
        for (int i = 0; i <= 10; i++)
            st.push(i + 1);

        st.push(100);
        System.out.println(st.size());

    }

    public static void queueFuntion() throws Exception {
        dynamicQueue que = new dynamicQueue();
        for (int i = 0; i <= 10; i++)
            que.push(i);

        // for (int i = 0; i <= 11; i++)
        // que.pop();

        System.out.println(que);
        System.out.println(que.b);
    }
}