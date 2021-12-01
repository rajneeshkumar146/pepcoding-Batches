public class l002 {
    public static void printArray(int[] arr, int idx) {
        if (idx == arr.length) {
            return;
        }
        System.out.println(arr[idx]);
        printArray(arr, idx + 1);
    }

    public static void printReverseArray(int[] arr, int idx) {
        if (idx == arr.length) {
            return;
        }
        printReverseArray(arr, idx + 1);
        System.out.println(arr[idx]);
    }

    public static boolean findData(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return false;

        if (arr[idx] == data)
            return true;
        return findData(arr, idx + 1, data);
    }

    public static int maximum(int[] arr, int idx) {
        if (idx == arr.length) {
            return Integer.MIN_VALUE;
        }

        int maxEle = maximum(arr, idx + 1);
        return Math.max(maxEle, arr[idx]);
    }

    public static int maximum_02(int[] arr, int lidx) {
        if (lidx == -1) {
            return Integer.MIN_VALUE;
        }

        int maxEle = maximum_02(arr, lidx - 1);
        return Math.max(maxEle, arr[lidx]);
    }

    public static int minimum(int[] arr, int idx) {
        if (idx == arr.length) {
            return Integer.MAX_VALUE;
        }

        int minEle = minimum(arr, idx + 1);
        return Math.min(minEle, arr[idx]);
    }

    public static int firstIdx(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return -1;

        if (arr[idx] == data)
            return idx;
        return firstIdx(arr, idx + 1, data);
    }

    public static int lastIdx(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return -1;

        int isDataPresent = lastIdx(arr, idx + 1, data);
        if (isDataPresent != -1)
            return isDataPresent;

        return arr[idx] == data ? idx : -1;
    }

    public static int[] allIndices(int[] arr, int data, int idx, int count) {
        if (idx == arr.length) {
            return new int[count];
        }

        if (arr[idx] == data)
            count++;

        int[] ans = allIndices(arr, data, idx + 1, count);

        if (arr[idx] == data)
            ans[count - 1] = idx;

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 10, -20, 3, 345, -234, 234, 40, 64 };
        System.out.println(minimum(arr, 0));
        System.out.println(maximum_02(arr, arr.length - 1));
    }
}