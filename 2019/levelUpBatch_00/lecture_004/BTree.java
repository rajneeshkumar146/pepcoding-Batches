import java.util.ArrayList;
import java.util.LinkedList;

public class BTree {

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, -1, -1, 40, -1, -1, 50, 60, 80, -1, -1, -1, 70, 90, -1, 100, -1, -1, -1 };
        Node root = create(arr);
        display(root);
        solve(root);

    }

    public static void solve(Node root) {
        // LCA(root);
        // basic(root);

        // linearTree(root);
        // display(root);

        DLL(root);
        while (head_ != null) {
            System.out.print(head_.data + " -> ");
            head_ = head_.right;
        }
        System.out.println();
        while (prev_ != null) {
            System.out.print(prev_.data + " -> ");
            prev_ = prev_.left;
        }
    }

    public static void basic(Node root) {
        // System.out.println(diameter(root));
        // System.out.println(diameter2(root)[0]);
        // levelOder_01(root);
        levelOder_02(root);
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

        int[] myAns = new int[2];
        dia = Math.max(dia, left + right + 1);
        return Math.max(left, right) + 1;

    }

    public static void levelOder_01(Node root) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        int level = 0;

        while (que.size() > 0) {
            System.out.print("Level: " + level + " -> ");
            int size = que.size();
            while (size-- > 0) {
                Node rnode = que.removeFirst();
                System.out.print(rnode.data + " ");
                if (rnode.left != null)
                    que.addLast(rnode.left);
                if (rnode.right != null)
                    que.addLast(rnode.right);
            }
            level++;
            System.out.println();
        }

    }

    public static void levelOder_02(Node root) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        que.addLast(null);

        int level = 0;

        System.out.print("Level: " + level + " -> ");
        while (que.size() != 1) {

            Node rnode = que.removeFirst();
            System.out.print(rnode.data + " ");
            if (rnode.left != null)
                que.addLast(rnode.left);
            if (rnode.right != null)
                que.addLast(rnode.right);
            if (que.getFirst() == null) {
                que.addLast(null);
                que.removeFirst();

                level++;
                System.out.println();
                System.out.print("Level: " + level + " -> ");
            }
        }

    }

    // ==================================

    public static void set2() {

    }

    public static void kaway(Node node, int k, Node rnode) { // rnode = return node
        if (node == null)
            return;
        if (node == rnode)
            return;
        if (k == 0) {
            System.out.print(node.data + " ");
            return;
        }

        kaway(node.left, k - 1, rnode);
        kaway(node.right, k - 1, rnode);

    }

    public static void kfar_01(Node node, int k, int data) {
        ArrayList<Node> path = rootToNodePath(node, data);

        Node rnode = null;
        for (int i = 0; i < path.size(); i++) {
            kaway(path.get(i), k - i, rnode);
            rnode = path.get(i);
        }
    }

    public static int kfar_02(Node node, int k, int data) {
        if (node == null)
            return -1;
        if (node.data == data) {
            kaway(node, k, null);
            return 1;
        }

        int ld = kfar_02(node.left, k, data);
        if (ld != -1) {
            if (ld == k) {
                System.out.print(node.data + " ");
            } else {
                kaway(node, k - ld, node.left);
            }
            return ld + 1;
        }

        int rd = kfar_02(node.right, k, data);
        if (rd != -1) {
            if (rd == k) {
                System.out.print(node.data + " ");
            } else {
                kaway(node, k - rd, node.right);
            }
            return rd + 1;
        }

        return -1;

    }

    public static class allPair {
        int height = 0;
        int size = 0;
        boolean find = false;

        int ceil = (int) 1e8;
        int floor = (int) -1e8;

        Node pred = null;
        Node succ = null;
        Node prev = null;
    }

    public static void allSol(Node node, int level, int data, allPair sol) {
        if (node == null)
            return;

        sol.size++;
        sol.height = Math.max(sol.height, level);
        sol.find = sol.find || node.data == data;

        if (node.data > data && node.data < sol.ceil)
            sol.ceil = node.data;

        if (node.data < data && node.data > sol.floor)
            sol.floor = node.data;

        if (node.data == data && sol.pred == null) {
            sol.pred = sol.prev;
        }

        if (sol.prev != null && sol.succ == null && sol.prev.data == data) {
            sol.succ = node;
        }

        sol.prev = node;

        allSol(node.left, level + 1, data, sol);
        allSol(node.right, level + 1, data, sol);

    }

    static int prev = (int) -1e8;

    public static boolean isBST(Node node) {
        if (node == null)
            return true;

        if (!isBST(node.left))
            return false;

        if (prev > node.data) {
            return false;
        }
        prev = node.data;

        if (!isBST(node.right))
            return false;

        return true;
    }

    public static class BSTpair {
        boolean isBst = true;
        int count = 0;
        int lBSTsize = 0;
        Node lBstRoot = null;

        int min = (int) 1e8;
        int max = (int) -1e8;

    }

    public static BSTpair BSTSol_(Node node) {
        if (node == null) {
            return new BSTpair();
        }

        BSTpair lp = BSTSol_(node.left);
        BSTpair rp = BSTSol_(node.right);

        BSTpair myPair = new BSTpair();
        myPair.count = lp.count + rp.count;

        if (lp.isBst && rp.isBst && lp.max < node.data && node.data <= rp.min) {
            myPair.count++;
            myPair.lBSTsize = myPair.count;
            myPair.lBstRoot = node;
        } else {
            myPair.isBst = false;
            if (lp.lBSTsize > rp.lBSTsize) {
                myPair.lBSTsize = lp.lBSTsize;
                myPair.lBstRoot = lp.lBstRoot;
            } else {
                myPair.lBSTsize = rp.lBSTsize;
                myPair.lBstRoot = rp.lBstRoot;
            }
        }

        myPair.min = Math.min(Math.min(lp.min, rp.min), node.data);
        myPair.max = Math.max(Math.max(lp.max, rp.max), node.data);

        return myPair;
    }

    public static Node linearTree(Node node) {
        if (node == null)
            return null;
        if (node.left == null && node.right == null)
            return node;

        Node leftTail = linearTree(node.left);
        Node rightTail = linearTree(node.right);

        if (leftTail == null)
            node.left = node.right;
        else {
            leftTail.left = node.right;
        }
        node.right = null;
        return rightTail != null ? rightTail : leftTail;
    }

    static Node prev_ = null;
    static Node head_ = null;

    public static void DLL(Node node) {
        if (node == null)
            return;

        DLL(node.left);
        if (prev_ == null)
            head_ = node;
        else {
            node.left = prev_;
            prev_.right = node;
        }
        prev_ = node;
        DLL(node.right);
    }

}