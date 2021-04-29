import java.util.PriorityQueue;

public class questions {
    // 215
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> que = new PriorityQueue<>(); // By Default Min.
        for (int ele : nums) {
            que.add(ele);
            if (que.size() > k)
                que.remove();
        }

        return que.peek();
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void downheapify(int[] arr, int pi, int li) {
        int maxIdx = pi, lci = 2 * pi + 1, rci = 2 * pi + 2;
        if (lci <= li && arr[lci] > arr[maxIdx])
            maxIdx = lci;
        if (rci <= li && arr[rci] > arr[maxIdx])
            maxIdx = rci;
        if (maxIdx != pi) {
            swap(arr, pi, maxIdx);
            downheapify(arr, maxIdx, li);
        }
    }

    public int findKthLargest_Btr(int[] nums, int k) {
        int li = nums.length - 1;
        for (int i = li; i >= 0; i--)
            downheapify(nums, i, li);

        while (k-- > 1) {
            swap(nums, 0, li--);
            downheapify(nums, 0, li);
        }

        return nums[0];
    }

    public int findKthSmallest(int[] nums, int k) {
        PriorityQueue<Integer> que = new PriorityQueue<>((a, b) -> { // max PQ.
            return b - a;
        }); // By Default Min.

        for (int ele : nums) {
            que.add(ele);
            if (que.size() > k)
                que.remove();
        }

        return que.peek();
    }

    // 703
    class KthLargest {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int K = 0;

        public KthLargest(int k, int[] nums) {
            this.K = k;
            for (int ele : nums) {
                this.pq.add(ele);
                if (this.pq.size() > this.K)
                    this.pq.remove();

            }
        }

        public int add(int val) {
            this.pq.add(val);
            if (this.pq.size() > this.K)
                this.pq.remove();

            return this.pq.peek();
        }
    }

}