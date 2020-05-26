import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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

//Leetcode 987.====================================================


static int leftMinValue=0;
static int rightMaxValue=0;

public static void width(TreeNode node,int lev){
    if(node==null) return;

    leftMinValue=Math.min(leftMinValue,lev);
    rightMaxValue=Math.max(rightMaxValue,lev);
    
    width(node.left, lev - 1);
    width(node.right, lev + 1);
} 

public static class pairVO implements Comparable<pairVO>{
    TreeNode node;  //actual Node
    int vl=0;  // vertical Level
    public pairVO(TreeNode node,int vl){
        this.node=node;
        this.vl=vl;
    }

    @override
    public int compareTo(pairVO o){  // for c++: bool opeartor < ( pairvo const & o) const{
       if(this.vl==o.vl) return this.node.val-o.node.val; // in c++: replace '-' with '>'
       return this.vl-o.vl;  // default behaviour of que // in c++: replace '-' with '>'
    }
}

public List<List<Integer>> verticalTraversal(TreeNode root) {
    List<List<Integer>> ans=new ArrayList<>();
    if(root==null) return ans;

    width(root,0);
    int n=rightMaxValue - leftMinValue + 1;
    for(int i=0;i<n;i++)
        ans.add(new ArrayList<>());

    PriorityQueue<pairVO> pque=new PriorityQueue<>();
    PriorityQueue<pairVO> cque=new PriorityQueue<>();

    pque.add(new pairVO(root,-leftMinValue));

    while(pque.size()!=0){
        int size=pque.size();
        while(size--> 0){
            pairVO rpair=pque.poll();
            ans.get(rpair.vl).add(rpair.node.val);

            if(rpair.node.left!=null) cque.add(new pairVO(rpair.node.left,rpair.vl-1));
            if(rpair.node.right!=null) cque.add(new pairVO(rpair.node.right,rpair.vl+1));
        }

        PriorityQueue<pairVO> temp=pque;
        pque=cque;
        cque=temp;
    }
}

}