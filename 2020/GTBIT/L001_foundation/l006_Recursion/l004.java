public class l004 {

    public static int equiSet(int[] arr, int idx, int sum1, int sum2, String set1, String set2) {
        if (idx == arr.length) {
            if (sum1 == sum2) {
                System.out.println(set1 + " = " + set2);
                return 1;
            }

            return 0;
        }

        int count = 0;
        count += equiSet(arr, idx + 1, sum1 + arr[idx], sum2, set1 + arr[idx] + " ", set2);
        count += equiSet(arr, idx + 1, sum1, sum2 + arr[idx], set1, set2 + arr[idx] + " ");

        return count;

    }

    public static void main(String[] args) {
        int[] arr = { 20, 30, 10, 60, 50, 70, 90, 110 };
        System.out.println(equiSet(arr, 0, 0, 0, "", ""));
        // System.out.println(equiSet(arr, 1, arr[0], 0, arr[0] + " ", ""));
    }
}