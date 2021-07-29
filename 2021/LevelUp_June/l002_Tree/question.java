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

}