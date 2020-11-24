public class l002{
    public static class TreeNode{
        int val = 0;
        Node left = null;
        Node right = null;

        Node(int data){
            this.data = data;
        }
    }

    public static int size(Node node){
        return node == null? 0 : size(node.left) + size(node.right) + 1;
    }

    // Edge : -1, Node : 0
    public static int height(TreeNode root){
        return root == null ? -1 : Math.max(height(root.left),height(root.right)) + 1;
    }

    public static boolean find(TreeNode root,int data){
        TreeNode curr = root;
        while(curr != null){
            if(curr.val == data) return true;
            else if(curr.val < data ) curr = curr.right;
            else curr = curr.left;
        }
    }

    public static TreeNode LCA(TreeNode root,int d1,int d2){
        TreeNode curr = root;
        while(curr != null){
           if(curr.val < d1 && curr.val < d2) curr = curr.right;
           else if(curr.val > d1 && curr.val > d2) curr = curr.left;
           else return curr;
        }

        return null;
    }

    public static int minimum(TreeNode node){
        TreeNode curr = node;
        while(curr.left != null) curr = curr.left;

        return curr.val;
    }

    public static int maximum(TreeNode node){
        TreeNode curr = node;
        while(curr.right != null) curr = curr.right;

        return curr.val;
    }

    public static TreeNode addData(TreeNode node,int data){
        if(node == null) return new Node(data);

        if(node.val > data) node.left = addData(node.left,data);
        else node.right = addData(node.right,data);

        return node;
    }

    public static TreeNode removeData(TreeNode node,int data){
        if(node == null) return new Node(data);

        if(node.val > data) node.left = removeData(node.left,data);
        else if(node.val < data)node.right = removeData(node.right,data);
        else{
            if(node.left == null || node.right == null)
               return node.left != null ? node.left?node.right;

            int rightKaMinimum = minimum(node.right);
            node.val = rightKaMinimum;

            node.right = removeData(node.right, rightKaMinimum);
        }

        return node;
    }

    static int idx = 0;
    public static TreeNode constructFromPreOrder(int[] arr,int lRange,int rRange){
        if(idx == arr.length || arr[idx] < lRange || arr[idx] > rRange) return null;

        TreeNode node = new TreeNode(arr[idx++]);
        node.left = constructFromPreOrder(arr,lRange, node.val);
        node.right = constructFromPreOrder(arr,node.val,rRange);

        return node;
    }







}