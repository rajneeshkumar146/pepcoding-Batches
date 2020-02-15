public class l001 {

    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node() {

        }

        Node(int data) {
            this.data = data;
        }

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    static int idx = 0;

    public static Node createTree(int[] arr) {
        if (idx == arr.length || arr[idx] == -1) {
            idx++;
            return null;
        }

        Node node = new Node(arr[idx]);
        idx++;

        node.left = createTree(arr);
        node.right = createTree(arr);
        return node;
    }

    public static void display(Node node) {
        if (node == null)
            return;
        String ans = "";
        ans += node.left != null ? node.left.data : ".";
        ans += " -> " + node.data + " <- ";
        ans += node.right != null ? node.right.data : ".";
        System.out.println(ans);

        display(node.left);
        display(node.right);
    }

    public static boolean findData(Node node, int data) {
        if (node == null)
            return false;
        if (node.data == data)
            return true;

        // boolean res = false;
        // res = res || findData(node.left, data);
        // res = res || findData(node.right, data);

        boolean left = findData(node.left, data);
        if (left == true)
            return left;

        boolean right = findData(node.right, data);
        if (right == true)
            return right;

        return false;
    }

    public static int size(Node node) {
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    public static int height(Node node) {
        return node == null ? -1 : Math.max(height(node.left), height(node.right)) + 1;
    }

    public static ArrayList<Node> nodeToRootPath(Node node, int data) {
        if (node == null)
            return null;

        if (node.data == data) {
            ArrayList<Node> base = new ArrayList<>();
            base.add(node);
            return base;
        }

        ArrayList<Node> left = nodeToRootPath(node.left, data);
        if (left != null) {
            left.add(node);
            return left;
        }

        ArrayList<Node> right = nodeToRootPath(node.right, data);
        if (right != null) {
            right.add(node);
            return right;
        }

        return null;
    }

    public static Node LCA_01(Node node, int data1, int data2) {
        ArrayList<Node> list1 = nodeToRootPath(node, data1);
        ArrayList<Node> list2 = nodeToRootPath(node, data2);

        if (list1 == null || list2 == null)
            return null;

        int i = list1.size() - 1;
        int j = list2.size() - 1;
        Node prev = null;

        while (i >= 0 && j >= 0) {
            if (list1.get(i).data != list2.get(j).data)
                break;

            prev = list1.get(i);
            i--;
            j--;
        }

        return prev;

    }

    public static int diameter_01(Node node) {
        if (node == null)
            return 0;

        int ld = diameter_01(node.left);
        int rd = diameter_01(node.right);

        int lh = height(node.left);
        int rh = height(node.right);

        return Math.max(Math.max(ld, rd), lh + rh + 2);
    }

    public static int[] diameter_02(Node node) {
        if (node == null)
            return new int[] { 0, -1 };

        int[] ld = diameter_02(node.left);
        int[] rd = diameter_02(node.right);

        int[] ans = new int[2];

        ans[0] = Math.max(Math.max(ld[0], rd[0]), ld[1] + rd[1] + 2);
        ans[1] = Math.max(ld[1], rd[1]) + 1;

        return ans;
    }

    static int maxDia = 0;

    public static int diameter_03(Node node) {
        if (node == null)
            return -1;

        int lh = diameter_03(node.left);
        int rh = diameter_03(node.right);

        maxDia = Math.max(maxDia, lh + rh + 2);
        return Math.max(lh, rh) + 1;
    }

    static int maxSum = (int) -1e7;

    public static int leafToLeafMaxSum(Node node) {
        if (node == null)
            return (int) -1e7;

        if (node.left == null && node.right == null)
            return node.data;

        int leftsum = leafToLeafMaxSum(node.left);
        int rightsum = leafToLeafMaxSum(node.right);
        if (node.left != null && node.right != null)
            maxSum = Math.max(maxSum, leftsum + rightsum + node.data);

        return (node.left == null ? rightsum : node.right == null ? leftsum : math.max(leftsum, rightsum)) + node.data;
    }

    public static int nodeToNodeMaxSum(Node node) {
        if (node == null)
            return 0;

        int leftsum = nodeToNodeMaxSum(node.left);
        int rightsum = nodeToNodeMaxSum(node.right);
        
        int sideMax = Math.max(leftsum, rightsum) + node.data;
        maxSum = Math.max(Math.max(maxSum, sideMax), Math.max(leftsum + rightsum + node.data, node.data));

        return math.max(sideMax, node.data);
    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 40, 60, -1, -1, 70, -1, -1, 50, 80, -1, -1, -1, 30, 90, -1, 110, 150, -1, -1, -1, 100,
                120, -1, -1, -1 };

        Node node = createTree(arr);
        display(node);
    }

}