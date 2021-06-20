import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class l004_questions {
    // 39
    public void combinationSum(int[] arr, int idx, int target, List<List<Integer>> ans, List<Integer> smallAns) {
        if (target == 0 || idx == arr.length) {
            if (target == 0) {
                List<Integer> base = new ArrayList<>(smallAns); // deep Copy
                ans.add(base);
            }

            return;
        }

        if (target - arr[idx] >= 0) {
            smallAns.add(arr[idx]);
            combinationSum(arr, idx, target - arr[idx], ans, smallAns);
            smallAns.remove(smallAns.size() - 1);
        }

        combinationSum(arr, idx + 1, target, ans, smallAns);
    }

    // 40
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        combinationSum(candidates, 0, target, ans, smallAns);
        return ans;
    }

    public void combinationSum2(int[] arr, int idx, int target, List<List<Integer>> ans, List<Integer> smallAns) {
        if (target == 0) {
            List<Integer> base = new ArrayList<>(smallAns); // deep Copy
            ans.add(base);

            return;
        }

        int prev = -1;
        for (int i = idx; i < arr.length; i++) {
            if (prev != arr[i] && target - arr[i] >= 0) {
                smallAns.add(arr[i]);
                combinationSum2(arr, i + 1, target - arr[i], ans, smallAns);
                smallAns.remove(smallAns.size() - 1);
                prev = arr[i];
            }
        }

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        Arrays.sort(candidates); // sort(candidates.begin(),candidates.end());
        combinationSum2(candidates, 0, target, ans, smallAns);
        return ans;
    }

    // 322
    public int coinChange_(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        int minAns = (int) 1e9;
        for (int ele : coins) {
            if (amount - ele >= 0) {
                int recAns = coinChange_(coins, amount - ele);
                if (recAns != (int) 1e9 && recAns + 1 <= minAns) {
                    minAns = recAns + 1;
                }
            }
        }

        return minAns;
    }

    public int coinChange(int[] coins, int amount) {
        int ans = coinChange_(coins, amount);
        return ans != (int) 1e9 ? ans : -1;
    }

    //

    public void getFactors(int n, int sp, List<List<Integer>> ans, List<Integer> smallAns) {

        if (n <= 1) {
            if (smallAns.size() > 1) {
                List<Integer> base = new ArrayList<>(smallAns);
                ans.add(base);
            }
            return;
        }

        for (int i = sp; i <= n; i++) {
            if (n % i == 0) {
                smallAns.add(i);
                getFactors(n / i, i, ans, smallAns);
                smallAns.remove(smallAns.size() - 1);
            }
        }

    }

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        getFactors(n, 2, ans, smallAns);
        return ans;
    }

    
}