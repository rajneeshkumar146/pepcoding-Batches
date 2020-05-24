public class question{
     public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
     }

     //Leetcode: 863.=======================================================
     public boolean rootToNodePath_(TreeNode root,int data,ArrayList<TreeNode> path){
        if(root==null) return false;
        if(root.val==data){
            path.add(root);
            return true;
        }
  
        boolean res = rootToNodePath_(root.left,data,path) || rootToNodePath_(root.right,data,path);
       if(res) path.add(root);
        return res;
      }

     public void kDown(TreeNode root,int level,TreeNode blockNode,List<Integer> ans){
        if(root==null || root==blockNode) return;
 
        if(level==0){
          ans.add(root.val);
          return;  
        }
 
        kDown(root.left,level-1,blockNode,ans);
        kDown(root.right,level-1,blockNode,ans);
 
     }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        ArrayList<TreeNode> path=new ArrayList<>();
        rootToNodePath_(root,target.val,path);

        TreeNode blockNode=null;
        List<Integer> ans=new ArrayList<>();
        for(int i=0;i<path.size();i++){
            if(K-i<0) break;
            kDown(path.get(i),K-i,blockNode,ans);
            blockNode=path.get(i);
        }  
        return ans;
    }     

    // Leetcode: 112.=======================================================
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null) return false;
        if(root.left==null && root.right==null && sum-root.val==0)
          return true;
    
        boolean res=false;
        res=res || hasPathSum(root.left,sum-root.val);
        res=res || hasPathSum(root.right,sum-root.val);
        return res;
    }

    //Leetcode: 113.========================================================
    
    public void pathSum(TreeNode root, int sum,List<List<Integer>> res,List<Integer> smallAns) {
        if(root==null) return;
        if(root.left==null && root.right==null && sum-root.val==0){
            List<Integer> base=new ArrayList<>(smallAns);// res.push_back(smallAns); res.back().push_back(root.val);
            base.add(root.val);
            res.add(base);
            return;
        }
        
        smallAns.add(root.val);
        pathSum(root.left,sum-root.val,res,smallAns);
        pathSum(root.right,sum-root.val,res,smallAns);
        smallAns.remove(smallAns.size()-1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root==null) return new ArrayList<>();

        List<List<Integer>> res=new ArrayList<>();
        List<Integer> smallAns=new ArrayList<>();
        pathSum(root,sum,res,smallAns);
        return res;
    }

    //geeks: https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/

    public static int maxPathSum(Node root) {
        max_leafToLeafSum=(int) -1e8;
        leafToLeaf(root);
        return max_leafToLeafSum;
    }
   
   
   public static int leafToLeaf(Node node){
       if(node==null) return 0;

       int leftNodeToLeafSum = leafToLeaf(node.left);
       int rightNodeToLeafSum = leafToLeaf(node.right);

       if(node.left!=null && node.right!=null){
           max_leafToLeafSum=Math.max(max_leafToLeafSum,leftNodeToLeafSum + rightNodeToLeafSum + node.data);
           return Math.max(leftNodeToLeafSum , rightNodeToLeafSum) + node.data;
       }

       return (node.left==null? rightNodeToLeafSum: leftNodeToLeafSum) + node.data;
   }

   //Leetcode 124.========================================================

   int max_nodeToNodeSum = (int)-1e8;
   public int maxPathSum(TreeNode root) {
       maxPathSum_(root);
       return max_nodeToNodeSum;
   }     
  public int maxPathSum_(TreeNode node){
   if(node==null) return 0;

   int leftNodeToNodeSum = maxPathSum_(node.left);
   int rightNodeToNodeSum = maxPathSum_(node.right);
   
   int max_=Math.max(leftNodeToNodeSum,rightNodeToNodeSum) + node.val;
   max_nodeToNodeSum=Math.max(Math.max(max_nodeToNodeSum,node.val),
                    Math.max(leftNodeToNodeSum + node.val + rightNodeToNodeSum, max_));

   return Math.max(max_,node.val);
}

}