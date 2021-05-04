public class questions {
    // 46
    public void permute(int[] nums, int count, List<List<Integer>> res, List<Integer> ans) {
        if (count == nums.length) {
            List<Integer> base = new ArrayList<>();
            for (int ele : ans)
                base.add(ele);
            res.add(base);
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] >= -10 && nums[i] <= 10) {
                int val = nums[i];

                nums[i] = -11;
                ans.add(val);

                permute(nums, count + 1, res, ans);

                ans.remove(ans.size() - 1);
                nums[i] = val;
            }
        }

    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();

        permute(nums, 0, res, ans);

        return res;
    }

    // 47

    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1#
    public static int floodFill(int sr, int sc, int[][] arr, String ans, int[][] dir, String[] dirS,
            ArrayList<String> res) {

        int n = arr.length, m = arr[0].length;
        if (sr == n - 1 && sc == m - 1) {
            res.add(ans);
            return 1;
        }

        arr[sr][sc] = 0; // block
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && arr[r][c] == 1) {
                count += floodFill(r, c, arr, ans + dirS[d], dir, dirS, res);
            }
        }

        arr[sr][sc] = 1; // free
        return count;
    }

    public static ArrayList<String> findPath(int[][] arr, int n) {
        ArrayList<String> res = new ArrayList<>();
        if (n == 0 || arr[n - 1][n - 1] == 0 || arr[0][0] == 0)
            return res;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        String[] dirS = { "D", "U", "R", "L" };

        int count = floodFill(0, 0, arr, "", dir, dirS, res);

        Collections.sort(res);

        return res;
    }

    // https://www.geeksforgeeks.org/count-number-ways-reach-destination-maze/
    int mod = 1e9 + 7;
    public int floodFill(int sr, int sc, int[][] arr, int[][] dir) {

        int n = arr.length, m = arr[0].length;
        if (sr == n - 1 && sc == m - 1) {
            return 1;
        }

        arr[sr][sc] = 1; // block
        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && arr[r][c] == 0) {
                count = (count % mod + floodFill(r, c, arr, dir) % mod) % mod;
            }
        }

        arr[sr][sc] = 0; // free
        return count;
    }

    public int FindWays(int n, int m, int[][] blocked_cells) {
        int[][] arr = new int[n][m];
        for (int[] cell : blocked_cells) {
            int i = cell[0] - 1;
            int j = cell[1] - 1;

            arr[i][j] = 1;
        }

        if (arr[n - 1][m - 1] == 1 || arr[0][0] == 1)
            return 0;

        int[][] dir = { { 0, 1 }, { 1, 0 } };
        int count = floodFill(0, 0, arr, dir);
        return count;
    }

}