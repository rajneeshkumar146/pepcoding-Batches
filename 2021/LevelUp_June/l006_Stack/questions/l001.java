import java.util.LinkedList;
import java.util.Arrays;

public class l001 {
    public static int[] NGOR(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, n);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);

        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i])
                ans[st.removeFirst()] = i;

            st.addFirst(i);
        }

        return ans;
    }

    public static int[] NSOR(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, n);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);

        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] > arr[i])
                ans[st.removeFirst()] = i;

            st.addFirst(i);
        }

        return ans;
    }

    public static int[] NGOL(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);

        for (int i = n - 1; i >= 0; i--) {
            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i])
                ans[st.removeFirst()] = i;

            st.addFirst(i);
        }

        return ans;
    }

    public static int[] NSOL(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);

        for (int i = n - 1; i >= 0; i--) {
            while (st.getFirst() != -1 && arr[st.getFirst()] > arr[i]) 
                ans[st.removeFirst()] = i;

            st.addFirst(i);
        }

        return ans;
    }

}