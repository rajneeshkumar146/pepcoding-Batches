import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;

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

        // DLL(root);
        // while (head_ != null) {
        // System.out.print(head_.data + " -> ");
        // head_ = head_.right;
        // }
        // System.out.println();
        // while (prev_ != null) {
        // System.out.print(prev_.data + " -> ");
        // prev_ = prev_.left;
        // }

        idx = 0;
        int[] arr = { 50, 25, 20, 30, 75, 65 };
        // root = BSTFromPreOder(arr, (int) -1e8, 0, (int) 1e8);
        // display(root);
        System.out.println(HeightOfBSTFromPreOder(arr, (int) -1e8, 0, (int) 1e8));
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

        prev_ = node;
        DLL(node.right);
    }

    public static boolean sumPathI(Node node, int tar, String ans) {
        if (node == null)
            return false;
        if (node.left == null && node.right == null && tar - node.data == 0) {
            System.out.println(ans + " " + node.data);
            return true;
        }

        boolean res = false;
        res = res || sumPathI(node.left, tar - node.data, ans + node.data + " ");
        res = res || sumPathI(node.right, tar - node.data, ans + node.data + " ");
        return res;
    }

    public static ArrayList<ArrayList<Integer>> pathSum_II_01(Node node, int tar) {
        if (node == null)
            return null;
        if (node.left == null && node.right == null && tar - node.data == 0) {
            ArrayList<ArrayList<Integer>> base = new ArrayList<>();
            ArrayList<Integer> small = new ArrayList<>();
            small.add(node.data);
            base.add(small);
            return base;
        }

        ArrayList<ArrayList<Integer>> myans = new ArrayList<>();
        ArrayList<ArrayList<Integer>> left = pathSum_II_01(node.left, tar - node.data);
        if (left != null) {
            for (ArrayList<Integer> small : left) {
                small.add(0, node.data);
                myans.add(small);
            }
        }

        ArrayList<ArrayList<Integer>> right = pathSum_II_01(node.right, tar - node.data);
        if (right != null) {
            for (ArrayList<Integer> small : right) {
                small.add(0, node.data);
                myans.add(small);
            }
        }

        return myans;
    }

    public static void pathSum_II_02(Node node, int tar, ArrayList<Integer> small, ArrayList<ArrayList<Integer>> ans) {
        if (node == null)
            return;
        if (node.left == null && node.right == null && tar - node.data == 0) {
            ArrayList<Integer> baseAns = new ArrayList<Integer>(small);
            baseAns.add(node.data);
            ans.add(baseAns);
            return;
        }

        small.add(node.data);
        pathSum_II_02(node.left, tar - node.data, small, ans);
        pathSum_II_02(node.right, tar - node.data, small, ans);
        small.remove(small.size() - 1);
    }

    public static int pathSum_III(Node node, int prefixSum, int tar, HashMap<Integer, Integer> map) {
        if (node == null)
            return 0;

        prefixSum += node.data;
        int count = map.getOrDefault(prefixSum - tar, 0);

        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);

        count += pathSum_III(node.left, prefixSum, tar, map);
        count += pathSum_III(node.right, prefixSum, tar, map);

        if (map.get(prefixSum) == 1) {
            map.remove(prefixSum);
        } else {
            map.put(prefixSum, map.get(prefixSum) - 1);
        }
        return count;
    }

    static int Max_leaftoleaf = (int) -1e8;

    public static int leafToLeafSum(Node node) {
        if (node == null)
            return 0;

        int leftNodeToLeaf = leafToLeafSum(node.left);
        int rightNodeToLeaf = leafToLeafSum(node.right);
        if (node.left != null && node.right != null) {
            Max_leaftoleaf = Math.max(Max_leaftoleaf, leftNodeToLeaf + rightNodeToLeaf + node.data);
            return Math.max(leftNodeToLeaf, leftNodeToLeaf) + node.data;
        }
        return (node.left == null ? rightNodeToLeaf : leftNodeToLeaf) + node.data;
    }

    static int Max_nodeToNode = (int) -1e8;

    public static int nodeToNodeSum(Node node) {
        if (node == null)
            return (int) -1e8;

        int leftNodeToNode = nodeToNodeSum(node.left);
        int rightNodeToNode = nodeToNodeSum(node.right);

        int max_ = Math.max(leftNodeToNode, rightNodeToNode) + node.data;

        Max_nodeToNode = Math.max(Math.max(leftNodeToNode + rightNodeToNode + node.data, Max_nodeToNode),
                Math.max(max_, node.data));
        return Math.max(max_, node.data);
    }

    static Node LCA_node = null;

    public static boolean LCA_02(Node node, int data1, int data2) {
        if (node == null)
            return false;

        boolean selfDone = node.data == data1 || node.data == data2;

        boolean left = LCA_02(node.left, data1, data2);
        if (LCA_node != null)
            return true;

        boolean right = LCA_02(node.left, data1, data2);
        if (LCA_node != null)
            return true;

        if ((left && right) || (left && selfDone) || (right && selfDone)) {
            LCA_node = node;
            return true;
        }

        return left || right || selfDone;

    }

    public static boolean find_BST(Node node, int data) {
        if (node == null)
            return false;

        if (node.data == data)
            return true;

        if (data < node.data)
            return find_BST(node.left, data);
        else
            return find_BST(node.right, data);
    }

    static Node x, y, z;

    public static boolean recoverBST(Node node) {
        if (node == null)
            return false;

        boolean res = false;
        res = res || recoverBST(node.left);
        if (z != null && node.data < z.data) {
            y = node;
            if (x == null)
                x = z;
            else
                return true;
        }

        z = node;
        res = res || recoverBST(node.right);
        return res;
    }

    public static Node BSTFromPreOder(int[] arr, int lb, int ele, int ub) {
        if (ele < lb || ele > ub || idx == arr.length)
            return null;

        Node node = new Node(arr[idx], null, null);
        idx++;
        if (idx < arr.length) {
            node.left = BSTFromPreOder(arr, lb, arr[idx], node.data);
        }
        if (idx < arr.length) {
            node.right = BSTFromPreOder(arr, node.data, arr[idx], ub);
        }
        return node;

    }

    public static int HeightOfBSTFromPreOder(int[] arr, int lb, int ele, int ub) {
        if (ele < lb || ele > ub || idx == arr.length)
            return -1;

        int ele_ = arr[idx];
        idx++;

        int lh = -1, rh = -1;
        if (idx < arr.length)
            lh = HeightOfBSTFromPreOder(arr, lb, arr[idx], ele_);
        if (idx < arr.length)
            rh = HeightOfBSTFromPreOder(arr, ele_, arr[idx], ub);

        return Math.max(lh, rh) + 1;

    }

    static int leftLevel = -1;

    public static void leftView(Node node, int level) {
        if (node == null)
            return;

        if (leftLevel < level) {
            System.out.println(node.data + " ");
            leftLevel = level;
        }
        leftView(node.left, level + 1);
        leftView(node.right, level + 1);
    }

    public static class verticalPair {
        Node node;
        int level = 0;

        verticalPair(Node node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public static void verticalOder(Node node) {
        LinkedList<verticalPair> que = new LinkedList<>();
        que.addLast(new verticalPair(node, 0));

    }

}