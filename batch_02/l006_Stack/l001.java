import java.util.Stack;

public class l001 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // int[] arr = { 9, 1, 7, 3, 11, -2, 25, 20, 2, -4 };
        // nextGreaterOnRight(arr);

        MinStack2 st = new MinStack2();
        st.push(-2);
        st.push(0);
        st.push(-3);
        System.out.println(st.pop());
        System.out.println(st.getMin());

    }

    public static void nextGreaterOnRight(int[] arr) {
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (st.size() == 0) {
                st.push(arr[i]);
                continue;
            }

            while (st.size() != 0 && st.peek() < arr[i]) {
                System.out.println(st.pop() + " -> " + arr[i]);

            }

            st.push(arr[i]);
        }

        while (st.size() != 0) {
            System.out.println(st.pop() + " -> " + "no such ele");
        }
    }

    public static int[] asteroidCollision(int[] arr) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0)
                st.push(arr[i]);
            else {
                while (st.size() != 0 && st.peek() > 0 && st.peek() < -arr[i]) {
                    st.pop();
                }

                if (st.size() != 0 && st.peek() == -arr[i])
                    st.pop();
                else if (st.size() == 0 || st.peek() < 0)
                    st.push(arr[i]);
            }
        }

        int[] ans = new int[st.size()];
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = st.pop();
        }

        return ans;
    }

    public String removeKdigits(String num, int k) {
        char[] arr = num.toCharArray();
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (st.size() != 0 && st.peek() > arr[i] && k != 0) {
                st.pop();
                k--;
            }

            st.push(arr[i]);
        }

        while (k-- > 0) {
            st.pop();
        }

        StringBuilder sb = new StringBuilder();
        while (st.size() != 0) {
            sb.append(st.pop());
        }

        if (sb.length() != 0 && sb.charAt(sb.length() - 1) == '0') {
            int i = sb.length() - 2;
            for (; i >= 0; i--) {
                if (sb.charAt(i) != '0')
                    break;
            }

            sb = new StringBuilder(sb.substring(0, i + 1));

        }
        sb.reverse();
        String ans = sb.toString();
        return ans.length() != 0 ? ans : "0";
    }

    public static int maxAreaInHistogram(int[] arr) {
        int maxArea = 0;
        Stack<Integer> st = new Stack<>();
        st.push(-1);

        for (int i = 0; i < arr.length; i++) {
            while (st.peek() != -1 && arr[st.peek()] >= arr[i]) {
                int height = arr[st.pop()];
                int leftb = st.peek();
                int width = (i - leftb - 1);
                int area = width * height;

                if (area > maxArea)
                    maxArea = area;
            }
            st.push(i);
        }

        while (st.peek() != -1) {
            int height = arr[st.pop()];
            int leftb = st.peek();
            int width = (arr.length - leftb - 1);
            int area = width * height;

            if (area > maxArea)
                maxArea = area;
        }

        return maxArea;

    }

    public static int maximalRectangle(char[][] matrix) {
        int[] height = new int[matrix[0].length];
        int area = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int val = matrix[i][j] - '0';
                if (val == 1) {
                    height[j] += val;
                } else {
                    height[j] = 0;
                }
            }

            area = Math.max(area, maxAreaInHistogram(height));
        }
        return area;
    }

    static class MinStack {
        Stack<Integer> st;
        Stack<Integer> minSt;
        int min = Integer.MAX_VALUE;

        public MinStack() {
            st = new Stack<>();
            minSt = new Stack<>();
        }

        public void push(int x) {
            st.push(x);
            minSt.push(Math.min(min, x));
            min = minSt.peek();
        }

        public void pop() {
            if (st.size() == 0)
                return;

            st.pop();
            minSt.pop();

            if (minSt.size() != 0)
                min = minSt.peek();
            else
                min = Integer.MAX_VALUE;
        }

        public int top() {
            return st.peek();
        }

        public int getMin() {
            return minSt.peek();
        }
    }

    static class MinStack2 {
        Stack<Long> st;
        long minsf = 0;

        public MinStack2() {
            st = new Stack<>();
        }

        public void push(int x) {
            long val = x;
            if (st.size() == 0) {
                minsf = val;
                st.push(val);
                return;
            }

            if (val > minsf) {
                st.push(val);
            } else {
                st.push(val - minsf + val);
                minsf = val;
            }
        }

        public void pop() {
            if (st.peek() <= minsf) {
                minsf = minsf - st.peek() + minsf;
            }
            st.pop();
        }

        public int top() {
            if (st.peek() <= minsf)
                return minsf;

            long val = st.peek();
            return (int) val;
        }

        public int getMin() {
            return (int) minsf;
        }
    }

    public static boolean balanceBrackets(char[] arr) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (ch == '(' || ch == '{' || ch == '[' || ch == '<')
                st.push(ch);
            else {
                if (st.size() == 0)
                    return false;
                else if (st.peek() == '(' && ch != ')')
                    return false;
                else if (st.peek() == '{' && ch != '}')
                    return false;
                else if (st.peek() == '[' && ch != ']')
                    return false;
                else if (st.peek() == '<' && ch != '>')
                    return false;

                st.pop();
            }
        }

        return st.size() == 0;
    }

    public static int rainWater(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int ans = 0;
        st.push(-1);

        for (int i = 0; i < arr.length; i++) {
            while (st.peek() != -1 && arr[i] > arr[st.peek()]) {
                int h = arr[st.pop()];
                if (st.peek() == -1)
                    break;
                int width = i - st.peek() - 1;

                int height = (Math.min(arr[st.peek()], arr[i]) - h);
                ans += width * height;
            }

            st.push(i);
        }

        return ans;

    }

}