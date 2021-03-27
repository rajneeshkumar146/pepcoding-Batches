import java.util.Stack;
import java.util.LinkedList;
import java.util.Arrays;

public class questions {
    public static boolean isBalancedBrackets(String str) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{')
                st.push(ch);
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (st.size() == 0)
                    return false; // more closing brackets
                else if (ch == ')' && st.peek() != '(')
                    return false;
                else if (ch == '}' && st.peek() != '{')
                    return false;
                else if (ch == ']' && st.peek() != '[')
                    return false;
                else
                    st.pop();
            }
        }

        return st.size() == 0;
    }

    // NG : Next Greater
    // NS : Next Smaller
    // OR : on Right
    // OL : on Left
    public static int[] NGOR(int[] arr) {
        int n = arr.length;
        LinkedList<Integer> st = new LinkedList<>();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        for (int i = 0; i < n; i++) {
            while (st.size() != 0 && arr[st.getFirst()] < arr[i]) {
                ans[st.removeFirst()] = arr[i];
            }

            st.addFirst(i);
        }
        return ans;
    }

    public static int[] NGOL(int[] arr) {
        int n = arr.length;
        LinkedList<Integer> st = new LinkedList<>();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        for (int i = n - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[st.getFirst()] < arr[i]) {
                ans[st.removeFirst()] = arr[i];
            }

            st.addFirst(i);
        }
        return ans;
    }

    public static int[] NSOR(int[] arr) {
        int n = arr.length;
        LinkedList<Integer> st = new LinkedList<>();
        int[] ans = new int[n];
        Arrays.fill(ans, n);

        for (int i = 0; i < n; i++) {
            while (st.size() != 0 && arr[st.getFirst()] > arr[i]) {
                ans[st.removeFirst()] = i;
            }

            st.addFirst(i);
        }
        return ans;
    }

    public static int[] NSOL(int[] arr) {
        int n = arr.length;
        LinkedList<Integer> st = new LinkedList<>();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        for (int i = n - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[st.getFirst()] > arr[i]) {
                ans[st.removeFirst()] = i;
            }

            st.addFirst(i);
        }
        return ans;
    }

    public static int largestArea(int[] arr) {
        int n = arr.length;
        int[] nsol = NSOL(arr);
        int[] nsor = NSOR(arr);

        int area = 0;
        for (int i = 0; i < n; i++) {
            int h = arr[i];
            int w = nsor[i] - nsol[i] - 1;
            area = Math.max(area, h * w);
        }

        return area;
    }

    public static boolean duplicateBrackets(String str) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ')') {
                st.push(str.charAt(i));
                continue;
            }

            boolean flag = false;
            while (st.size() != 0 && st.peek() != '(') {
                flag = true;
                st.pop();
            }

            if (!flag && st.size() != 0 && st.peek() == '(')
                return true;

            if (st.size() != 0)
                st.pop();
        }

        return false;
    }

    public static boolean duplicateBrackets2(String str) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ')') {
                st.push(str.charAt(i));
                continue;
            }

            boolean isThereExpression = false;
            while (st.peek() != '(') {
                isThereExpression = true;
                st.pop();
            }

            if (!isThereExpression)
                return true;

            st.pop();
        }

        return false;
    }

    // hw : leetcode 42

}