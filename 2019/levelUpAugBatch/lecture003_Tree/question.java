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
}