import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

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

    // 503
    public int[] nextGreaterElements(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);

        for (int i = 0; i < 2 * n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i % n])
                ans[st.removeFirst()] = i % n;

            if (i < n)
                st.addFirst(i);
        }

        return ans;
    }

    // https://practice.geeksforgeeks.org/problems/stock-span-problem-1587115621/1
    public static int[] calculateSpan(int[] arr, int n) {
        int[] ans = new int[n];
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);

        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] <= arr[i])
                st.removeFirst();

            ans[i] = i - st.getFirst();
            st.addFirst(i);
        }

        return ans;
    }

    // 901
    class StockSpanner {
        int day = 0;
        LinkedList<int[]> st = new LinkedList<>();

        public StockSpanner() {
            // {idx, val}
            st.addFirst(new int[] { -1, -1 });
        }

        public int next(int price) {
            while (st.getFirst()[0] != -1 && st.getFirst()[1] <= price)
                st.removeFirst();

            int span = day - st.getFirst()[0];
            st.addFirst(new int[] { day++, price });
            return span;
        }
    }

    // HM -> 739

    // 20
    public boolean isValid(String str) {
        LinkedList<Character> st = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{')
                st.addFirst(ch);
            else {
                if (st.size() == 0)
                    return false;
                else if (st.getFirst() == '(' && ch != ')')
                    return false;
                else if (st.getFirst() == '[' && ch != ']')
                    return false;
                else if (st.getFirst() == '{' && ch != '}')
                    return false;
                else
                    st.removeFirst();
            }
        }

        return st.size() == 0;
    }

    // 735
    public int[] asteroidCollision(int[] arr) {
        int n = arr.length;
        LinkedList<Integer> st = new LinkedList<>();

        for (int ele : arr) {
            if (ele > 0) {
                st.addFirst(ele);
                continue;
            }

            while (st.size() != 0 && st.getFirst() > 0 && st.getFirst() < -ele)
                st.removeFirst();

            if (st.size() != 0 && st.getFirst() == -ele)
                st.removeFirst();
            else if (st.size() == 0 || st.getFirst() < 0)
                st.addFirst(ele);
            else {
                // nothing to do
            }
        }

        int[] ans = new int[st.size()];
        int idx = ans.length - 1;
        while (st.size() != 0) {
            ans[idx--] = st.removeFirst();
        }

        return ans;
    }

    // 946
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length, idx = 0;
        LinkedList<Integer> st = new LinkedList<>();
        for (int ele : pushed) {
            st.addFirst(ele);
            while (st.size() != 0 && st.getFirst() == popped[idx]) {
                idx++;
                st.removeFirst();
            }
        }

        return st.size() == 0;
    }

    // 856
    public int scoreOfParentheses(String s) {
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(0);

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(')
                st.addFirst(0);
            else {
                int a = st.removeFirst();
                int b = st.removeFirst();

                int val = b + Math.max(2 * a, 1);
                st.addFirst(val);
            }
        }

        return st.removeFirst();
    }

    // 7n
    public int largestRectangleArea_01(int[] heights) {
        int[] nsol = NSOL(heights); // 3n
        int[] nsor = NSOR(heights); // 3n

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) { // n
            maxArea = Math.max(maxArea, heights[i] * (nsor[i] - nsol[i] - 1));
        }

        return maxArea;
    }

    // 84
    // 2n
    public int largestRectangleArea(int[] heights) {
        int n = heights.length, maxArea = 0;
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);

        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && heights[st.getFirst()] >= heights[i]) {
                int h = heights[st.removeFirst()];
                int w = i - st.getFirst() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            st.addFirst(i);
        }

        while (st.getFirst() != -1) {
            int h = heights[st.removeFirst()];
            int w = n - st.getFirst() - 1;
            maxArea = Math.max(maxArea, h * w);
        }

        return maxArea;
    }

    // 85
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int n = matrix.length, m = matrix[0].length;
        int[] height = new int[m];
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            maxArea = Math.max(maxArea, largestRectangleArea(height));
        }

        return maxArea;
    }

    // 32
    public int longestValidParentheses(String s) {
        int n = s.length(), maxLen = 0;
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == ')' && st.getFirst() != -1 && s.charAt(st.getFirst()) == '(') {
                st.removeFirst();
                maxLen = Math.max(maxLen, i - st.getFirst());
            } else
                st.addFirst(i);
        }

        return maxLen;
    }

    // 402
    public String removeKdigits(String num, int k) {
        ArrayList<Character> st = new ArrayList<>();

        for (int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);
            while (st.size() != 0 && st.get(st.size() - 1) > ch && k > 0) {
                k--;
                st.remove(st.size() - 1);
            }
            st.add(ch);
        }

        while (k-- > 0)
            st.remove(st.size() - 1);

        StringBuilder sb = new StringBuilder();
        boolean nonZeroValue = false;
        for (Character ch : st) {
            if (ch == '0' && !nonZeroValue)
                continue;

            nonZeroValue = true;
            sb.append(ch);
        }

        return sb.length() != 0 ? sb.toString() : "0";
    }

    // 316, 1081
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        StringBuilder st = new StringBuilder();
        boolean[] vis = new boolean[26];
        int[] freq = new int[26];

        for (int i = 0; i < n; i++)
            freq[s.charAt(i) - 'a']++;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            freq[ch - 'a']--;
            if (vis[ch - 'a'])
                continue;

            while (st.length() != 0 && st.charAt(st.length() - 1) > ch && freq[st.charAt(st.length() - 1) - 'a'] > 0) {
                vis[st.charAt(st.length() - 1) - 'a'] = false;
                st.deleteCharAt(st.length() - 1);
            }

            vis[ch - 'a'] = true;
            st.append(ch);
        }

        return st.toString();
    }

    // 1249
    public String minRemoveToMakeValid(String s) {
        int n = s.length();
        LinkedList<Integer> st = new LinkedList<>();
        char[] chArr = s.toCharArray();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == ')') {
                if (st.size() != 0)
                    st.removeFirst();
                else
                    chArr[i] = '#';
            } else if (ch == '(')
                st.addFirst(i);
        }

        while (st.size() != 0)
            chArr[st.removeFirst()] = '#';

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (chArr[i] != '#')
                sb.append(s.charAt(i));

        }

        return sb.toString();
    }

    // Home work : String : (((abc)))()(fd)(d(f)())

    // 895
    class FreqStack {
        private class pair implements Comparable<pair> {
            int val = 0;
            int idx = 0;
            int freq = 0;

            pair(int val, int idx, int freq) {
                this.val = val;
                this.idx = idx;
                this.freq = freq;
            }

            public int compareTo(pair o) {
                if (this.freq == o.freq)
                    return o.idx - this.idx;
                else
                    return o.freq - this.freq;

            }
        }

        private PriorityQueue<pair> pq;
        private HashMap<Integer, Integer> freq;
        private int idx = 0;

        public FreqStack() {
            pq = new PriorityQueue<>();
            freq = new HashMap<>();
        }

        public void push(int val) {
            freq.put(val, freq.getOrDefault(val, 0) + 1);
            pq.add(new pair(val, idx++, freq.get(val)));
        }

        public int pop() {
            pair rp = pq.remove();
            freq.put(rp.val, rp.freq - 1);
            if (freq.get(rp.val) == 0)
                freq.remove(rp.val);
            return rp.val;
        }

        public int top() {
            pair rp = pq.peek();
            return rp.val;
        }
    }

    class FreqStack_ {

        private ArrayList<LinkedList<Integer>> freqMap;
        private HashMap<Integer, Integer> map;
        private int maxFreq = 0;

        public FreqStack() {
            freqMap = new ArrayList<>();
            map = new HashMap<>();

            freqMap.add(new LinkedList<>());
        }

        public void push(int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            maxFreq = Math.max(maxFreq, map.get(val));

            if (maxFreq == freqMap.size())
                freqMap.add(new LinkedList<>());

            freqMap.get(map.get(val)).addFirst(val);
        }

        public int pop() {
            int rv = freqMap.get(maxFreq).removeFirst();
            if (freqMap.get(maxFreq).size() == 0) {
                freqMap.remove(maxFreq--);
            }

            map.put(rv, map.get(rv) - 1);
            if (map.get(rv) == 0)
                map.remove(rv);

            return rv;
        }
    }

    // 155
    class MinStack {
        LinkedList<Long> st = new LinkedList<>();
        long minSf = 0;

        public MinStack() {

        }

        public void push(int val) {
            long x = val;
            if (st.size() == 0) {
                st.addFirst(x);
                minSf = x;
                return;
            }

            if (x < minSf) {
                st.addFirst(2 * x - minSf);
                minSf = x;
            } else {
                st.addFirst(x);
            }
        }

        public void pop() {
            if (st.getFirst() < minSf) {
                minSf = 2 * minSf - st.getFirst();
            }

            st.removeFirst();
        }

        public int top() {
            if (st.getFirst() < minSf) {
                return (int) minSf;
            }

            return (int) (long) st.getFirst();
        }

        public int getMin() {
            return (int) minSf;
        }
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        class logPair {
            int id, timeStamp, sleepTime;
            boolean isStart = false;

            logPair(String str) {
                String[] ar = str.split(":");
                this.id = Integer.parseInt(ar[0]);
                this.timeStamp = Integer.parseInt(ar[2]);
                this.isStart = ar[1].equals("start");
                this.sleepTime = 0;
            }
        }

        LinkedList<logPair> st = new LinkedList<>();
        int[] ans = new int[n];

        for (String s : logs) {
            logPair log = new logPair(s);

            if (log.isStart)
                st.addFirst(log);
            else {
                logPair rp = st.removeFirst();
                ans[rp.id] += log.timeStamp - rp.timeStamp + 1 - rp.sleepTime;

                if (st.size() != 0)
                    st.getFirst().sleepTime += log.timeStamp - rp.timeStamp + 1;
            }
        }

        return ans;
    }

    // 853
    public int carFleet(int target, int[] position, int[] speed) {
        int n = speed.length;

        // {position, time}
        double[][] disTimeData = new double[n][2];
        for (int i = 0; i < n; i++) {
            disTimeData[i][0] = position[i] * 1.0;
            disTimeData[i][1] = ((target - position[i]) * 1.0) / speed[i];
        }

        Arrays.sort(disTimeData, (a, b) -> {
            return (int) (a[0] - b[0]);
        });

        int carFleet = 1;
        double prevTime = disTimeData[n - 1][1];
        for (int i = n - 2; i >= 0; i--) {
            if (disTimeData[i][1] > prevTime) {
                prevTime = disTimeData[i][1];
                carFleet++;
            } else {
                // fleet will be same;
            }
        }

        return carFleet;
    }

}