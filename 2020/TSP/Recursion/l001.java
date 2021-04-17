public class l001 {

    public static void ppppppp(int a, int b) {
        return;
    }

    public static void pppppp(int a, int b) {
        System.out.println(a);
        ppppppp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void ppppp(int a, int b) {
        System.out.println(a);
        pppppp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void pppp(int a, int b) {
        System.out.println(a);
        ppppp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void ppp(int a, int b) {
        System.out.println(a);
        pppp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void pp(int a, int b) {
        System.out.println(a);
        ppp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void p(int a, int b) {
        System.out.println(a);
        pp(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void printIncreasing(int a, int b) {
        if (a > b)
            return;

        System.out.println(a);
        printIncreasing(a + 1, b);
        System.out.println("hi" + a);
    }

    public static void display(int[] arr, int idx) {
        if (idx == arr.length)
            return;

        System.out.println(arr[idx]);
        display(arr, idx + 1);
    }

    public static void countNoTimesDataOcc(int[] arr, int idx, int data, int count) {
        if (idx == arr.length) {
            System.out.println(count);
            return;
        }

        if (arr[idx] == data)
            count++;

        countNoTimesDataOcc(arr, idx + 1, data, count);

        if (arr[idx] == data)
            System.out.println(idx);
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

    public static int countAllDataOcc(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return 0;

        int recAns = countAllDataOcc(arr, idx + 1, data);
        return arr[idx] == data ? recAns + 1 : recAns;
    }

    public static void main(String[] args) {

        int[] arr = { 0, 2, 2, 2, 4, 5, 7, 2, 5, 6, 2 };
        int ans = countAllDataOcc(arr, 0, 2);
        System.out.println(ans);

    }
}