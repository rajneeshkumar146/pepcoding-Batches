import java.util.LinkedList;
import java.util.Stack;

public class SNQ {
    public static void main(String[] args) {
        // System.out.println(infixEval("8+4*3-9/3^(2-1)"));
        int[] arr = { 6, 2, 5, 4, 5, 1, 6 };
        nextGreterOnLeftSide(arr);
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

        for (int i = 0; i < height.length; i++) {
            if (st.size() == 0 || height[st.peek()] <= height[i])
                st.push(i);
            else {

                while (st.size() != 0 && height[st.peek()] > height[i]) {
                    int idx = st.pop();
                    int he = height[idx];
                    int width = i - (st.size() == 0 ? -1 : st.peek()) - 1;
                    int currArea = he * width;

                    maxArea = Math.max(maxArea, currArea);
                }
            }
        }

        while (st.size() != 0 && height[st.peek()] > height[i]) {
            int idx = st.pop();
            int he = height[idx];
            int width = i - (st.size() == 0 ? -1 : st.peek()) - 1;
            int currArea = he * width;

            maxArea = Math.max(maxArea, currArea);
        }

    }

}