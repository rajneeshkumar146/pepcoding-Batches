public class l001{

    public static class TreeNode{
        int val = 0;
        Node left = null;
        Node right = null;

        Node(int data){
            this.data = data;
        }
    }

    public static int size(TreeNode root){
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    // Edge : -1, Node : 0
    public static int height(TreeNode root){
        return root == null ? -1 : Math.max(height(root.left),height(root.right)) + 1;
    }

    public static boolean find(TreeNode root,int data){
        if(root == null) return false;
        if(root.val == data) return true;
        return find(root.left,data) || find(root.right,data);
    }

    public static boolean rootToNodePath(TreeNode root,int data,ArrayList<TreeNode> path){
        if(root == null) return false;
        if(root.val == data){
            path.add(root);
            return true;
        }

        boolean res = rootToNodePath(root.left,data,path) || rootToNodePath(root.right,data,path);
        if(res) path.add(root);
        return res;
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> list1 = new ArrayList<>();
        ArrayList<TreeNode> list2 = new ArrayList<>();
        
        rootToNodePath(root,p.val,list1);
        rootToNodePath(root,q.val,list2);

        int i = list1.size() - 1;
        int j = list2.size() - 1;

        TreeNode LCA = null; 
        while(i>=0 && j>=0){
            if(list1.get(i).val != list2.get(j).val) break;

            LCA = list1.get(i);
            i--;
            j--;
        }
    }

    public static void kaway(TreeNode node,TreeNode block,int k,List<Integer> ans){
        if(node == null || node == block || k < 0) return;
        if(k == 0){
            ans.add(node.val);
            return;
        }

        kaway(node.left,block,k-1,ans);
        kaway(node.right,block,k-1,ans);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        ArrayList<TreeNode> list = new ArrayList<>(); 
        rootToNodePath(root,target.val,list);

        TreeNode block = null;
        List<Integer> ans = new ArrayList<Integer>();
        for(int i=0;i<list.size();i++){
            kaway(list.get(i),block,K-i,ans);
            block = list.get(i);
        }
        return ans;
    }


}