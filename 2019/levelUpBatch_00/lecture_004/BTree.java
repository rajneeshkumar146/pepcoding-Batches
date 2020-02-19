import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Stack;

public class BTree {

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, -1, -1, 40, -1, -1, 50, 60, 80, -1, -1, -1, 70, 90, -1, 100, -1, -1, -1 };
        Node root = create(arr);
        // display(root);
        // solve(root);

        // System.out.println(width(root, true));
        // System.out.println(width(root, false));

        BSTQuest();

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

        // idx = 0;
        // int[] arr = { 50, 25, 20, 30, 75, 65 };
        // root = BSTFromPreOder(arr, (int) -1e8, 0, (int) 1e8);
        // display(root);
        // System.out.println(HeightOfBSTFromPreOder(arr, (int) -1e8, 0, (int) 1e8));
        set3(root);
    }

    public static void basic(Node root) {
        // System.out.println(diameter(root));
        // System.out.println(diameter2(root)[0]);
        // levelOder_01(root);
        levelOder_02(root);
    }

    public static void BSTQuest() {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        Node root = createBST(arr, 0, arr.length - 1);

        // addData(root, 32);
        // addData(root, 25);

        // removeData(root, 25);

        // if (find_BST(root, 20) && find_BST(root, 70)) {
        // System.out.println(LCA_InBST(root, 20, 70));
        // }

        display(root);
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
        if (idx < arr.node->datalength)
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

    // ================================================

    public static Node createBST(int[] arr, int si, int ei) {
        if (si > ei) {
            return null;
        }

        int mid = (si + ei) / 2; // (si+ei)>>>1; si + (ei-si)/2;
        Node node = new Node(arr[mid], null, null);

        node.left = createBST(arr, si, mid - 1);
        node.right = createBST(arr, mid + 1, ei);

        return node;
    }

    public static Node addData(Node root, int data) {
        if (root == null) {
            Node node = new Node(data, null, null);
            return node;
        }

        if (data < root.data) {
            root.left = addData(root.left, data);
        } else {
            root.right = addData(root.right, data);
        }

        return root;
    }

    public static Node findMaxInBST_forRemovedata(Node node) {
        Node prev = null;
        Node rnode = node;
        while (rnode.right != null) {
            prev = rnode;
            rnode = rnode.right;
        }
        return prev;

    }

    public static Node removeData(Node node, int data) {
        if (data < node.data) {
            node.left = removeData(node.left, data);
        } else if (data > node.data) {
            node.right = removeData(node.right, data);
        } else {
            if (node.left == null || node.right == null) {
                return node.left == null ? node.right : node.left;
            }

            Node rdata_prev = findMaxInBST_forRemovedata(node.left);
            node.data = rdata_prev.right.data;

            rdata_prev.right = removeData(rdata_prev.right, rdata_prev.right.data);
        }

        return node;
    }

    public static Node LCA_InBST(Node node, int a, int b) {
        if (node == null)
            return null;

        if (node.data < a) {
            return LCA_InBST(node.right, a, b);
        } else if (b < node.data) {
            return LCA_InBST(node.left, a, b);
        } else {
            // return node;
            if (find_BST(node, a) && find_BST(node, b)) {
                return node;
            }
            return null;
        }
    }

    public static int width(Node node, boolean isLeftWidth) {
        if (node == null)
            return -1;

        int left = width(node.left, isLeftWidth) + (isLeftWidth ? 1 : -1);
        int right = width(node.right, isLeftWidth) + (isLeftWidth ? -1 : 1);

        return Math.max(left, right);
    }

    public static int LCseq(Node node, int potentialValue, int currLen) {
        if (node == null)
            return -1;

        if (node.data == potentialValue) {
            currLen++;
        } else {
            currLen = 1;
        }

        int max1 = LCseq(node.left, node.data + 1, currLen);
        int max2 = LCseq(node.right, node.data + 1, currLen);

        return Math.max(currLen, Math.max(max1, max2));
    }

    // set3========================================

    public static void set3(Node node) {
        // HashMapTest();
        // morrisIn(node);
        // System.out.println();
        // morrisPre(node);
        traverse(node);
    }

    public static void HashMapTest() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("India", 1000);
        map.put("China", 40);
        map.put("Nepal", 800);
        map.put("Pakistan", -1000);
        map.put("USA", 0);

        System.out.println(map);

        System.out.println(map.get("UE"));
        System.out.println(map.containsKey("India"));
        map.put("India", map.getOrDefault("India", 10) + 1);
        System.out.println(map);
        ArrayList<String> keys = new ArrayList<>(map.keySet());
        System.out.println(keys);
    }

    static HashMap<Integer, Integer> map = new HashMap<>();
    static int maxFreq = 0;

    public static int[] mostFrequentSum(Node node) {
        if (node == null)
            return new int[0];

        mostFrequentSum_(node);
        int size = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key) == maxFreq)
                size++;
        }

        int[] ans = new int[size];
        int i = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key) == maxFreq) {
                ans[i] = key;
                i++;
            }
        }

        return ans;

    }

    public static int mostFrequentSum_(Node node) {
        if (node == null)
            return 0;

        int sum = mostFrequentSum_(node.left) + mostFrequentSum_(node.right) + node.data;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        maxFreq = Math.max(maxFreq, map.get(sum));
        return sum;
    }

    static Node succ = null;

    public static boolean BTSucc(Node node, int data) {
        if (node == null)
            return false;

        if (node.data == data) {
            if (node.right != null) {
                Node curr = node.right;
                while (curr.left != null) {
                    curr = curr.left;
                }
                succ = curr;
            }
            return true;
        }

        succ = node;
        boolean res = false;
        res = res || BTSucc(node.left, data);
        res = res || BTSucc(node.right, data);

        return res;
    }

    public static Node rightMost(Node node, Node curr) {
        while (node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }

    public static void morrisIn(Node node) {
        Node curr = node;
        while (curr != null) {
            Node nextLeft = curr.left;
            if (nextLeft == null) {
                System.out.print(curr.data + " "); // print.
                curr = curr.right;
                continue;
            }

            Node rightMost = rightMost(nextLeft, curr);

            if (rightMost.right == null) {
                rightMost.right = curr; // thread.
                curr = curr.left;
            } else {
                rightMost.right = null; // break thread.
                System.out.print(curr.data + " "); // print.
                curr = curr.right;
            }

        }
    }

    public static void morrisPre(Node node) {
        Node curr = node;
        while (curr != null) {
            Node nextLeft = curr.left;
            if (nextLeft == null) {
                System.out.print(curr.data + " "); // print.
                curr = curr.right;
                continue;
            }

            Node rightMost = rightMost(nextLeft, curr);

            if (rightMost.right == null) {
                rightMost.right = curr; // thread.
                System.out.print(curr.data + " "); // print.
                curr = curr.left;
            } else {
                rightMost.right = null; // break thread.
                curr = curr.right;
            }

        }
    }

    public static class Tpair {
        Node node = null;
        boolean sd = false;
        boolean ld = false;
        boolean rd = false;

        Tpair(Node node) {
            this.node = node;
        }
    }

    public static void traverse(Node root) {
        Stack<Tpair> st = new Stack<>();
        st.push(new Tpair(root));
        while (st.size() != 0) {
            Tpair gnode = st.peek();
            if (!gnode.ld) {
                gnode.ld = true;
                if (gnode.node.left != null) {
                    st.push(new Tpair(gnode.node.left));
                }

            } else if (!gnode.rd) {
                gnode.rd = true;
                if (gnode.node.right != null) {
                    st.push(new Tpair(gnode.node.right));
                }
            } else if (!gnode.sd) {
                System.out.print(gnode.node.data + " ");
                gnode.sd = true;
            } else {
                st.pop();
            }
        }

    }

}