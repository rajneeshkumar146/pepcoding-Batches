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

    public static int permutationWithInfi_subSeq(int[] arr, int tar, String ans) {

    }

    public static int combinationWithInfi_subSeq(int[] arr, int tar, int idx, String ans) {

    }

    public static int combinationWithSingle_subSeq(int[] arr, int tar, int idx, String ans) {

    }

    public static void main(String[] args) {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;

        // System.out.println(permutationWithInfi(arr, tar, ""));
        // System.out.println(coFmbinationWithInfi(arr, tar,0, ""));
        // System.out.println(combinationWithSingle(arr, tar, 0, ""));

        

    }
}