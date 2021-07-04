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
}