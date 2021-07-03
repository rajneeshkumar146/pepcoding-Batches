import java.util.LinkedList;

public class l003ConstructionSet {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode constructFromInOrder(int[] inOrder, int si, int ei) {
        if (si > ei)
            return null;

        int mid = (si + ei) / 2;
        TreeNode root = new TreeNode(inOrder[mid]);

        root.left = constructFromInOrder(inOrder, si, mid - 1);
        root.right = constructFromInOrder(inOrder, mid + 1, ei);

        return root;
    }

    static int idx = 0;

    public static TreeNode bstFromPreorder(int[] preorder, int lr, int rr) {
        if (idx == preorder.length || preorder[idx] < lr || preorder[idx] > rr)
            return null;

        TreeNode node = new TreeNode(preorder[idx++]);

        node.left = bstFromPreorder(preorder, lr, node.val);
        node.right = bstFromPreorder(preorder, node.val, rr);

        return node;
    }

    public static TreeNode bstFromPreorder(int[] preorder) {
        idx = 0;
        return bstFromPreorder(preorder, -(int) 1e9, (int) 1e9);
    }

    public static TreeNode bstFromPostOrder(int[] postOrder, int lr, int rr) {
        if (idx == -1 || postOrder[idx] < lr || postOrder[idx] > rr)
            return null;

        TreeNode node = new TreeNode(postOrder[idx--]);

        node.right = bstFromPreorder(postOrder, node.val, rr);
        node.left = bstFromPreorder(postOrder, lr, node.val);

        return node;
    }

    public static TreeNode bstFromPostOrder(int[] postOrder) {
        idx = postOrder.length - 1;
        return bstFromPostOrder(postOrder, -(int) 1e9, (int) 1e9);
    }

    public static class bstLPair {
        TreeNode par = null;
        int lr = 0;
        int rr = 0;

        bstLPair(TreeNode par, int lr, int rr) {
            this.par = par;
            this.lr = lr;
            this.rr = rr;
        }
    }

    public static TreeNode constructBSTFromLevelOrder(int[] LevelOrder) {
        if (LevelOrder.length == 0)
            return null;
        LinkedList<bstLPair> que = new LinkedList<>();
        TreeNode root = new TreeNode(LevelOrder[0]);
        int idx = 1;

        que.addLast(new bstLPair(root, -(int) 1e9, root.val));
        que.addLast(new bstLPair(root, root.val, (int) 1e9));

        while (idx < LevelOrder.length) {
            bstLPair rp = que.removeFirst();
            int lr = rp.lr, rr = rp.rr;
            if (LevelOrder[idx] < lr || LevelOrder[idx] > rr)
                continue;

            TreeNode node = new TreeNode(LevelOrder[idx++]);
            if (node.val < rp.par.val)
                rp.par.left = node;
            else
                rp.par.right = node;

            que.addLast(new bstLPair(node, rp.lr, node.val));
            que.addLast(new bstLPair(node, node.val, rp.rr));
        }

        return root;
    }

    public static TreeNode preOrIn(int[] pre, int psi, int pei, int[] in, int isi, int iei) {
        if (isi > iei)
            return null;
        int idx = isi;
        while (in[idx] != pre[psi])
            idx++;

        int tel = idx - isi;
        TreeNode root = new TreeNode(pre[psi]);

        root.left = preOrIn(pre, psi + 1, psi + tel, in, isi, idx - 1);
        root.right = preOrIn(pre, psi + tel + 1, pei, in, idx + 1, iei);

        return root;
    }

    public static TreeNode postOrIn(int[] post, int psi, int pei, int[] in, int isi, int iei) {
        if (isi > iei)
            return null;
        int idx = isi;
        while (in[idx] != post[pei])
            idx++;

        int tel = idx - isi;
        TreeNode root = new TreeNode(post[pei]);

        root.left = postOrIn(post, psi, psi + tel - 1, in, isi, idx - 1);
        root.right = postOrIn(post, psi + tel, pei - 1, in, idx + 1, iei);

        return root;
    }

    public TreeNode constructFromPrePost(int[] pre, int psi, int pei, int[] post, int ppsi, int ppei) {
        if (psi > pei)
            return null;

        TreeNode root = new TreeNode(pre[psi]);
        if (psi == pei)
            return root;

        int idx = ppsi;
        while (post[idx] != pre[psi + 1])
            idx++;

        int tnel = idx - ppsi + 1;

        root.left = constructFromPrePost(pre, psi + 1, psi + tnel, post, ppsi, idx);
        root.right = constructFromPrePost(pre, psi + tnel + 1, pei, post, idx + 1, ppei - 1);

        return root;
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {

        int n = pre.length;
        return constructFromPrePost(pre, 0, n - 1, post, 0, n - 1);
    }

    // HM_:https://practice.geeksforgeeks.org/problems/construct-tree-from-inorder-and-levelorder/1

    // https://www.geeksforgeeks.org/check-if-given-preorder-inorder-and-postorder-traversals-are-of-same-tree/

    public static void serialize_PreOrder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("# ");
            return;
        }

        sb.append(root.val + " ");
        serialize_PreOrder(root.left, sb);
        serialize_PreOrder(root.right, sb);
    }

    public static String serialize_PreOrder(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize_PreOrder(root, sb);
        return sb.toString();
    }

    public static TreeNode deserialize_preorder(String[] arr, int[] idx) {
        if (idx[0] > arr.length || arr[idx[0]].equals("#")) {
            idx[0]++;
            return null;
        }

        int i = idx[0]++;
        int val = Integer.parseInt(arr[i]);
        TreeNode root = new TreeNode(val);
        root.left = deserialize_preorder(arr, idx);
        root.right = deserialize_preorder(arr, idx);

        return root;
    }

    public static TreeNode deserialize_preorder(String str) {
        String[] arr = str.split(" ");
        int[] idx = new int[1];

        return deserialize_preorder(arr, idx);
    }

    public static String serialize_levelOrder(TreeNode root) {
        if (root == null)
            return "";
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        StringBuilder sb = new StringBuilder();

        while (que.size() != 0) {
            TreeNode rnode = que.removeFirst();
            if (rnode == null) {
                sb.append("# ");
                continue;
            } else
                sb.append(rnode.val + " ");

            que.addLast(rnode.left);
            que.addLast(rnode.right);
        }

        return sb.toString();
    }

    public static TreeNode deserialize_Levelorder(String str) {
        if (str.length() == 0)
            return null;

        LinkedList<TreeNode> que = new LinkedList<>();
        String[] arr = str.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));

        que.addLast(root);

        int i = 1, n = arr.length;
        while (i < n) {
            TreeNode rnode = que.removeFirst();
            if (i < n && !arr[i].equals("#")) {
                TreeNode lci = new TreeNode(Integer.parseInt(arr[i]));
                rnode.left = lci;
                que.addLast(lci);
            }
            i++;

            if (i < n && !arr[i].equals("#")) {
                TreeNode rci = new TreeNode(Integer.parseInt(arr[i]));
                rnode.right = rci;
                que.addLast(rci);
            }
            i++;
        }

        return root;
    }

    public Node inorderSuccessor(Node node) {

        Node succ = null;
        Node right = node.right;
        if (right != null) {
            while (right.left != null) {
                right = right.left;
            }

            return right;
        }

        while (node.parent != null && node.parent.left != node) {
            node = node.parent;
        }

        return node.parent;
    }

}