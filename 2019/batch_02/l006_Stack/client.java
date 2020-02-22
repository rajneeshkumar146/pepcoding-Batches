import java.util.Stack;

public class client {

    public static void main(String[] args) {
        // stack st = new stack(5);
        // st.tos = 4;
        // st.push(10);
        // st.push(20);
        // st.push(30);
        // st.push(40);
        // st.push(50);
        // // st.push(60);

        // while (!st.isEmpty()) {
        // System.out.println(st.pop());
        // }
        // st.pop();

        // queue que = new queue(5);
        // que.push(10);
        // que.push(20);
        // que.push(30);
        // que.push(40);
        // que.push(50);
        // que.pop();
        // que.pop();
        // que.pop();
        // que.push(60);

        // que.display();

        solve();

    }

    public static void solve() {
        // dynamicStack st = new dynamicStack(10);
        // stack st = new stack(10);

        // dynamicStack st = new stack(10);
        stack st = new dynamicStack(10);

        // st.fun1();
        // st.fun2();
        st.fun();

        System.out.println(st.a);
        System.out.println(st.b);
        // System.out.println(st.c);

        // st.push(10);
        // st.display();

    }
}