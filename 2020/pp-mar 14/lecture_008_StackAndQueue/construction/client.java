import java.util.LinkedList;

public class client {

    public static void stackTest() throws Exception {
        stack st = new stack(10);

        for (int i = 1; i <= 10; i++)
            st.push(i * 10);

        System.out.println(st);
        while (st.size() != 5) {
            System.out.println(st.pop());
        }
    }

    public static void stackBehaviourUsingLL() {
        LinkedList<Integer> st = new LinkedList<>();

        for (int i = 1; i <= 10; i++)
            st.addFirst(i * 10);

        while (st.size() != 0) {
            System.out.println(st.removeFirst());
        }
    }

    public static void queueTest_01() throws Exception {
        queue que = new queue(4);

        que.add(10);
        que.add(20);
        que.remove();
        que.add(30);
        que.remove();
        que.add(40);
        que.add(50);
        que.add(60);

        // while (que.size() != 0) {
        // System.out.println(que.remove());
        // }

        System.out.println(que);
    }

    public static void queueTest_02() throws Exception {
        queue que = new queue(10);
        for (int i = 1; i <= 10; i++)
            que.add(i * 10);

        while (que.size() != 0) {
            System.out.println(que.remove());
        }
    }

    public static void main(String[] args) throws Exception {
        stackBehaviourUsingLL();
    }
}