public class l001 {

    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }
    }

    // Traversal Of Tree

    public static void preOrderTraversal(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        ans.add(root.data);
        preOrderTraversal(root.left, ans);
        preOrderTraversal(root.right, ans);
    }

    public static void inOrderTraversal(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        inOrderTraversal(root.left, ans);
        ans.add(root.data);
        inOrderTraversal(root.right, ans);
    }

    public static void postOrderTraversal(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        postOrderTraversal(root.left, ans);
        postOrderTraversal(root.right, ans);
        ans.add(root.data);
    }

    public static void traversal(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        preOrderTraversal(root, ans);
        // inOrderTraversal(root, ans);
        // postOrderTraversal(root, ans);
    }

    // Basics functions

    public static int size(Node root) {
        if (root == null)
            return 0;
        int left = size(root.left);
        int right = size(root.right);

        return left + right + 1;
    }

    // By Default
    public static int height(Node root) {
        if (root == null)
            return -1;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static int heightInTermsOfNode(Node root) {
        if (root == null)
            return 0;

        int leftHeight = heightInTermsOfNode(root.left);
        int rightHeight = heightInTermsOfNode(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static int maximum(Node root) {
        if (root == null)
            return -(int) 1e8;

        int leftMax = maximum(root.left);
        int rightMax = maximum(root.right);

        return Math.max(Math.max(leftMax, rightMax), root.data);
    }

    public static int minimum(Node root) {
        if (root == null)
            return (int) 1e8;

        int leftMin = minimum(root.left);
        int rightMin = minimum(root.right);

        return Math.min(Math.min(leftMax, rightMax), root.data);
    }

    public static int sum(Node root) {
        if (root == null)
            return 0;

        int leftSum = sum(root.left);
        int rightSum = sum(root.right);

        return leftSum + root.data + rightSum;
    }

    public static boolean findData(Node root, int data) {
        if (root == null)
            return false;

        return root.data == data || findData(root.left, data) || findData(root.right, data);
    }

    public static boolean findData2(Node root, int data) {
        if (root == null)
            return false;

        if (root.data == data)
            return true;

        boolean left = findData(root.left, data);
        if(left) return true;
        
        boolean right = findData(root.right, data);
        if(right) return true;
        
        return false;
    }

}
