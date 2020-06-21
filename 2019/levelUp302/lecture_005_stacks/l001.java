public class l001 {

    public static void main(String[] args) {
        solve();
    }

    public static class Mystack {
        private int N = 10000;
        private int[] arr;
        private int sz = 0;
        private int tos = -1;

        Mystack() {
            this.arr = new int[N];
        }

        public Mystack(int size) {
            this.arr = new int[size];
            this.N = size;
        }

        public int size() {
            return this.sz;
        }

        public boolean empty() {
            return this.sz == 0;
        }

        public void push(int val) {
            if (sz == N)
                return;
            tos++;
            sz++;
            arr[tos] = val;
        }

        public int pop() {
            if (sz == 0)
                return -1;
            int rv = arr[tos];
            arr[tos] = -1;
            sz--;
            tos--;
            return rv;
        }

        public int top() {
            if (sz == 0)
                return -1;
            return arr[tos];
        }
    }

    public static void solve() {
        Mystack st = new Mystack(10);

        for (int i = 1; i <= 20; i++)
            st.push(10 + i);

        while (st.size() != 0) {
            System.out.println(st.top() + " ");
            st.pop();
        }
    }

}
