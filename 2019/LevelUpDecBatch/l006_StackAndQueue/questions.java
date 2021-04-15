import java.util.Stack;
import java.util.ArrayDeque;

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

    // 20
    public boolean isValid(String str) {
        if (str.length() == 0)
            return true;
        int n = str.length();
        if (str.charAt(0) == ')' || str.charAt(0) == ']' || str.charAt(0) == '}')
            return false;
        if (str.charAt(n - 1) == '(' || str.charAt(n - 1) == '[' || str.charAt(n - 1) == '{')
            return false;

        Stack<Character> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{')
                st.push(ch);
            else {
                if (st.size() == 0) // more no of closing brackets
                    return false;
                else if (ch == ')' && st.peek() != '(')
                    return false;
                else if (ch == ']' && st.peek() != '[')
                    return false;
                else if (ch == '}' && st.peek() != '{')
                    return false;
                else
                    st.pop();
            }
        }

        return st.size() == 0; // st.size() != 0; // means No of Opening Brackets
    }

    // 1249
    public String minRemoveToMakeValid(String s) {
        ArrayDeque<Integer> st = new ArrayDeque<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(')
                st.addFirst(i);
            else if (ch == ')') {
                if (st.size() != 0 && s.charAt(st.getFirst()) == '(')
                    st.removeFirst();
                else
                    st.addFirst(i);
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (st.size() != 0 && st.getLast() == i) {
                st.removeLast();
                continue;
            }

            ans.append(s.charAt(i));
        }

        return ans.toString();
    }

    // 32
    public int longestValidParentheses(String s) {
        int n = s.length();
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int len = 0;
        for (int i = 0; i < n; i++) {
            if (st.peek() != -1 && s.charAt(st.peek()) == '(' && s.charAt(i) == ')') {
                st.pop();
                len = Math.max(len, i - st.peek());
            } else {
                st.push(i);
            }
        }

        return len;
    }

}