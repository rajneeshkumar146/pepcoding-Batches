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

    public List<Integer> distanceK_02(TreeNode root, TreeNode target, int K) {
    
    }
    
    public List<Integer> distanceK_02(TreeNode root, TreeNode target, int K) {
        List<Integer> ans=new ArrayList<>();
        return ans;
    }      





}