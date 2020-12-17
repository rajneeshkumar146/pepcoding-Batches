public class BSTQuestions{

    public class TreeNode{
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val){
            this.val = val;
        }
    }

    public  int size(TreeNode node){
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    public  int hight(TreeNode node){
        return node == null ? 0 : Math.max(height(node.left), height(node.right)) + 1;
    }

    public  int maxValue(TreeNode node){
        TreeNode curr = node;
        while(curr.right != null){
            curr = curr.right;
        }

        return curr.val;
    }

    public  int minValue(TreeNode node){
        TreeNode curr = node;
        while(curr.left != null){
            curr = curr.left;
        }

        return curr.val;
    }

    public  boolean find(TreeNode node,int data){
        TreeNode curr = node;
        while(curr != null){
            if(curr.val == data) return true;
            else if(curr.val < data) curr = curr.right;
            else curr = curr.left;
        }

        return false;
    }

    public TreeNode LCANode(TreeNode node,int d1,int d2){
        TreeNode curr = node;
        while(curr != null){
            if(curr.val < d1 && curr.val < d2) curr = curr.right;
            else if(curr.val > d1 && curr.val > d2) curr = curr.left;
            else return curr;
        }

        return null;
    }

    TreeNode prev = null;
    public boolean isBST(TreeNode node){
        if(node == null) return true;

        if(!isBST(node.left)) return false;

        if(prev != null && prev.val > node.val) return false;
        prev = node;
        
        if(!isBST(node.right)) return false;

        return true;
    }

    //99
    TreeNode a = null;
    TreeNode b = null;
    TreeNode prev = null;
    
    public boolean recoverTree_(TreeNode root) {
        if(root == null) return false;
        
        if(recoverTree_(root.left)) return true;
           if(prev != null && prev.val > root.val){
               b = root;
               if(a == null) a = prev;
               else return true;
           }
        
           prev = root;
        if(recoverTree_(root.right)) return true;
        
        return false;
    }
    
    public void recoverTree(TreeNode root) {
        recoverTree_(root);
        if(a != null){
            int temp = a.val;
            a.val = b.val;
            b.val = temp;
        }
    }








}