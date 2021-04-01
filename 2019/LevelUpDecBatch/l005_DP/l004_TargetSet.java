import java.util.Arrays;

public class l004_TargetSet {
    public static void print1D(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void print2D(int[][] arr) {
        for (int[] ar : arr) {
            print1D(ar);
        }
    }

    public static int coinChangePermutation_memo(int[] arr, int tar, int[] dp) {
        if (tar == 0) {
            return dp[tar] = 1;
        }

        if (dp[tar] != -1)
            return dp[tar];

        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0) {
                count += coinChangePermutation_memo(arr, tar - ele, dp);
            }
        }

        return dp[tar] = count;
    }

    public static int coinChangePermutation_DP(int[] arr, int Tar, int[] dp) {
        dp[0] = 1;
        for (int tar = 0; tar <= Tar; tar++) {
            for (int ele : arr) {
                if (tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }

        return dp[Tar];
    }

    public static int coinChangeCombination_memo(int[] arr, int tar, int li, int[][] dp) {
        if (tar == 0) {
            return dp[li][tar] = 1;
        }
        int count = 0;
        for (int i = li; i >= 0; i--)
            if (tar - arr[i] >= 0) {
                count += coinChangeCombination_memo(arr, tar - arr[i], i, dp);
            }

        return dp[li][tar] = count;
    }

    public static int coinChangeCombination_2DP(int[] arr, int Tar, int LI, int[][] dp) {

        for (int li = 0; li <= LI; li++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (tar == 0) {
                    dp[li][tar] = 1;
                    continue;
                }

                for (int i = li; i >= 0; i--)
                    if (tar - arr[i] >= 0) {
                        dp[li][tar] += dp[i][tar - arr[i]];
                    }
            }
        }

        return dp[LI][Tar];
    }

    public static int coinChangeCombination_1DP(int[] arr, int Tar, int[] dp) {
        dp[0] = 1;
        for (int ele : arr) {
            for (int tar = ele; tar <= Tar; tar++) {
                if (tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }

        return dp[Tar];

    }

    public static void coinChnage() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;

        int[][] dp = new int[arr.length][tar + 1];
        System.out.println(coinChangeCombination_2DP(arr, tar, arr.length - 1, dp));

        print2D(dp);
    }

    // Leetcode 518,322,377
    // 377
    public int combinationSum4(int[] arr, int Tar) {
        int[] dp = new int[Tar + 1];
        dp[0] = 1;
        for (int tar = 0; tar <= Tar; tar++) {
            for (int ele : arr) {
                if (tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }

        return dp[Tar];
    }

    // 518
    public int change(int Tar, int[] arr) {
        int[] dp = new int[Tar + 1];
        dp[0] = 1;
        for (int ele : arr) {
            for (int tar = ele; tar <= Tar; tar++) {
                if (tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }

        return dp[Tar];

    }

    // 322
    public int coinChange(int[] coins, int amount, int[] dp) {
        if (amount == 0) {
            return 0;
        }

        if (dp[amount] != (int) 1e9)
            return dp[amount];

        int minCoins = (int) 1e8;
        for (int ele : coins) {
            if (amount - ele >= 0) {
                minCoins = Math.min(minCoins, coinChange(coins, amount - ele, dp) + 1);

            }
        }

        return dp[amount] = minCoins;
    }

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, (int) 1e9);

        int ans = coinChange(coins, amount, dp);

        return ans != (int) 1e8 ? ans : -1;
    }

    // https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/
    public static int numberOfSolution(int[] arr, int Tar) {
        int[] dp = new int[Tar + 1];

        for (int tar = 0; tar <= Tar; tar++) {
            for (int ele : arr) {
                if (tar - ele >= 0)
                    dp[tar] += dp[tar - ele];
            }
        }

        return dp[Tar];
    }

    public static int numberOfSolution(int[] arr, int Tar, int aTar, int idx, int[] coff) {
        if (Tar == 0) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + "(" + coff[i] + ")");
                if (i != arr.length - 1)
                    System.out.print(" + ");
            }

            System.out.println(" = " + aTar);

            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (Tar - arr[i] >= 0) {
                coff[i]++;
                count += numberOfSolution(arr, Tar - arr[i], aTar, i, coff);
                coff[i]--;
            }
        }

        return count;
    }

    public static void numberOfSolution() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;
        System.out.println(numberOfSolution(arr, tar, tar, 0, new int[arr.length]));
    }

    // https://www.geeksforgeeks.org/subset-sum-problem-dp-25/

    public static int targetSum(int[] arr, int n, int tar, int[][] dp) {
        if (n == 0 || tar == 0) {
            return dp[n][tar] = (tar == 0) ? 1 : 0;
        }

        if (dp[n][tar] != -1)
            return dp[n][tar];

        boolean res = false;
        if (tar - arr[n - 1] >= 0)
            res = res || (targetSum(arr, n - 1, tar - arr[n - 1], dp) == 1);

        res = res || (targetSum(arr, n - 1, tar, dp) == 1);

        return dp[n][tar] = res ? 1 : 0;
    }

    public static boolean targetSum_DP(int[] arr, int Tar) {
        int N = arr.length;
        boolean[][] dp = new boolean[N + 1][Tar + 1];

        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0);
                    continue;
                }

                if (tar - arr[n - 1] >= 0)
                    dp[n][tar] = dp[n][tar] || dp[n - 1][tar - arr[n - 1]];
                dp[n][tar] = dp[n][tar] || dp[n - 1][tar];
            }
        }

        return dp[N][Tar];
    }

    public static int targetSumTotalWays_DP(int[] arr, int Tar) {
        int N = arr.length;
        int[][] dp = new int[N + 1][Tar + 1];

        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0) ? 1 : 0;
                    continue;
                }

                if (tar - arr[n - 1] >= 0)
                    dp[n][tar] += dp[n - 1][tar - arr[n - 1]];
                dp[n][tar] += dp[n - 1][tar];
            }
        }

        return dp[N][Tar];
    }

    public static void targetSum(int[] arr, int tar) {
        int n = arr.length;
        int[][] dp = new int[n + 1][tar + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        boolean res = targetSum(arr, n, tar, dp) == 1;
        System.out.println(res);
        print2D(dp);
    }

    // 416
    public boolean canPartition(int[] arr) {
        int sum = 0;
        for (int ele : arr)
            sum += ele;

        if (sum % 2 != 0)
            return false;

        int Tar = sum / 2;
        int N = arr.length;
        boolean[][] dp = new boolean[N + 1][Tar + 1];

        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0);
                    continue;
                }

                if (tar - arr[n - 1] >= 0)
                    dp[n][tar] = dp[n][tar] || dp[n - 1][tar - arr[n - 1]];
                dp[n][tar] = dp[n][tar] || dp[n - 1][tar];
            }
        }

        return dp[N][Tar];
    }

    public int findTargetSumWays(int[] arr, int n, int tar, int sum) {
        if (n == 0) {
            return (tar == 0 ? 1 : 0);
        }

        int count = 0;
        if (tar - arr[n - 1] >= 0)
            count += findTargetSumWays(arr, n - 1, tar - (arr[n - 1]), sum); // as a positive number
        if (tar + arr[n - 1] <= sum)
            count += findTargetSumWays(arr, n - 1, tar - (-arr[n - 1]), sum); // as a Negative number

        return count;
    }

    public int findTargetSumWays(int[] nums, int tar) {
        int sum = 0;
        for (int ele : nums)
            sum += ele;

        if (sum < tar || tar < -sum)
            return 0;
        int n = nums.length;
        return findTargetSumWays(nums, n, tar, sum);
    }

    // By using 2D_DP
    public int findTargetSumWays2(int[] arr, int n, int tar, int sum, int[][] dp) {
        if (n == 0) {
            return dp[n][sum] = (tar == sum) ? 1 : 0;
        }

        if (dp[n][sum] != -1)
            return dp[n][sum];

        int count = 0;
        count += findTargetSumWays2(arr, n - 1, tar, sum + arr[n - 1], dp); // as a positive number
        count += findTargetSumWays2(arr, n - 1, tar, sum - arr[n - 1], dp); // as a Negative number

        return dp[n][sum] = count;
    }

    public int findTargetSumWays(int[] nums, int tar) {
        int sum = 0;
        for (int ele : nums)
            sum += ele;

        if (sum < tar || tar < -sum)
            return 0;
        int n = nums.length;
        int[][] dp = new int[n + 1][2 * sum + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = findTargetSumWays2(nums, n, sum + tar, sum, dp);
        return ans;
    }

    // https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
    public static int knapsack(int[] weight, int[] value, int n, int bagWeight, int[][] dp) {
        if (n == 0 || bagWeight == 0) {
            return dp[n][bagWeight] = 0;
        }

        if (dp[n][bagWeight] != 0)
            return dp[n][bagWeight];

        int maxCost = 0;
        if (bagWeight - weight[n - 1] >= 0)
            maxCost = knapsack(weight, value, n - 1, bagWeight - weight[n - 1], dp) + value[n - 1];
        maxCost = Math.max(maxCost, knapsack(weight, value, n - 1, bagWeight, dp));

        return dp[n][bagWeight] = maxCost;
    }

    public static int knapsack_DP(int[] weight, int[] value, int BagWeight) {
        int N = weight.length;
        int[][] dp = new int[N + 1][BagWeight + 1];

        for (int n = 0; n <= N; n++) {
            for (int bagWeight = 0; bagWeight <= BagWeight; bagWeight++) {
                if (n == 0 || bagWeight == 0) {
                    dp[n][bagWeight] = 0;
                    continue;
                }

                if (bagWeight - weight[n - 1] >= 0)
                    dp[n][bagWeight] = dp[n - 1][bagWeight - weight[n - 1]] + value[n - 1];
                dp[n][bagWeight] = Math.max(dp[n][bagWeight], dp[n - 1][bagWeight]);
            }
        }

        return dp[N][BagWeight];
    }

    public static int knapsack_unbounded(int[] weight, int[] value, int BagWeight) {
        // combination
        int n = weight.length;
        int[] dp = new int[BagWeight + 1];
        for (int i = 0; i < n; i++) {
            for (int bagWeight = weight[i]; bagWeight <= BagWeight; bagWeight++) {
                dp[bagWeight] = Math.max(dp[bagWeight], dp[bagWeight - weight[i]] + value[i]);
            }
        }

        return dp[BagWeight];
    }

    // 698
    public boolean canPartitionKSubsets(int[] arr, int k, int idx, int sumSF, int tar, boolean[] vis) {
        if (k == 0)
            return true;
        if (sumSF > tar)
            return false;
        if (sumSF == tar) {
            return canPartitionKSubsets(arr, k - 1, 0, 0, tar, vis);
        }

        boolean res = false;
        for (int i = idx; i < arr.length; i++) {
            if (vis[i])
                continue;
            vis[i] = true;
            res = res || canPartitionKSubsets(arr, k, i + 1, sumSF + arr[i], tar, vis);
            vis[i] = false;
        }

        return res;

    }

    public boolean canPartitionKSubsets(int[] arr, int k) {
        int n = arr.length;
        int sum = 0;
        int maxEle = 0;
        for (int ele : arr) {
            sum += ele;
            maxEle = Math.max(maxEle, ele);
        }

        if (sum % k != 0 || maxEle > sum / k)
            return false;
        boolean[] vis = new boolean[n];

        return canPartitionKSubsets(arr, k, 0, 0, sum / k, vis);
    }

    public static void main(String[] args) {
        // coinChnage();
        // numberOfSolution();

        targetSum(new int[] { 2, 3, 5, 7 }, 10);
    }

}