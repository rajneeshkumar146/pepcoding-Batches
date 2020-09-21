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



   

}