import java.util.ArrayList;

public class BTree {

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, -1, -1, 40, -1, -1, 50, 60, 80, -1, -1, -1, 70, 90, -1, 100, -1, -1, -1 };
        Node root = create(arr);
        // display(root);
        solve(root);

    }

    public static void solve(Node root) {
        // LCA(root);
        basic(root);
    }

    public static void basic(Node root) {
        System.out.println(diameter(root));
        System.out.println(diameter2(root)[0]);

    }

    public static void LCA(Node root) {
        ArrayList<Node> list1 = rootToNodePath(root, 100);
        ArrayList<Node> list2 = rootToNodePath(root, 60);

        // for (Node n : ans) {
        // System.out.print(n.data + " ");
        // }

        int prev = -1;
        for (int i = list1.size() - 1, j = list2.size() - 1; i >= 0 && j >= 0; i--, j--) {
            if (list1.get(i).data != list2.get(j).data)
                break;

            prev = list1.get(i).data;
        }

        System.out.println(prev);

    }

    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    static int idx = 0;

    public static Node create(int[] arr) {
        if (idx == arr.length || arr[idx] == -1) {
            idx++;
            return null;
        }

        Node nnode = new Node(arr[idx], null, null);
        idx++;
        nnode.left = create(arr);
        nnode.right = create(arr);
        return nnode;
    }

    public static void display(Node node) {
        if (node == null)
            return;
        String str = "";

        str += node.left == null ? "." : node.left.data;
        str += " -> " + node.data + " <- ";
        str += node.right == null ? "." : node.right.data;
        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    // Basic.===========================

    public static int height(Node node) {
        if (node == null)
            return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public static int size(Node node) {
        if (node == null)
            return 0;
        return size(node.left) + size(node.right) + 1;
    }

    public static boolean find(Node node, int data) {
        if (node == null)
            return false;
        if (node.data == data)
            return true;

        boolean res = false;
        res = res || find(node.left, data);
        res = res || find(node.right, data);
        return res;
    }

    public static ArrayList<Node> rootToNodePath(Node node, int data) {
        if (node == null)
            return null;
        if (node.data == data) {
            ArrayList<Node> base = new ArrayList<>();
            base.add(node);
            return base;
        }

        ArrayList<Node> left = rootToNodePath(node.left, data);
        if (left != null) {
            left.add(node);
            return left;
        }
        ArrayList<Node> right = rootToNodePath(node.right, data);
        if (right != null) {
            right.add(node);
            return right;
        }
        return null;
    }

    public static int diameter(Node node) {
        if (node == null)
            return 0;

        int dl = diameter(node.left);
        int dr = diameter(node.right);

        int lh = height(node.left);
        int rh = height(node.right);

        return Math.max(Math.max(dl, dr), lh + rh + 1);

    }

    public static int[] diameter2(Node node) {
        if (node == null) {
            return new int[2];
        }

        int[] left = diameter2(node.left);
        int[] right = diameter2(node.right);

        int[] myAns = new int[2];
        myAns[0] = Math.max(Math.max(left[0], right[0]), left[1] + right[1] + 1);
        myAns[1] = Math.max(left[1], right[1]) + 1;
        return myAns;

    }

    static int dia = 0;

    public static int diameter3(Node node) {
        if (node == null) {
            return 0;
        }

        int left = diameter3(node.left);
        int right = diameter3(node.right);

        int myAns = new int[2];
        dia = Math.max(dia, left + right + 1);
        return Math.max(left, right) + 1;

    }

}