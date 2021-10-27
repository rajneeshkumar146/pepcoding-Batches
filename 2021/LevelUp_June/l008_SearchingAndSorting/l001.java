public class l001 {

    public static long totalInversionCount(long[] arr, long[] sortedArray, long si, long mid, long ei) {
        int i = (int) si, j = (int) mid + 1, k = (int) si;
        long count = 0;

        while (i <= mid && j <= ei) {
            if (arr[i] <= arr[j])
                sortedArray[k++] = arr[i++];
            else {
                sortedArray[k++] = arr[j++];
                count += mid - i + 1;
            }
        }

        while (i <= mid || j <= ei)
            sortedArray[k++] = arr[i <= mid ? i++ : j++];

        while (si <= ei)
            arr[(int) si] = sortedArray[(int) si++];

        return count;
    }

    public static long inversionCount(long[] arr, long[] sortedArray, long si, long ei) {
        if (si >= ei)
            return 0;

        long mid = (si + ei) / 2;
        long count = 0;

        count += inversionCount(arr, sortedArray, si, mid);
        count += inversionCount(arr, sortedArray, mid + 1, ei);

        count += totalInversionCount(arr, sortedArray, si, mid, ei);
        return count;
    }

    public static long inversionCount(long arr[], long N) {
        if (N == 0)
            return 0;

        long[] sortedArray = new long[(int) N];
        return inversionCount(arr, sortedArray, 0, N - 1);
    }

    //658


}