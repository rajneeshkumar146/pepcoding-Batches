public class l002RecursionTrees {
    public static int permutationWithInfi(int[] arr, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0) {
                count += permutationWithInfi(arr, tar - ele, ans + ele);
            }
        }
        return count;
    }

    public static int combinationWithInfi(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += combinationWithInfi(arr, tar - arr[i], i, ans + arr[i]);
        }
        return count;
    }

    public static int combinationWithSingle(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += combinationWithSingle(arr, tar - arr[i], i + 1, ans + arr[i]);
        }

        return count;
    }

    public static int permutationWithSingleCoin(int[] arr, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0 && tar - arr[i] >= 0) {
                int val = arr[i];

                arr[i] = -arr[i];
                count += permutationWithSingleCoin(arr, tar - val, ans + val);
                arr[i] = -arr[i];
            }
        }

        return count;
    }

    public static int permutationWithSingleCoin(int[] arr, boolean[] vis, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!vis[i] && tar - arr[i] >= 0) {
                vis[i] = true;
                count += permutationWithSingleCoin(arr, vis, tar - arr[i], ans + arr[i]);
                vis[i] = false;
            }
        }

        return count;
    }

    // ====================================================================================================

    public static int permutationWithInfi_subSeq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += permutationWithInfi_subSeq(arr, tar - arr[idx], 0, ans + arr[idx]);
        count += permutationWithInfi_subSeq(arr, tar, idx + 1, ans);

        return count;
    }

    public static int combinationWithInfi_subSeq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += combinationWithInfi_subSeq(arr, tar - arr[idx], idx, ans + arr[idx]);
        count += combinationWithInfi_subSeq(arr, tar, idx + 1, ans);

        return count;
    }

    public static int combinationWithSingle_subSeq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += combinationWithSingle_subSeq(arr, tar - arr[idx], idx + 1, ans + arr[idx]);
        count += combinationWithSingle_subSeq(arr, tar, idx + 1, ans);

        return count;
    }

    public static int permutationWithSingleCoin_subSeq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (arr[idx] > 0 && tar - arr[idx] >= 0) {
            int val = arr[idx];
            arr[idx] = -arr[idx];
            count += permutationWithSingleCoin_subSeq(arr, tar - val, 0, ans + val);
            arr[idx] = -arr[idx];
        }
        count += permutationWithSingleCoin_subSeq(arr, tar, idx + 1, ans);
        return count;
    }

    // 1D_Queen_Set=================================================================================

    // tboxes = total Bpxes, tqn = total queen, qpsf = queen placed so far, bn =
    // box_no,
    public static int queenCombination(int tboxe, int tqn, int qpsf, int bn, String ans) {
        if (qpsf == tqn) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = bn; i < tboxe; i++) {
            count += queenCombination(tboxe, tqn, qpsf + 1, i + 1, ans + "b" + i + "q" + qpsf + " ");
        }
        return count;
    }

    public static int queenPermutation(boolean[] tboxe, int tqn, int qpsf, int bn, String ans) {
        if (qpsf == tqn) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = bn; i < tboxe.length; i++) {
            if (!tboxe[i]) {
                tboxe[i] = true;
                count += queenPermutation(tboxe, tqn, qpsf + 1, 0, ans + "b" + i + "q" + qpsf + " ");
                tboxe[i] = false;
            }
        }
        return count;
    }

    public static void coinChange() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;

        // System.out.println(permutationWithInfi(arr, tar, ""));
        // System.out.println(coFmbinationWithInfi(arr, tar,0, ""));
        // System.out.println(combinationWithSingle(arr, tar, 0, ""));
        // System.out.println(permutationWithSingleCoin(arr, tar, ""));

        // System.out.println(permutationWithInfi_subSeq(arr, tar, 0, ""));
        // System.out.println(combinationWithInfi_subSeq(arr, tar, 0, ""));
        // System.out.println(combinationWithSingle_subSeq(arr, tar, 0, ""));
        // System.out.println(permutationWithSingleCoin_subSeq(arr, tar, 0, ""));
    }

    public static void queenSet() {
        boolean[] boxes = new boolean[6];
        // System.out.println(queenCombination(6, 4, 0, 0, ""));
        System.out.println(queenPermutation(boxes, 4, 0, 0, ""));
    }

    public static void main(String[] args) {
        queenSet();

    }
}