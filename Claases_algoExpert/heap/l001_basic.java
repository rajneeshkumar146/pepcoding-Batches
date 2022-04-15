import java.util.*;

public class l001_basic {

    public static List<Integer> mergeTwoSortedArrays(List<Integer> arr1, List<Integer> arr2) {
        int n = arr1.size(), m = arr2.size();
        if (n == 0 || m == 0)
            return n == 0 ? arr2 : arr1;

        List<Integer> ans = new ArrayList<>();
        int i = 0, j = 0;

        while (i < n && j < m) {
            if (arr1.get(i) < arr2.get(j))
                ans.add(arr1.get(i++));
            else
                ans.add(arr2.get(j++));
        }

        while (i < n)
            ans.add(arr1.get(i++));
        while (j < m)
            ans.add(arr2.get(j++));

        return ans;
    }

    public static List<Integer> mergeSortedArrays(List<List<Integer>> arr, int l, int r) {
        if (l == r)
            return arr.get(l);
        int mid = (l + r) / 2;
        return mergeTwoSortedArrays(mergeSortedArrays(arr, l, mid), mergeSortedArrays(arr, mid + 1, r));
    }

    // best time complexity -> O(N.logn), N : n.l, l is avg length of array
    public static List<Integer> mergeSortedArrays(List<List<Integer>> arrays) {
        int n = arrays.size();
        if (n == 0)
            return new ArrayList<>();

        return mergeSortedArrays(arrays, 0, n - 1);
    }

    // brute force : O(N.n), N : n.l
    public static List<Integer> mergeSortedArrays_(List<List<Integer>> arrays) {
        int n = arrays.size();
        if (n == 0)
            return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++)
            ans = mergeTwoSortedArrays(ans, arrays.get(i));

        return ans;
    }

    // leftMostColumn

    public static int leftMostCol(int[][] mat) {
        int n = mat.length, m = mat[0].length, res = (int) 1e9;
        for (int r = 0; r < n; r++) {
            int si = 0, ei = m - 1, ans = (int) 1e9;
            while (si <= ei) {
                int mid = (si + ei) / 2;
                if (mat[r][mid] == 1) {
                    ans = mid;
                    if (mid - 1 >= 0 && mat[r][mid - 1] == 0) {
                        break;
                    }
                }
                if (mat[r][mid] == 1)
                    ei = mid - 1;
                else
                    si = mid + 1;
            }

            res = Math.min(res, ans);
        }

        return res;
    }

    // Alice And Bob question
    // Given two sorted arrays A and B, and a target number. Find a pair of elements
    // that A[i] - B[k] == target. Return null if the pair doesn't exist.

    // Input
    // Alice = [2, 5, 7, 8, 14]
    // Bob = [1, 6, 10, 15]
    // target = 2
    // Expected output
    // [8, 6]

    // Input
    // Alice = [2, 5, 7, 8, 14] Bob = [1, 6, 10, 15] target = -10
    // Expected output
    // [5, 15]

    public static List<List<Integer>> aliceBob(int[] a, int[] b, int tar) {
        HashSet<Integer> set = new HashSet<>();
        for (int ele : b)
            set.add(ele);

        List<List<Integer>> ans = new ArrayList<>();
        for (int ele : a) {
            int val = ele - tar;
            if (set.contains(val)) {
                List<Integer> sres = new ArrayList<>();
                sres.add(ele);
                sres.add(val);
                ans.add(sres);
            }
        }

        return ans;
    }

    public int[] sortKSortedArray(int[] array, int k) {
        int n = array.length, idx = 0;
        int[] ans = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int ele : array) {
            pq.add(ele);
            if (pq.size() > k + 1) {
                ans[idx++] = pq.remove();
            }
        }

        while (pq.size() != 0)
            ans[idx++] = pq.remove();

        return ans;
    }

    // laptop rentals
    public int laptopRentals(ArrayList<ArrayList<Integer>> times) {
        int n = times.size(), idx = 0;
        int[] start = new int[n];
        int[] end = new int[n];
        for (var ele : times) {
            start[idx] = ele.get(0);
            end[idx++] = ele.get(1);
        }

        Arrays.sort(start);
        Arrays.sort(end);
        int usedLaptop = 0, sIdx = 0, eIdx = 0;
        while (sIdx < n) {
            if (start[sIdx] >= end[eIdx]) {
                usedLaptop--;
                eIdx++;
            }

            usedLaptop++;
            sIdx++;
        }

        return usedLaptop;
    }

    // median
    static class ContinuousMedianHandler {
        double median = 0;
            PriorityQueue<Integer> minPQ = new PriorityQueue<>();
            PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a,b)->{
                return  b - a;
            });
            
        public void insert(int number) {
          if(maxPQ.size() == 0 || number <= maxPQ.peek()) maxPQ.add(number);
                else minPQ.add(number);
                
                if(maxPQ.size() - minPQ.size() == 2) minPQ.add(maxPQ.remove());
                else if(maxPQ.size() - minPQ.size() == -1) maxPQ.add(minPQ.remove());
        }
    
        public double getMedian() {
                if(maxPQ.size()== minPQ.size()) median = (maxPQ.peek() + minPQ.peek()) / 2.0;
                else median = maxPQ.peek() * 1.0;
          return median;
        }
      }

}
