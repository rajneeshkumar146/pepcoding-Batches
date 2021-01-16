public class test {
    public static int permutation(char[] arr, int count, boolean[] vis) {
        if (count == arr.length) {
            return 1;
        }

        int prev = -1, cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (prev != -1 && arr[i] == arr[prev])
                continue;
            if (!vis[i]) {
                vis[i] = true;

                cnt += permutation(arr, count + 1, vis);

                vis[i] = false;
                prev = i;
            }
        }

        return cnt;
    }

    public static void permutation(char[] arr, int count, boolean[] vis, String ans) {
        if (count == arr.length) {
            System.out.println(ans);
            return;
        }

        int prev = -1;
        for (int i = 0; i < arr.length; i++) {
            if (prev != -1 && arr[i] == arr[prev])
                continue;
            if (!vis[i]) {
                vis[i] = true;

                permutation(arr, count + 1, vis, ans + arr[i]);

                vis[i] = false;
                prev = i;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        char[] arr = scn.nextLine().toCharArray();
        Arrays.sort(arr);
        // String str = new String(arr);

        boolean[] vis = new boolean[arr.length];
        System.out.println(permutation(arr, 0, vis));
        permutation(arr, 0, vis, "");
    }
}