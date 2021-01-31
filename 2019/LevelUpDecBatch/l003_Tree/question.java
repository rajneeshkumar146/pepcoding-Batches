import java.util.ArrayList;
public class question {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public int size(TreeNode node) {
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    // Edges, for Nodes : return node == null ? 0
    public int height(TreeNode node) {
        return node == null ? -1 : Math.max(height(node.left), height(node.right)) + 1;
    }

    public int maximum(TreeNode node) {
        if (node == null)
            return -(int) 1e9;
        int lmv = maximum(node.left); // left maximum value
        int rmv = maximum(node.right); // right maximum value

        return Math.max(Math.max(lmv, rmv), node.val);
    }

    public boolean find(TreeNode node, int data) {
        if(node == null) return false;
        if(node.val == data){
            return true;
        }

        return find(node.left,data) || find(node.right,data); 
    }

    // ArrayList<Node>
    public void rootToNodePath(TreeNode node,int data){

    }

    //236
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
    }

}