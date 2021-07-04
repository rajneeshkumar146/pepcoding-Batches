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

}