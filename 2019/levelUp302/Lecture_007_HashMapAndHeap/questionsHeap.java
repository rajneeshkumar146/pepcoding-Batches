import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class questionsHeap {

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        for (int i = 0; i < n; i++)
            pq.add(new int[] { matrix[i][0], i, 0 });

        while (k-- > 1) {
            int[] rvtx = pq.remove();
            if (++rvtx[2] < m)
                pq.add(new int[] { matrix[rvtx[1]][rvtx[2]], rvtx[1], rvtx[2] });

        }

        return pq.remove()[0];
    }

    public int[] kthSmallestPrimeFraction(int[] A, int k) {
        int n = A.length;

        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> {
            return (int) (a[0] - b[0]);
        });

        for (int i = 1; i < n; i++) {
            pq.add(new double[] { A[0] / A[i], 0, i });
        }

        while (k-- > 1) {
            double[] rvtx = pq.remove();
            if (rvtx[1] + 1 < rvtx[2]) {

                int x = ((int) rvtx[1]) + 1;
                int y = (int) rvtx[2];
                double val = A[x] / A[y];

                pq.add(new double[] { val, x, y });
            }
        }

        double[] rvtx = pq.remove();
        int x = (int) rvtx[1];
        int y = (int) rvtx[2];
        return new int[] { x, y };
    }

    public int[] kthSmallestPrimeFraction2(int[] A, int k) {
        int n = A.length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return A[a[0]] * A[b[1]] - A[b[0]] * A[a[1]];
        });

        for (int i = 1; i < n; i++) {
            pq.add(new int[] { 0, i });
        }

        while (k-- > 1) {
            int[] rvtx = pq.remove();
            if (++rvtx[0] < rvtx[1])
                pq.add(new int[] { rvtx[0], rvtx[1] });
        }

        int[] rvtx = pq.remove();
        return new int[] { rvtx[0], rvtx[1] };
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0)
            return new int[0];

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums1)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        ArrayList<Integer> list = new ArrayList<>();
        for (int ele : nums2) {
            if (map.containsKey(ele)) {
                list.add(ele);
                map.remove(ele);
            }
        }

        int[] ans = new int[list.size()];
        int i = 0;
        for (int ele : list)
            ans[i++] = ele;

        return ans;

    }

    public class pair implements Comparable<pair> {
        int val;
        int freq;

        pair(int val, int freq) {
            this.val = val;
            this.freq = freq;
        }

        @Override
        public int compareTo(pair o) {
            return this.freq - o.freq;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        PriorityQueue<pair> pq = new PriorityQueue<>();
        for (Integer i : map.keySet()) {
            pq.add(new pair(i, map.get(i)));
            if (pq.size() > k)
                pq.remove();
        }

        int[] ans = new int[pq.size()];
        int i = 0;
        while (pq.size() != 0)
            ans[i++] = pq.remove().val;

        return ans;
    }

    public List<List<String>> groupAnagrams(String[] str) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            String s = str[i];
            int[] freq = new int[26];
            for (int j = 0; j < s.length(); j++)
                freq[s.charAt(j) - 'a']++;

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++)
                if (freq[j] != 0)
                    sb.append((char) (j + 'a') + (freq[j] + ""));

            String RLES = sb.toString();
            map.putIfAbsent(RLES, new ArrayList<>());
            map.get(RLES).add(s);
        }

        List<List<String>> ans = new ArrayList<>();
        for (String code : map.keySet()) {
            ans.add(map.get(code));
        }

        return ans;
    }

}