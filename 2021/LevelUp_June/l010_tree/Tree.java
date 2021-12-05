import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;

class tree{
    class Node {
        public int val;
        public List<Node> children;
    
        public Node() {}
    
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // leet 589 ========================================

    public void rec(Node root,List<Integer> ans){
        ans.add(root.val);

        for(Node child:root.children){
            rec(child,ans);
        }
    }


    public List<Integer> preorder(Node root) {
        if(root==null) return new ArrayList<>();
        
        List<Integer> ans=new ArrayList<>();
        rec(root,ans);
        return ans;
    }

    // is mirror 
    public static boolean areMirror(Node n1, Node n2) {
        if(n1.children.size()!=n2.children.size()) return false;
        // if(n1.data!=n2.data) return false;
        
        int size=n1.children.size();
        
        for(int i=0; i<size; i++){
            if(!areMirror(n1.children.get(i),n2.children.get(size-1-i))) return false;
        }
        
        return true;
      }

      public boolean find(TreeNode root, TreeNode data){
          if(root==null || data==null) return false;

          if(root==data) return true;

          return find(root.right,data) || find(root.left,data);
      }


      // leet 236 ============================================= 
    public boolean  nodeToRootPath(TreeNode root, TreeNode p,ArrayList<TreeNode> ntr){
        if(root==null || p==null) return false;

        if(root==p){
            ntr.add(root);
            return true;
        }

        boolean ans=nodeToRootPath(root.left,p,ntr) || nodeToRootPath(root.right,p,ntr);

        if(ans){
            ntr.add(root);
        }

        return ans;
    } 

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> ntr1=new ArrayList<>();
        nodeToRootPath(root,p,ntr1);
        
        ArrayList<TreeNode> ntr2=new ArrayList<>();
        nodeToRootPath(root,q,ntr2);

        int i=ntr1.size()-1;
        int j=ntr2.size()-1;

        while(i>=0 && j>=0 && ntr1.get(i)==ntr2.get(j)){
            i--;
            j--;
        }

        i++;
        return ntr1.get(i);        
    }

    // lca optimized (no extra space)
    TreeNode lca=null;
    public boolean find(TreeNode root, TreeNode p, TreeNode q){
        if(root==null) return false;

        boolean left=find(root.left,p,q);
        boolean right=find(root.right,p,q);

        // getting true from left and right 
        if(left && right){
            lca=root;
        }

        // one data is root, another is on the left side or right side
        if(root==p || root==q){
            if(left || right){
                lca=root;
            }
            return true;
        }

        // if(root==q){
        //     if(left || right){
        //         lca=root;
        //     }
        //     return true;
        // }

        return left || right;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        find(root,p,q);
        return lca;
    }


    // k down in a binary tree =====================
    public void fill(TreeNode root, List<int> ans, int k){
        if(root==null) return false;

        if(k==0){
            ans.add(root.val);
            return;
        }

        fill(root.left,ans,k-1,blocker);
        fill(root.right,ans,k-1,blocker);
    }

    public List<TreeNode> kdown(TreeNode root, int k){
        List<TreeNode> ans=new ArrayList<>();

        fill(root,p,ans,k);

        return ans;
    }

    public void kdown_fill(TreeNode root, int k, List<Integer> ans,TreeNode blocker){
        if(root==null || k<0 || root==blocker) return;

        if(k==0){
            ans.add(root.val);
            return;
        }

        kdown_fill(root.left,k-1,ans,blocker);
        kdown_fill(root.right,k-1,ans,blocker);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<TreeNode> ntr=new ArrayList<>();
        nodeToRootPath(root,target,ntr);

        TreeNode blocker=null;

        List<Integer> ans=new ArrayList<>();

        for(int i=0; i<ntr.size(); i++){
            kdown_fill(ntr.get(i),k-i,ans,blocker);
            blocker=ntr.get(i);
        }

        return ans;
    }
    public int k_far_find(TreeNode root, TreeNode target, int k,List<Integer> ans){
        if(root==null) return -1;

        if(root==target){
            kdown_fill(root,k,ans,null);
            return 1;
        }

        int left_dis=k_far_find(root.left,target,k,ans);
        int right_dis=k_far_find(root.right,target,k,ans);

        if(left_dis>=0){
            kdown_fill(root,k-left_dis,ans,root.left);
            return left_dis+1;
        }

        if(right_dis>=0){
            kdown_fill(root,k-right_dis,ans,root.right);
            return right_dis+1;
        }

        return -1;
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans=new ArrayList<>();

        k_far_find(root,target,k,ans);

        return ans;
    }


