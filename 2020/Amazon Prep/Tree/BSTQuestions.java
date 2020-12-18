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

    public static TreeNode addData(TreeNode node,int data){
        if(node == null){
            return new Node(data);
        }

        if(node.val < data) node.right = addData(node.right,data);
        else node.left = addData(node.left,data);
        return node;
    }

    public static TreeNode removeData(TreeNode node,int data){
        if(node == null) return null;

        if(node.val < data) node.right = removeData(node.right,data);
        else if(node.val > data) node.left = removeData(node.left,data);
        else{
            if(node.left == null || node.right == null) 
               return node.left != null ? node.left : node.right;
            
            int maxChild = maxValue(node.left);
            node.val = maxChild;
            node.left = removeData(node.left, maxChild);
        }

        return node;
    }

    
    //105
    public TreeNode buildTree(int[] preorder,int psi,int pei, int[] inorder,int isi,int iei) {
        if(psi > pei) return null;

        int idx = isi;
        while(preorder[psi] != inorder[idx]) idx++;
        int count = idx - isi;

        TreeNode node = new TreeNode(preorder[psi]);
        node.left = buildTree(preorder, psi + 1,psi + count, inorder, isi , idx - 1);
        node.right = buildTree(preorder, psi + count + 1, pei, inorder, idx + 1 , iei);
        
        return node;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        
    }

    //106
    public TreeNode buildTree(int[] inorder,int isi,int iei, int[] postorder,int psi,int pei) {
        if(psi > pei) return null;

        int idx = isi;
        while(postorder[pei] != inorder[idx]) idx++;
        int count = idx - isi;
        TreeNode node = new TreeNode(postorder[pei]);

        node.left = buildTree(inorder, isi , idx - 1, postorder, psi ,psi + count - 1);
        node.right = buildTree(inorder, idx + 1 , iei, postorder, psi + count, pei - 1);
        
        return node;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    // 114
    public TreeNode flatten_(TreeNode root) {
        if(root == null) return null;
        if(root.left == null && root.right == null) return root; // tail
        
        TreeNode lastNodeOfLeftSubtree = flatten_(root.left);
        TreeNode lastNodeOfrightSubtree = flatten_(root.right);
        
        if(root.left != null){
            lastNodeOfLeftSubtree.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        
        
        return lastNodeOfrightSubtree != null ? lastNodeOfrightSubtree : lastNodeOfLeftSubtree;
    }
    
    public void flatten(TreeNode root) {
        flatten_(root);
    }

    // https://practice.geeksforgeeks.org/problems/binary-tree-to-dll/1
    Node head = null;
    Node prev = null;
    void bToDLL_(Node node){
        if(node == null) return;
        
        bToDLL_(node.left);

        if(head == null) head = node;
        else{
            prev.right = node;
            node.left = prev;
        }
        
        prev = node;
        bToDLL_(node.right);
    }

    Node bToDLL(Node root){
      bToDLL_(root);
      return head;
    }

    // CDLL
    // BT -> BST

    //889
    public TreeNode constructFromPrePost(int[] pre,int psi,int pei, int[] post,int posi,int poei) {
        if(psi > pei) return null;
        if(psi == pei) return  new TreeNode(pre[psi]);
        
        int idx = posi;
        while(pre[psi + 1] != post[idx]){
            idx++;
        }
        
        int count = idx - posi + 1;
        TreeNode node = new TreeNode(pre[psi]);
        node.left = constructFromPrePost(pre,psi + 1, psi + count, post, posi, idx);
        node.right = constructFromPrePost(pre,psi + count + 1, pei, post, idx + 1, poei - 1);
        
        return node;
        
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int n = pre.length;
        
        return constructFromPrePost(pre, 0, n - 1, post,0, n - 1); 
        
    }
}