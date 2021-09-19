import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class targetSum {

    public int[] twoSum(int[] arr, int tar) {
        int si = 0, ei = arr.length - 1;
        while (si < ei) {
            int sum = arr[si] + arr[ei];
            if (sum == tar) {
                return new int[] { si + 1, ei + 1 };
            } else if (sum < tar)
                si++;
            else
                ei--;
        }

        return new int[0];
    }

    public static void _1Sum_unique(int[] arr, int target) {
        Arrays.sort(arr);
        int si = 0, ei = arr.length - 1;
        while (si < ei) {
            int sum = arr[si] + arr[ei];
            if (sum == target)
                System.out.println(arr[si++] + ", " + arr[ei--]);
            else if (sum < target)
                si++;
            else
                ei--;
        }
    }

    public static List<List<Integer>> twoSum(int[] arr, int target, int si, int ei) {
        List<List<Integer>> ans = new ArrayList<>();
        while (si < ei) {
            int sum = arr[si] + arr[ei];
            if (sum == target) {
                ArrayList<Integer> smallAns = new ArrayList<>();
                smallAns.add(arr[si++]);
                smallAns.add(arr[ei--]);
                ans.add(smallAns);
                while (si < ei && arr[si] == arr[si - 1])
                    si++;
                while (si < ei && arr[ei] == arr[ei + 1])
                    ei--;
            } else if (sum < target)
                si++;
            else
                ei--;
        }

        return ans;
    }

    public static void prepareAns(List<List<Integer>> ans, int a, List<List<Integer>> smallAns) {
        for (List<Integer> sa : smallAns) {
            sa.add(0, a); // add(idx, data)
            ans.add(sa);
        }
    }

    public static List<List<Integer>> threeSum(int[] arr, int target, int si, int ei) {
        List<List<Integer>> ans = new ArrayList<>();
        int idx = si;
        while (idx < ei) {
            while (si != idx && idx < ei && arr[idx] == arr[idx - 1])
                idx++;

            List<List<Integer>> smallAns = twoSum(arr, target - arr[idx], idx + 1, ei);
            prepareAns(ans, arr[idx], smallAns);
            idx++;
        }

        return ans;
    }

    public static List<List<Integer>> fourSum(int[] arr, int target, int si, int ei) {
        List<List<Integer>> ans = new ArrayList<>();
        int idx = si;
        while (idx < ei) {
            while (si != idx && idx < ei && arr[idx] == arr[idx - 1])
                idx++;

            List<List<Integer>> smallAns = threeSum(arr, target - arr[idx], idx + 1, ei);
            prepareAns(ans, arr[idx], smallAns);
            idx++;
        }

        return ans;
    }

    public static List<List<Integer>> kSum(int[] arr, int target, int k, int si, int ei) {
        if (k == 2) {
            return twoSum(arr, target, si, ei);
        }
        List<List<Integer>> ans = new ArrayList<>();
        int idx = si;
        while (idx < ei) {
            while (si != idx && idx < ei && arr[idx] == arr[idx - 1])
                idx++;

            List<List<Integer>> smallAns = kSum(arr, target - arr[idx], k - 1, idx + 1, ei);
            prepareAns(ans, arr[idx], smallAns);
            idx++;
        }

        return ans;
    }

    public int findMin(int[] arr) {
        int n = arr.length, si = 0, ei = n - 1;
        // if(arr[si] <= arr[ei]) return si;

        while (si < ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] < arr[ei])
                ei = mid;
            else
                si = mid + 1;
        }

        return si;
    }

    public boolean isPossibleToEat(int[] arr, int hr, int rateOfEating) {
        int h = 0;
        for (int ele : arr) {
            h += (ele - 1) / rateOfEating + 1;
            if (h > hr)
                return false;
        }
        return true;
    }

    public int minEatingSpeed(int[] arr, int h) {
        int si = 1, ei = (int) 1e9;
        while (si < ei) {
            int rateOfEating = (si + ei) / 2;
            if (isPossibleToEat(arr, h, rateOfEating))
                ei = rateOfEating;
            else
                si = rateOfEating + 1;
        }

        return ei;
    }

    public static void main(String[] args) {
        int[] arr = { 10, 10, 10, 10, 20, 20, 20, 20, 20, 30, 30, 30, 40, 40, 50, 50, 50 };
        int target = 60;

        List<List<Integer>> ans = new ArrayList<>();
        twoSum(arr, target, 0, arr.length - 1, ans);
        System.out.println(ans);

    }
}