public class mergeSort {

    public static int[] mergeTwoSortedArrays(int[] A, int[] B) {
        if (A.length == 0 || B.length == 0)
            return A.length == 0 ? B : A;

        int i = 0, j = 0, k = 0;
        int n = A.length, m = B.length, len = n + m;
        int[] ans = new int[len];

        while (i < n && j < m) {
            if (A[i] < B[j])
                ans[k++] = A[i++];
            else
                ans[k++] = B[j++];
        }

        while (j < m) {
            ans[k++] = B[j++];
        }

        while (i < n) {
            ans[k++] = A[i++];
        }

        return ans;
    }

    public static int[] mergesort(int[] arr, int si, int ei) {
        if (si == ei) {
            return new int[] { arr[si] };
        }

        int mid = (si + ei) / 2;
        int[] left = mergesort(arr, si, mid);
        int[] right = mergesort(arr, mid + 1, ei);

        return mergeTwoSortedArrays(left, right);
    }

    public static void main(String[] args) {
        int[] arr = { -12, 2, 7, 4, 34, 23, 0, 1, -1, -50, 16, 23, 7, 4, 2, 3 };
        arr = mergesort(arr, 0, arr.length - 1);

        for (int ele : arr)
            System.out.print(ele + " ");
    }
}