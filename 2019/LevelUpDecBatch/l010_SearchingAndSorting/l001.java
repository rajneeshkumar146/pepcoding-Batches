public class l001 {

    public static int binarySearch(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2; // si + (ei - si) / 2;
            if (arr[mid] == data)
                return mid;
            else if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return -1;
    }

    public int firstIndex(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data) {
                if (mid - 1 >= 0 && arr[mid - 1] == data)
                    ei = mid - 1;
                else
                    return mid;
            } else if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return -1;
    }

    public int lastIndex(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data) {
                if (mid + 1 < n && arr[mid + 1] == data)
                    si = mid + 1;
                else
                    return mid;
            } else if (arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return -1;
    }

    // 34
    public int[] searchRange(int[] arr, int data) {
        return new int[] { firstIndex(arr, data), lastIndex(arr, data) };
    }

    public static int searchInsert(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] <= data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        int insertPos = si;
        int lastIndex = si - 1;

        return (lastIndex >= 0 && arr[lastIndex] == data) ? lastIndex : insertPos;
    }

    public static long InversionAcrossArray(long[] arr, int l, int r, int mid, long[] sortedArray) {
        int lsi = l, lei = mid;
        int rsi = mid + 1, rei = r;

        int k = 0;
        long count = 0;
        while (lsi <= lei && rsi <= rei) {
            if (arr[lsi] > arr[rsi]) {
                count += (lei - lsi + 1);
                sortedArray[k++] = arr[rsi++];
            } else
                sortedArray[k++] = arr[lsi++];
        }

        while (lsi <= lei)
            sortedArray[k++] = arr[lsi++];
        while (rsi <= rei)
            sortedArray[k++] = arr[rsi++];

        // for (k = 0; k < sortedArray.length; k++)
        // arr[k + l] = sortedArray[k];

        k = 0;
        for (int i = l; i <= r; i++)
            arr[i] = sortedArray[k++];

        return count;
    }

    public static long inversionCount(long[] arr, int si, int ei, long[] sortedArray) {
        if (si >= ei)
            return 0;

        int mid = (si + ei) / 2;
        long ICL = inversionCount(arr, si, mid, sortedArray); // IC : Inversion Count, L = left , R = Right
        long ICR = inversionCount(arr, mid + 1, ei, sortedArray);

        return (ICL + ICR + InversionAcrossArray(arr, si, ei, mid, sortedArray));
    }

    public static long inversionCount(long arr[], long N) {
        if (N == 0)
            return 0;

        long[] sortedArray = new long[N];
        return inversionCount(arr, 0, (int) N - 1,sortedArray);
    }

    public static void main(String[] args) {

    }
}