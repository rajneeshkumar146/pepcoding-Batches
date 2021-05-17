public class l001 {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort01(int[] arr) {
        int p = -1, itr = 0;
        while (itr < arr.length) {
            if (arr[itr] == 0)
                swap(arr, ++p, itr);
            itr++;
        }
    }

    public static void sort012(int[] arr) {
        int n = arr.length, p1 = -1, p2 = n - 1, itr = 0;
        while (itr <= p2) {
            if (arr[itr] == 0)
                swap(arr, itr++, ++p1);
            else if (arr[itr] == 2)
                swap(arr, itr, p2--);
            else
                itr++;
        }
    }

    public static int[] mergeTwoSortedArrays(int[] arr1, int[] arr2) {
        int i = 0, j = 0, k = 0;
        int n = arr1.length, m = arr2.length;
        int[] ans = new int[n + m];

        while (i < n && j < m) {
            if (arr1[i] < arr2[j])
                ans[k++] = arr1[i++];
            else
                ans[k++] = arr2[j++];
        }

        while (i < n)
            ans[k++] = arr1[i++];
        while (j < m)
            ans[k++] = arr2[j++];

        return ans;
    }

    public static int[] mergeTwoSortedArrays2(int[] arr1, int[] arr2) {
        int i = 0, j = 0, k = 0;
        int n = arr1.length, m = arr2.length;
        int len = n + m;
        int[] ans = new int[len];

        long val1 = 0, val2 = 0;
        while (k < len) {
            val1 = i < n ? arr1[i] : (long) 1e18;
            val2 = j < m ? arr2[j] : (long) 1e18;
            if (val1 < val2) {
                ans[k++] = (int) val1;
                i++;
            } else {
                ans[k++] = (int) val2;
                j++;
            }
        }

        return ans;
    }

    public static int[] mergeTwoSortedArrays3(int[] arr1, int[] arr2) {
        int i = 0, j = 0, k = 0;
        int n = arr1.length, m = arr2.length;
        int len = n + m;
        int[] ans = new int[n + m];

        while (k < len) {
            if (i == n)
                ans[k++] = arr2[j++];
            else if (j == m)
                ans[k++] = arr1[i++];
            else if (arr1[i] < arr2[j])
                ans[k++] = arr1[i++];
            else
                ans[k++] = arr2[j++];
        }

        return ans;
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        for (int li = n - 1; li > 0; li--) {
            for (int i = 1; i <= li; i++)
                if (arr[i - 1] > arr[i])
                    swap(arr, i, i - 1);
        }
    }

    public static void bubbleSort_Opti(int[] arr) {
        int n = arr.length;

        for (int li = n - 1; li > 0; li--) {
            boolean isSwapDone = false;
            for (int i = 1; i <= li; i++) {
                if (arr[i - 1] > arr[i]) {
                    swap(arr, i, i - 1);
                    isSwapDone = true;
                }
            }

            if (!isSwapDone)
                break;
        }
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i])
                    minIdx = j;
            }
            swap(arr, i, minIdx);
        }
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j])
                    swap(arr, j - 1, j);
                else
                    break;
            }
        }
    }

    public static int PartitionAnArray(int[] arr, int data) {
        int n = arr.length, p = -1, itr = 0;
        while (itr < n) {
            if (arr[itr] <= data)
                swap(arr, itr, ++p);
            itr++;
        }

        return p;
    }

    public static int PartitionAnArray2(int[] arr, int data) {
        int n = arr.length, p = n - 1, itr = 0;
        while (itr <= p) {
            if (arr[itr] <= data)
                itr++;
            else
                swap(arr, p--, itr);
        }

        return p;
    }

    public static void PartitionOverPivot(int[] arr, int pivotIdx) {
        int n = arr.length;
        swap(arr, pivotIdx, n - 1);

        int itr = 0, p = -1, li = n - 1;
        while (itr < li) {
            if (arr[itr] <= arr[li])
                swap(arr, itr, ++p);

            itr++;
        }

        swap(arr, ++p, li);
    }

    public static void display(int[] arr) {
        for (int ele : arr)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 1, 1, 1, 1, 11, 3, 4, 68, 123 };
        int data = 7;
        System.out.println(PartitionAnArray2(arr, data));
        display(arr);
    }
}