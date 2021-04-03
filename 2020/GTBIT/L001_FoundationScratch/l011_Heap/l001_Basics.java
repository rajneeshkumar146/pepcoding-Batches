import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class l001_Basics {

    public static void MinPQ() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // by default min PQ.
        for (int i = 10; i >= 0; i--) {
            pq.add(i);
        }

        while (pq.size() != 0) {
            int ele = pq.remove();
            System.out.print(ele + " ");
        }

    }

    public static void MaxPQ() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            // return a - b; // this - other, default behaviour.
            return b - a; // other - this, reverse of default.
        });

        for (int i = 10; i >= 0; i--) {
            pq.add(i);
        }

        while (pq.size() != 0) {
            int ele = pq.remove();
            System.out.print(ele + " ");
        }

    }

    public static void kLargest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int ele : arr) {
            pq.add(ele);
            if (pq.size() > k)
                pq.remove();
        }

        while (pq.size() != 0) {
            System.out.println(pq.remove());
        }
    }

    public static void kSmallest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        for (int ele : arr) {
            pq.add(ele);
            if (pq.size() > k)
                pq.remove();
        }

        while (pq.size() != 0) {
            System.out.println(pq.remove());
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        // {val,freq}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        for (Integer e : map.keySet()) {
            int val = e;
            int freq = map.get(val);

            int[] arr = new int[] { val, freq };
            pq.add(arr);

            if (pq.size() > k)
                pq.remove();
        }

        int[] ans = new int[k];
        int idx = 0;
        while (pq.size() != 0) {
            ans[idx++] = pq.remove();
        }

        return ans;
    }

    public int[] topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        // {val,freq}
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return map.get(a) - map.get(b);
        });

        for (Integer e : map.keySet()) {
            pq.add(e);
            if (pq.size() > k)
                pq.remove();
        }

        int[] ans = new int[k];
        int idx = 0;
        while (pq.size() != 0) {
            ans[idx++] = pq.remove();
        }

        return ans;
    }

    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words)
            map.put(word, map.getOrDefault(word, 0) + 1);

        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (map.get(a) == map.get(b)) {
                return b.compareTo(a);
            }
            return map.get(a) - map.get(b);
        });

        for (String word : map.keySet()) {
            pq.add(word);
            if (pq.size() > k)
                pq.remove();
        }

        int idx = pq.size();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < idx; i++)
            ans.add("");
        while (pq.size() != 0) {
            ans.set(--idx, pq.remove());
        }

        return ans;
    }

    public static void main(String[] args) {
        MinPQ();
        System.out.println();
        MaxPQ();

    }
}