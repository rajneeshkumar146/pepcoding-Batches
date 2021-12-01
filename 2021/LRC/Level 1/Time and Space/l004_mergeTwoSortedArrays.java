public class l004_mergeTwoSortedArrays {

    public static int[] mergeTwoSortedArrays(int[] a, int[] b) {
        int n = a.length, m = b.length;
        int[] ans = new int[n + m];

        int i = 0, j = 0, k = 0;
        while (k < ans.length) {
            if (i < n && j < m) {
                if (a[i] < b[j])
                    ans[k++] = a[i++];
                else
                    ans[k++] = b[j++];
            } else if (i < n)
                ans[k++] = a[i++];
            else if (j < m)
                ans[k++] = b[j++];
        }

        return ans;
    }

}