    // burning Tree ===================================================== 

    // moving downWards and burning nodes
    public static void getBurningNodesAtSpecificTime(TreeNode root, int time,ArrayList<ArrayList<Integer>> ans,TreeNode blocker){
        if(root==null || root==blocker) return;

        if(time==ans.size()){
            ans.add(new ArrayList<>());
        }

        ans.get(time).add(root.val);

        getBurningNodesAtSpecificTime(root.left,time+1,ans,blocker);
        getBurningNodesAtSpecificTime(root.right,time+1,ans,blocker);
    }


    public static int find_burningTre(TreeNode root, TreeNode data, ArrayList<ArrayList<Integer>> ans){
        if(root==null) return -1;

        if(root==data){
            // get every node below this node
            getBurningNodesAtSpecificTime(root,0,ans,null);
            return 1;
        }

        int left_dis=find_burningTre(root.left,data,ans);
        int right_dis=find_burningTre(root.right,data,ans);

        if(left_dis>=0){
            // get nodes
            getBurningNodesAtSpecificTime(root, left_dis, ans, root.left);
            return left_dis+1;
        }

        if(right_dis>=0){
            // get nodes
            getBurningNodesAtSpecificTime(root, right_dis, ans, root.right);
            return right_dis+1;
        }

        return -1;
    }


    public static void burningTree(TreeNode root, TreeNode data){
        ArrayList<ArrayList<Integer>> ans=new ArrayList<>();
        find_burningTre(root, data,ans);

    }


    // burning tree with water nodes ======================================================== 

    public static void getBurningNodesAtSpecificTime(TreeNode root, int time,ArrayList<ArrayList<Integer>> ans,TreeNode blocker,HashSet<TreeNode> water){
        if(root==null || root==blocker || water.contains(root)) return;

        if(time==ans.size()){
            ans.add(new ArrayList<>());
        }

        ans.get(time).add(root.val);

        getBurningNodesAtSpecificTime(root.left,time+1,ans,blocker);
        getBurningNodesAtSpecificTime(root.right,time+1,ans,blocker);
    }


    public static int find_burningTre(TreeNode root, TreeNode data, ArrayList<ArrayList<Integer>> ans,HashSet<TreeNode> water){
        if(root==null) return -1;

        if(root==data){
            // get every node below this node
            getBurningNodesAtSpecificTime(root,0,ans,null,water);
            return 1;
        }

        int left_dis=find_burningTre(root.left,data,ans,water);
        int right_dis=find_burningTre(root.right,data,ans,water);

        if(left_dis>=0){
            // get nodes
            getBurningNodesAtSpecificTime(root, left_dis, ans, root.left,water);
            return left_dis+1;
        }

        if(right_dis>=0){
            // get nodes
            getBurningNodesAtSpecificTime(root, right_dis, ans, root.right,water);
            return right_dis+1;
        }

        return -1;
    }


    public static void burningTree_water(TreeNode root, TreeNode data, ArrayList<TreeNode> waterNodes){
        ArrayList<ArrayList<Integer>> ans=new ArrayList<>();
        HashSet<TreeNode> water=new HashSet<>();

        for(TreeNode w:waterNodes){
            water.add(w);
        }

        find_burningTre(root, data,ans,water);

    }
}