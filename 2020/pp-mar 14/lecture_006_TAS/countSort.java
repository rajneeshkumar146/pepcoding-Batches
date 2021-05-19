public class countSort {

    public static void countSort_01(int[] arr) {
        int maxNum = -(int) 1e9;
        for (int ele : arr)
            maxNum = Math.max(maxNum, ele);

        int range = maxNum - 0 + 1;
        int[] freq = new int[range];
        for (int ele : arr)
            freq[ele]++;

        int idx = 0;
        for (int i = 0; i < range; i++) {
            while (freq[i]-- > 0) {
                arr[idx++] = i;
            }
        }
    }

    public static void countSort_02(int[] arr) {
        int maxNum = -(int) 1e9;
        int minNum = (int) 1e9;

        for (int ele : arr) {
            maxNum = Math.max(maxNum, ele);
            minNum = Math.min(minNum, ele);
        }

        int range = maxNum - minNum + 1;
        int[] freq = new int[range];
        for (int ele : arr)
            freq[ele - minNum]++;

        int idx = 0;
        for (int i = minNum; i <= maxNum; i++) {
            while (freq[i - minNum]-- > 0) {
                arr[idx++] = i;
            }
        }
    }

}