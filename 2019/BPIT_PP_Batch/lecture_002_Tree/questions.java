public class questions{

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    //Leetcode 112
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null) return false;
        if(root.left == null && root.right == null && sum - root.val == 0) return true;  

        return hasPathSum(root.left,sum-root.val) || hasPathSum(root.right,sum-root.val); 
    }

    //Leetcode 113
    public void pathSum_(TreeNode root, int sum,List<List<Integer>> Res,List<Integer> SmallAns) {
        if(root == null) return;
        if(root.left == null && root.right == null && sum - root.val == 0){
            List<Integer> ans = new ArrayList<>(SmallAns);
            ans.add(root.val);
            Res.add(ans);
            return;
        }
       
        SmallAns.add(root.val);
        
        pathSum_(root.left, sum-root.val, Res, SmallAns);
        pathSum_(root.right, sum-root.val, Res, SmallAns);
        
        SmallAns.remove(SmallAns.size() - 1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> Res=new ArrayList<>();
        List<Integer> SmallAns=new ArrayList<>();

        pathSum_(root,sum,Res,SmallAns);
        return Res;
    }

    //Leetcode 124
    int NTNRes = -(int)1e8;
    public int maxPathSum_(TreeNode root) {
        if(root== null ) return 0;

        int lMax = maxPathSum_(root.left);
        int rMax = maxPathSum_(root.right); 

        int max_ = Math.max(lMax,rMax) + root.val;
        NTNRes = Math.max(Math.max(NTNRes,root.val),Math.max(max_ ,lMax + root.val + rMax));

        return Math.max(max_, root.val);
    }
    
    public int maxPathSum(TreeNode root) {
        maxPathSum_(root);
        return NTNRes;
    }

    
}