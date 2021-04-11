import java.util.Stack;

public class question {
    // N : Next, G = greater, S : Smaller, L : Left, R : Right
    public static void NGOR(int[] arr, int[] ans) {
        int n = arr.length;
        Arrays.fill(ans, n); // Java : Arrays.fill(ans,n);

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (st.length != 0 && arr[st.peek()] < arr[i]) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }

    public static void NGOL(int[] arr, int[] ans) {
        int n = arr.length;
        Arrays.fill(ans, -1); // Java : Arrays.fill(ans,-1);

        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (st.length != 0 && arr[st.peek()] < arr[i]) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }

    public static void NSOR(int[] arr, int[] ans) {
        int n = arr.length;
        Arrays.fill(ans, n); // Java : Arrays.fill(ans,n);

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (st.length != 0 && arr[st.peek()] > arr[i]) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }

    public static void NSOL(int[] arr, int[] ans) {
        int n = arr.length;
        Arrays.fill(ans, -1); // Java : Arrays.fill(ans,-1);

        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (st.length != 0 && arr[st.peek()] > arr[i]) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
    }

    // https://practice.geeksforgeeks.org/problems/stock-span-problem-1587115621/1
    public static int[] NGOL(int[] arr, int n) {
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[st.peek()] < arr[i]) {
                ans[st.peek()] = i;
                st.pop();
            }

            st.push(i);
        }
        return ans;
    }

    public static int[] calculateSpan(int[] arr, int n) {
        int[] ans = NGOL(arr, n);
        for (int i = 0; i < n; i++)
            ans[i] = i - ans[i];
        return ans;
    }

    // 901
    class StockSpanner {
        Stack<int[]> st;
        int day;

        public StockSpanner() {
            this.st = new Stack<>();
            this.day = 0;

            st.push(new int[] { -1, -1 }); // {day,Data}
        }

        public int next(int price) {
            while (st.peek()[0] != -1 && st.peek()[1] <= price)
                st.pop();

            int span = day - st.peek()[0];
            st.push(new int[] { day++, price });
            return span;
        }
    }

}