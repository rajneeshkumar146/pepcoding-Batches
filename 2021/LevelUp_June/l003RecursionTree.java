public class l003RecursionTree {

    public static int permutationInfiCoins(int[] arr, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0)
                count += permutationInfiCoins(arr, tar - ele, ans + ele);
        }
        return count;
    }

    public static int combinationInfiCoins(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += combinationInfiCoins(arr, tar - arr[i], i, ans + arr[i]);
        }
        return count;
    }

    public static int combinationSingleCoins(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += combinationSingleCoins(arr, tar - arr[i], i + 1, ans + arr[i]);
        }
        return count;
    }

    public static int permutationSingleCoins() {
        return 0;
    }

    public static void combinationPermutation() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;
        String ans = "";

        // System.out.println(permutationInfiCoins(arr, tar, ans));
        // System.out.println(combinationInfiCoins(arr, tar, 0, ans));
        System.out.println(combinationSingleCoins(arr, tar, 0, ans));
    }

    public static void main(String[] args) {
        combinationPermutation();
    }

}