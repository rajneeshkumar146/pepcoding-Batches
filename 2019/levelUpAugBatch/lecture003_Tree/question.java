public class question{
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    // Leetcode 236
    public boolean nodeToRootPath(TreeNode node,int data,ArrayList<TreeNode> ans){
        if(node==null) return false;
        
        if(node.val == data){
            ans.add(node);
            return true;
        }
        
        boolean res = nodeToRootPath(node.left,data,ans) || nodeToRootPath(node.right,data,ans);
        if(res){
            ans.add(node);
        }
        
        return res;
    }
    
    
    public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
            ArrayList<TreeNode> list1 = new ArrayList<>(); 
                nodeToRootPath(node,p.val,list1);
        ArrayList<TreeNode> list2 = new ArrayList<>(); 
            nodeToRootPath(node,q.val,list2);

        int i = list1.size()-1;
        int j = list2.size()-1;

        TreeNode LCA = null;
        while(i>=0 && j>=0){
            if(list1.get(i)==list2.get(j)) LCA = list1.get(i);

            i--;
            j--;
        }
        
        return LCA;
    }

    //Leetcode 112
    public boolean hasPathSum(TreeNode root, int sum) {
     if(root==null) return false;
     if(root.left == null && root.right == null && sum-root.val == 0) return true;
     
     return hasPathSum(root.left,sum-root.val) || hasPathSum(root.right,sum-root.val);
    } 

    //Leetcode 113
    public void pathSum(TreeNode root, int sum,List<List<Integer>> res,List<Integer> ans) {
        if(root==null) return;
        if(root.left == null && root.right == null && sum-root.val == 0){
            List<Integer> base = new ArrayList<>(ans);
            base.add(root.val);
            res.add(base);    
            return;
        }

        ans.add(root.val);
        pathSum(root.left,sum-root.val,res,ans);
        pathSum(root.right,sum-root.val,res,ans);        
        ans.remove(ans.size()-1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        pathSum(root,sum,res,new ArrayList<>());
        return res;
    }

    // https://practice.geeksforgeeks.org/problems/maximum-path-sum/1
    static int maxLL = -(int)1e8;
    public static int leafToLeaf(Node node){
        if(node==null) return -(int)1e8;
        if(node.left == null && node.right == null ) return node.data;

        int nodeToLeafLeft = leafToLeaf(node.left);
        int nodeToLeafRight = leafToLeaf(node.right);

        if(node.left != null && node.right !=null){
            maxLL = Math.max(maxLL,nodeToLeafLeft + nodeToLeafRight + node.data);
        }
        
        return Math.max(nodeToLeafLeft,nodeToLeafRight) + node.data;
    }

    // Leetcode 119
    public static class pair{
        TreeNode node = null;
        int val = 0;

        pair(TreeNode node,int val){
            this.node = node;
            this.val = val;
        }
    }

    public static List<Integer> leftView(TreeNode node){
        LinkedList<pair> que = new LinkedList<>();
        que.addLast(new pair(node,0));

        int level = 0;
        ArrayList<pair> ans = new ArrayList<>();

        while(que.size()!=0){
            int size = que.size();
            while(size-- > 0){
                pair vtx = que.removeFirst();
                
                if(level == ans.size()) ans.add(vtx);   
                else if(vtx.val < ans.get(level).val) ans.set(level,vtx);

                if(vtx.node.left != null) que.addLast(new pair(vtx.node.left,vtx.val - 1));
                if(vtx.node.right != null) que.addLast(new pair(vtx.node.right,vtx.val + 1));
            
            }
            level++;
        }

        ArrayList<Integer> res = new ArrayList<>();
        for(pair p : ans){
            res.add(p.node.val);
        }

        return res;
    }

    //Leetcode 98
    TreeNode prev = null;
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;

        if(!isValidBST(root.left)) return false;
        
        if(prev!= null && prev.val > root.val) return false;
        prev = root;

        if(!isValidBST(root.right)) return false;

        return true;
    }

    public class BSTpair{
        boolean isBST = true;
        long min =  (long)1e18;
        long max = -(long)1e18;
    }

    public BSTpair isValidBST_(TreeNode root) {
        if(root == null) return new BSTpair();

        BSTpair left = isValidBST_(root.left);
        BSTpair right = isValidBST_(root.right);

        BSTpair myAns = new BSTpair();
        if(!left.isBST || !right.isBST ||  left.max >= root.val || right.min <= root.val){
            myAns.isBST = false;
            return myAns;
        
        }

        myAns.min = Math.min(left.min,root.val);
        myAns.max = Math.max(right.max,root.val); 

        return myAns;
    }
    
    public boolean isValidBST(TreeNode node){
        return isValidBST_(node).isBST;
    }

    public TreeNode buildTree(int[] preorder,int psi,int pei, int[] inorder,int isi,int iei){
        if(psi > pei) return null;

        TreeNode node = new TreeNode(preorder[psi]);
        int idx = isi;
        while(inorder[idx]!=preorder[psi]){
            idx++;
        }

        int count = idx - isi; // countOfNodesInLeftSubTree.

        node.left = buildTree(preorder,psi+1,psi+count,inorder,isi,idx-1);
        node.right = buildTree(preorder,psi+count+1,pei,inorder,idx+1,iei);

        return node;
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0) return null;
        int n = preorder.length;

        return buildTree(preorder,0,n-1,inorder,0,n-1);
    }

    //106
    public TreeNode buildTree(int[] postorder,int psi,int pei, int[] inorder,int isi,int iei) {
        if(psi > pei) return null;

        TreeNode node = new TreeNode(postorder[pei]);
        int idx = isi;

        while(inorder[idx] != postorder[pei]){
            idx++;
        }

        int count = idx - isi;

        node.left = buildTree(postorder,psi,psi + count - 1, inorder, isi,idx-1);
        node.right = buildTree(postorder,psi + count,pei-1, inorder, idx+1,iei);

        return node;
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
            if(postorder.length==0) return null;
            int n = postorder.length;
    
            return buildTree(postorder,0,n-1,inorder,0,n-1);
    }

    //Leetcode 889
    public TreeNode buildTree(int[] postorder,int posi,int poei, int[] preorder,int psi,int pei) {
        if(psi > pei) return null;
        if(psi == pei) return new TreeNode(preorder[psi]); // leaf node

        TreeNode node = new TreeNode(preorder[psi]);
        int idx = posi;

        while(postorder[idx] != preorder[psi + 1]){
            idx++;
        }

        int count = idx - posi + 1;

        node.left = buildTree(postorder,posi,posi + count - 1, preorder, psi+1,psi + count);
        node.right = buildTree(postorder,posi + count,poei-1, preorder,psi + count + 1, pei);

        return node;
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if(post.length==0) return null;
        int n = post.length;

        return buildTree(post,0,n-1,pre,0,n-1);        
    }

    // 968
    
    
    // -1 i am covered by a camera .
    // 0 i am a camera.
    // 1 means i need a camera.

    int camera=0;
    public int minCameraCover(TreeNode root) {
        if(minCameraCover_(root) == 1) camera++;
        return camera;
    }
    
    public int minCameraCover_(TreeNode root){
        if(root==null) return  -1;
        
        int lres = minCameraCover_(root.left);
        int rres = minCameraCover_(root.right);

        if(lres == 1 || rres == 1){
            camera++;
            return 0;
        }

        if(lres == 0 || rres == 0){
            return -1;
        }

        return 1;
    }
    
}