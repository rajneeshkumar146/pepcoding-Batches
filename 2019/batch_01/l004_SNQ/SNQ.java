import java.util.LinkedList;
import java.util.Stack;

public class SNQ {
    public static void main(String[] args) {
        // System.out.println(infixEval("8+4*3-9/3^(2-1)"));
        // int[] arr = { 6, 2, 5, 4, 5, 1, 6 };
        int[] arr = { 1, 0, 1 };
        // nextGreterOnLeftSide(arr);
        // largestArea(arr);
        System.out.println(largestArea_01(arr));
    }

    public static boolean isOperator(Character ch) {

        if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^')
            return true;
        return false;
    }

    public static int priority(Character ch) {
        if (ch == '+' || ch == '-')
            return 0;
        else if (ch == '*' || ch == '/')
            return 1;
        else if (ch == '^')
            return 2;

        return -1;
    }

    public static int performOperation(int val1, int val2, Character ch) {

        if (ch == '+')
            return val2 + val1;
        else if (ch == '-')
            return val2 - val1;
        else if (ch == '*')
            return val2 * val1;
        else if (ch == '/')
            return val2 / val1;
        else
            return (int) Math.pow(val2, val1);

    }

    public static int infixEval(String str) {
        Stack<Integer> numSt = new Stack<>();
        Stack<Character> opSt = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9')
                numSt.push(ch - '0');
            else if (ch == '(')
                opSt.push(ch);
            else if (isOperator(ch)) {
                while (opSt.size() != 0 && opSt.peek() != '(' && priority(opSt.peek()) > priority(ch)) {
                    int val1 = numSt.pop();
                    int val2 = numSt.pop();
                    char c = opSt.pop();

                    int ans = performOperation(val1, val2, c);
                    numSt.push(ans);
                }
                opSt.push(ch);
            } else {
                while (opSt.peek() != '(') {
                    int val1 = numSt.pop();
                    int val2 = numSt.pop();
                    char c = opSt.pop();

                    int ans = performOperation(val1, val2, c);
                    numSt.push(ans);
                }

                opSt.pop();
            }
        }

        while (opSt.size() != 0) {
            int val1 = numSt.pop();
            int val2 = numSt.pop();
            Character c = opSt.pop();

            int ans = performOperation(val1, val2, c);
            numSt.push(ans);

        }

        return numSt.pop();
    }

    public static void nextGreterOnLeftSide(int[] arr) {

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {

            while (st.size() != 0 && st.peek() <= arr[i]) {
                st.pop();
            }

            if (st.size() == 0)
                System.out.println(arr[i] + " -> " + -1);
            else
                System.out.println(arr[i] + " -> " + st.peek());

            st.push(arr[i]);
        }
    }

    public static void largestArea(int[] height) {
        int maxArea = 0;

        Stack<Integer> st = new Stack<>();

        int i = 0;
        for (i = 0; i < height.length; i++) {
            if (st.size() == 0 || height[st.peek()] <= height[i])
                st.push(i);
            else {

                while (st.size() != 0 && height[st.peek()] >= height[i]) {
                    int idx = st.pop();
                    int he = height[idx];
                    int width = i - (st.size() == 0 ? -1 : st.peek()) - 1;
                    int currArea = he * width;

                    maxArea = Math.max(maxArea, currArea);
                }
                st.push(i);
            }
        }

        while (st.size() != 0) {
            int idx = st.pop();
            int he = height[idx];
            int width = i - (st.size() == 0 ? -1 : st.peek()) - 1;
            int currArea = he * width;

            maxArea = Math.max(maxArea, currArea);
        }

        System.out.println(maxArea);

    }

    public static int largestArea_01(int[] height) {
        int maxArea = 0;

        Stack<Integer> st = new Stack<>();
        st.push(-1);

        int i = 0;
        while (i < height.length) {
            while (st.peek() != -1 && height[st.peek()] >= height[i]) {
                int ht = height[st.pop()];
                int area = ht * (i - st.peek() - 1);
                maxArea = Math.max(maxArea, area);
            }
            st.push(i);
            i++;
        }

        while (st.peek() != -1) {
            int ht = height[st.pop()];
            int area = ht * (i - st.peek() - 1);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public static int maxAreaInMatrix(char[][] arr) {
        if (arr.length == 0)
            return 0;
        int[] ar = new int[arr[0].length];
        int max_ = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                ar[j] = arr[i][j] == '0' ? 0 : ar[j] + 1;
            }
            max_ = Math.max(max_, largestArea_01(ar));
        }
        return max_;
    }

    public static class MinStack {
        Stack<Integer> st = new Stack<>();
        int MinSf = 0;

        public void push(int val) {
            if (st.size() == 0) {
                st.push(val);
                MinSf = val;
                return;
            }

            if (MinSf < val)
                st.push(val);
            else {
                st.push(2 * val - MinSf);
                MinSf = val;
            }

        }

        public int pop() {

            if (st.size() == 0)
                return -1;

            if (st.peek() < MinSf) {
                int actualValue = MinSf;
                int recoveredMinSf = 2 * MinSf - st.pop();

                MinSf = recoveredMinSf;
                return actualValue;
            } else
                return st.pop();
        }

        public int top() {
            if (st.size() == 0)
                return -1;

            if (st.peek() < MinSf)
                return minsf;
            else
                return st.peek();
        }

        public int getMin() {
            if (st.size() == 0)
                return -1;
            return MinSf;
        }

    }

    public static boolean balancedBracket(String str) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (st.size() == 0)
                    return false;

                if (ch == ')' && st.peek() == '(')
                    st.pop();
                else if (ch == ']' && st.peek() == '[')
                    st.pop();
                else if (ch == '}' && st.peek() == '{')
                    st.pop();
                else {
                    return false;
                }
            }
        }

        return st.size() == 0;

    }

}