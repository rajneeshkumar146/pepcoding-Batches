public class l002_leetcode {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    int minCameraRes = 0;

    // -1 : need a camera
    // 0 i'm a camera
    // 1 i'm covered dosen't need camera.
    public int minCameraCover(TreeNode root) { // leetcode 968

        if (minCameraCover_(root) == -1) // root apne parent ko bol raha hai camera chahiye pr root ka koi parent nahi
            minCameraRes++; // hai so vo khud camera laga lega.

        return minCameraRes;
    }

    public int minCameraCover_(TreeNode node) {
        if (node == null)
            return 1; // null ko koi camera chahiye hota isliye 1 return kiya.

        int leftres = minCameraCover_(node.left);
        int rightres = minCameraCover_(node.right);

        if (leftres == -1 || rightres == -1) { // mere kisi b hi child ko camera chahiye to mujhai camera laganahi
                                               // padega.
            minCameraRes++;
            return 0;
        }

        if (leftres == 0 || rightres == 0) { // agar mere kisi bhi child ke pass camera hua to mai covered hogya.
            return 1;
        }

        return -1; // is node ke child already covered the apne childs ki wajh se isliye ye node
                   // apne parent ko bolega mujhai camera chahiye.

    }

    // diameter_tree==============================================================

    public int oans = 0;// oans = overall answer

    public class zigzagPath {
        int maulc = -1;// maulc = my answer using left child
        int maurc = -1;// maurc = my answer using right child

        zigzagPath(int a, int b) {
            maulc = a;
            maurc = b;
        }
    }

    public int longestZigZagPath(TreeNode root) {
        longestZigZagPath_(root);
        return oans;
    }

    public zigzagPath longestZigZagPath_(TreeNode root) {
        if (root == null)
            return new zigzagPath(-1, -1);

        zigzagPath left = longestZigZagPath_(root.left);
        zigzagPath right = longestZigZagPath_(root.right);

        oans = Math.max(oans, Math.max(left.maurc, right.maulc) + 1);

        return new zigzagPath(left.maurc + 1, right.maulc + 1);
    }

    // leetcode_337================================================

    public class incExc {
        int inc = 0;
        int exc = 0;

        incExc(int inc, int exc) {
            this.inc = inc;
            this.exc = exc;
        }
    }

    int omax = 0;

    public int rob(TreeNode root) {
        rob_(root);
        return omax;
    }

    public incExc rob_(TreeNode root) {
        if (root == null)
            return new incExc(0, 0);

        incExc left = rob_(root.left);
        incExc right = rob_(root.right);

        omax = Math.max(omax, Math.max(left.inc + right.inc, left.exc + right.exc + root.val));
        return new incExc(root.val + left.exc + right.exc, left.inc + right.inc);
    }

    // leetcode_112=========================================
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null && sum - root.val == 0)
            return true;

        boolean res = false;
        res = res || hasPathSum(root.left, sum - root.val);
        res = res || hasPathSum(root.right, sum - root.val);
        return res;
    }

    // leetcode_113=========================================

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();

        pathSum_(root, sum, ans, smallAns);
        return ans;
    }

    public void pathSum_(TreeNode root, int sum, List<List<Integer>> ans, List<Integer> smallAns) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null && sum - root.val == 0) {
            List<Integer> myAns = new ArrayList<>();
            myAns.addAll(smallAns);
            myAns.add(root.val);
            ans.add(myAns);
            return;
        }

        smallAns.add(root.val);
        pathSum_(root.left, sum - root.val, ans, smallAns);
        pathSum_(root.right, sum - root.val, ans, smallAns);
        smallAns.remove(smallaAns.size() - 1);
    }

}