import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class targetSum {

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

    public static void twoSum(int[] arr, int target, int si, int ei, List<List<Integer>> ans) {
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
    }

    public static void main(String[] args) {
        int[] arr = { 10, 10, 10, 10, 20, 20, 20, 20, 20, 30, 30, 30, 40, 40, 50, 50, 50 };
        int target = 60;

        List<List<Integer>> ans = new ArrayList<>();
        twoSum(arr, target, 0, arr.length - 1, ans);
        System.out.println(ans);

    }
}