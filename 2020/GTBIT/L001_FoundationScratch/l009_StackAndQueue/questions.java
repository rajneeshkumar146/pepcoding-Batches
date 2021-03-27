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

    }

    public static int[] NSOR(int[] arr) {
        int n = arr.length;
        LinkedList<Integer> st = new LinkedList<>();
        int[] ans = new int[n];

    }

    public static int[] NSOL(int[] arr) {
        int n = arr.length;
        LinkedList<Integer> st = new LinkedList<>();
        int[] ans = new int[n];

    }

}