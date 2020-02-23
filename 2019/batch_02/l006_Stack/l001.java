import java.util.Stack;

public class l001 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        int[] arr = { 9, 1, 7, 3, 11, -2, 25, 20, 2, -4 };
        nextGreaterOnRight(arr);
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
}