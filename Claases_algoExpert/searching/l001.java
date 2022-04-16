import java.util.*;

public class l001 {
    public static int binarySearch(int[] arr, int tar) {
        int n = arr.length, si = 0, ei = n - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == tar)
                return mid;
            else if (arr[mid] < tar)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return -1;
    }

    public static int[] findThreeLargestNumbers(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int ele : arr) {
            pq.add(ele);
            if (pq.size() > 3)
                pq.remove();
        }

        int[] ans = new int[3];
        int idx = 0;
        while (pq.size() != 0)
            ans[idx++] = pq.remove();
        return ans;
    }

    public static int[] searchInSortedMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length, r = 0, c = m - 1;
        while (r < n && c >= 0) {
            if (matrix[r][c] == target)
                return new int[] { r, c };
            else if (matrix[r][c] > target)
                c--;
            else
                r++;
        }
        return new int[] { -1, -1 };
    }

    public static int firstOccurrence(int[] arr, int tar) {
        int n = arr.length, si = 0, ei = n - 1;
        while (si < ei) {
            int mid = si + (ei - si) / 2;
            if (arr[mid] >= tar)
                ei = mid;
            else
                si = mid + 1;
        }

        return ei;
    }

    public static int lastOccurrence(int[] arr, int tar) {
        int n = arr.length, si = 0, ei = n - 1;
        while (si < ei) {
            int mid = (int) Math.ceil(si + (ei - si) / 2.0);
            if (arr[mid] <= tar)
                si = mid;
            else
                ei = mid - 1;
        }

        return si;
    }

    // log(n)
    public static int[] searchForRange(int[] arr, int tar) {
        int fo = firstOccurrence(arr, tar); // log(n)
        int lo = lastOccurrence(arr, tar);// log(n)
        return arr[fo] != tar ? new int[] { -1, -1 } : new int[] { fo, lo };
    }

    public static int shiftedBinarySearch(int[] arr, int tar) {
        int n = arr.length, si = 0, ei = n - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;

            if (arr[mid] == tar)
                return mid;
            else if (arr[si] <= arr[mid]) {
                if (arr[si] <= tar && tar < arr[mid])
                    ei = mid - 1;
                else
                    si = mid + 1;
            } else {
                if (arr[mid] < tar && tar <= arr[ei])
                    si = mid + 1;
                else
                    ei = mid - 1;
            }

        }
        return -1;
    }

    // 81
    public boolean search(int[] nums, int target) {

    }

    // 724
    public int pivotIndex(int[] nums) {

    }
}