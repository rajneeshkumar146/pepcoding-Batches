import java.util.LinkedList;
import java.util.Arrays;

public class question {
    public static boolean balancedBrackets(String str) {
        LinkedList<Character> st = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{')
                st.addFirst(ch);
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (st.size() == 0) // more closing brackets
                    return false;
                else if (ch == ')' && st.getFirst() != '(')
                    return false;
                else if (ch == ']' && st.getFirst() != '[')
                    return false;
                else if (ch == '}' && st.getFirst() != '{')
                    return false;
                else
                    st.removeFirst();
            }
        }

        return st.size() == 0; // more opening brackets
    }

    public static boolean duplicateBrackets(String str) {
        LinkedList<Character> st = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            boolean isLoopRun = false;
            while (st.size() != 0 && ch == ')' && st.getFirst() != '(') {
                isLoopRun = true;
                st.removeFirst();
            }

            if (!isLoopRun && ch == ')')
                return true;
            else if (isLoopRun)
                st.removeFirst();
            else
                st.addFirst(ch);
        }

        return false;
    }

    public static void nextGreater(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        LinkedList<Integer> st = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (st.size() != 0 && arr[st.getFirst()] < arr[i])
                ans[st.removeFirst()] = arr[i];

            st.addFirst(i);
        }

        for (int ele : ans)
            System.out.println(ele);
    }

    public static void mergeOverlappingIntervals(int[][] arr) {

        // c++
        // sort(arr.begin(), arr.end(),[](int[] a,int[] b){
        // return a[0] < b[0];
        // })

        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0]; // this - other gives default behaviour
            // return b[0] - a[0] // other - this gives reverse of default behaviour
        });

        LinkedList<int[]> st = new LinkedList<>();
        for (int[] a : arr) {
            int minStartTime = a[0];
            int maxEndingTime = a[1];
            while (st.size() != 0 && a[0] <= st.getFirst()[1]) {
                minStartTime = st.getFirst()[0];
                maxEndingTime = Math.max(maxEndingTime, st.getFirst()[1]);
                st.removeFirst();
            }

            st.addFirst(new int[] { minStartTime, maxEndingTime });
        }

        while (st.size() != 0) {
            int[] a = st.removeLast();
            System.out.println(a[0] + " " + a[1]);
        }
    }
}