import java.util.ArrayList;
import java.util.LinkedList;

public class l004Construction {
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

    public static TreeNode constructFromInOrder(int[] inOrder) {
        return constructFromInOrder(inOrder, 0, inOrder.length - 1);
    }

    public class Codec {
        public void serialize(TreeNode root, StringBuilder sb) {
            if (root == null)
                return;

            sb.append(root.val + " ");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }

        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);

            return sb.toString();
        }

        public TreeNode bstFromPreorder(int[] preorder, int lr, int rr, int[] idx) {
            int i = idx[0];
            if (i >= preorder.length || preorder[i] < lr || preorder[i] > rr)
                return null;

            TreeNode root = new TreeNode(preorder[i]);
            idx[0]++;

            root.left = bstFromPreorder(preorder, lr, root.val, idx);
            root.right = bstFromPreorder(preorder, root.val, rr, idx);

            return root;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.equals(""))
                return null;

            String[] arr = data.split(" ");
            int[] preorder = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                preorder[i] = Integer.parseInt(arr[i]);
            }

            int[] idx = new int[1];
            return bstFromPreorder(preorder, -(int) 1e9, (int) 1e9, idx);
        }
    }

    public static TreeNode buildTree(int[] preorder, int psi, int pei, int[] inorder, int isi, int iei) {
        if (psi > pei)
            return null;

        int idx = isi;
        while (inorder[idx] != preorder[psi])
            idx++;

        int tnel = idx - isi;
        TreeNode root = new TreeNode(preorder[psi]);
        root.left = buildTree(preorder, psi + 1, psi + tnel, inorder, isi, idx - 1);
        root.right = buildTree(preorder, psi + tnel + 1, pei, inorder, idx + 1, iei);

        return root;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        return buildTree(preorder, 0, n - 1, inorder, 0, n - 1);
    }

    public static TreeNode buildTreeFromPostAndIn(int[] postOrder, int psi, int pei, int[] inorder, int isi, int iei) {
        if (psi > pei)
            return null;

        int idx = isi;
        while (inorder[idx] != postOrder[pei])
            idx++;

        int tnel = idx - isi;
        TreeNode root = new TreeNode(postOrder[pei]);
        root.left = buildTreeFromPostAndIn(postOrder, psi, psi + tnel - 1, inorder, isi, idx - 1);
        root.right = buildTreeFromPostAndIn(postOrder, psi + tnel, pei - 1, inorder, idx + 1, iei);

        return root;
    }

    public static TreeNode buildTreeFromPostAndIn(int[] inorder, int[] postorder) {
        int n = inorder.length;
        return buildTreeFromPostAndIn(postorder, 0, n - 1, inorder, 0, n - 1);
    }

    public static TreeNode constructFromPrePost(int[] postOrder, int ppsi, int ppei, int[] preOrder, int psi, int pei) {
        if (psi > pei)
            return null;

        TreeNode root = new TreeNode(preOrder[psi]);
        if (psi == pei)
            return root;

        int idx = ppsi;
        while (postOrder[idx] != preOrder[psi + 1])
            idx++;

        int tnel = idx - ppsi + 1;
        root.left = constructFromPrePost(postOrder, ppsi, ppsi + tnel - 1, preOrder, psi + 1, psi + tnel);
        root.right = constructFromPrePost(postOrder, ppsi + tnel, pei - 1, preOrder, psi + tnel + 1, pei);

        return root;
    }

    public static TreeNode constructFromPrePost(int[] preOrder, int[] postorder) {
        int n = postorder.length;
        return constructFromPrePost(postorder, 0, n - 1, preOrder, 0, n - 1);
    }

    public class CodecBinaryTree {

        public void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("# ");
                return;
            }

            sb.append(root.val + " ");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }

        public String serialize(TreeNode root) {
            if (root == null)
                return "";
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);

            return sb.toString();
        }

        int idx = 0;

        public TreeNode deserialize(String[] arr) {
            if (idx >= arr.length || arr[idx].equals("#")) {
                idx++;
                return null;
            }

            TreeNode root = new TreeNode(Integer.parseInt(arr[idx++]));
            root.left = deserialize(arr);
            root.right = deserialize(arr);

            return root;
        }

        public TreeNode deserialize(String data) {
            if (data.length() == 0)
                return null;

            idx = 0;
            String[] arr = data.split(" ");
            return deserialize(arr);
        }
    }

    public class CodecBinaryTree_02 {

        public String serialize(TreeNode root) {
            if (root == null)
                return "";
            StringBuilder sb = new StringBuilder();
            LinkedList<TreeNode> que = new LinkedList<>();
            que.addLast(root);

            while (que.size() != 0) {
                TreeNode rnode = que.removeFirst();
                sb.append((rnode != null ? rnode.val : "#") + " ");

                if (rnode == null)
                    continue;

                que.addLast(rnode.left);
                que.addLast(rnode.right);
            }

            return sb.toString();
        }

        public TreeNode deserialize(String data) {
            if (data.length() == 0)
                return null;

            String[] arr = data.split(" ");
            LinkedList<TreeNode> que = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
            que.addLast(root);

            int idx = 1;
            while (que.size() != 0) {
                TreeNode rnode = que.removeFirst();

                if (!arr[idx].equals("#")) {
                    TreeNode leftChild = new TreeNode(Integer.parseInt(arr[idx]));
                    rnode.left = leftChild;
                    que.addLast(leftChild);
                }
                idx++;

                if (!arr[idx].equals("#")) {
                    TreeNode rightChild = new TreeNode(Integer.parseInt(arr[idx]));
                    rnode.right = rightChild;
                    que.addLast(rightChild);
                }
                idx++;

            }

            return root;
        }
    }

}