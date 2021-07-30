import java.util.*;

public class question {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 968
    // -1 : need a camera, 0 : it has a camera, 1 : dosen't required any camera.
    int camera = 0;

    public int minCameraCover_(TreeNode root) {
        if (root == null)
            return 1;

        int lc = minCameraCover_(root.left);
        int rc = minCameraCover_(root.right);

        if (lc == -1 || rc == -1) {
            camera++;
            return 0;
        }

        if (lc == 0 || rc == 0) {
            return 1;
        }

        return -1;
    }

    public int minCameraCover(TreeNode root) {
        if (root == null)
            return 0;
        if (minCameraCover_(root) == -1)
            camera++;

        return camera;
    }

    // 1372
    // {forwardSlop,backwardSlop,longestZigZagPath}
    public int[] longestZigZag_(TreeNode root) {
        if (root == null) {
            return new int[] { -1, -1, -1 };
        }

        int[] lr = longestZigZag_(root.left);
        int[] rr = longestZigZag_(root.right);

        int[] ans = new int[3];
        ans[0] = lr[1] + 1;
        ans[1] = rr[0] + 1;
        ans[2] = Math.max(Math.max(ans[0], ans[1]), Math.max(lr[2], rr[2]));

        return ans;
    }

    public int longestZigZag(TreeNode root) {
        return longestZigZag_(root)[2];
    }

    // 337
    // {rob, without rob}
    public int[] rob_(TreeNode root) {
        if (root == null)
            return new int[] { 0, 0 };

        int[] lr = rob_(root.left);
        int[] rr = rob_(root.right);

        int[] ans = new int[2];
        ans[0] = lr[1] + root.val + rr[1];
        ans[1] = Math.max(lr[0], lr[1]) + Math.max(rr[0], rr[1]);

        return ans;
    }

    public int rob(TreeNode root) {
        int[] ans = rob_(root);
        return Math.max(ans[1], ans[0]);
    }

    // 979
    int moves = 0;

    public int distributeCoins_(TreeNode root) {
        if (root == null)
            return 0;
        int leftRequiredExcess = distributeCoins_(root.left);
        int rightRequiredExcess = distributeCoins_(root.right);

        moves += Math.abs(leftRequiredExcess) + Math.abs(rightRequiredExcess);

        return leftRequiredExcess + rightRequiredExcess + (root.val - 1);
    }

    public int distributeCoins(TreeNode root) {
        if (distributeCoins_(root) != 0)
            return -1;

        return moves;
    }

    // 1443
    public static int dfs(ArrayList<Integer>[] tree, int root, List<Boolean> hasApple, boolean[] vis) {
        int time = 0;
        vis[root] = true;
        for (int child : tree[root]) {
            if (!vis[child])
                time += dfs(tree, child, hasApple, vis);
        }

        if (time != 0)
            return time + 2;
        else if (hasApple.get(root))
            return 2;
        else
            return 0;
    }

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        ArrayList<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<>();

        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }

        boolean[] vis = new boolean[n];
        int ans = dfs(tree, 0, hasApple, vis);
        return ans != 0 ? ans - 2 : ans;
    }

    // 687
    // 1339

}