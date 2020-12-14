public class file{
    public class TreeNode{
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val){
            this.val = val;
        }
    }

    public static int size(TreeNode node){
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    public static int hight(TreeNode node){
        return node == null ? 0 : Math.max(height(node.left), height(node.right)) + 1;
    }

    public static boolean find(TreeNode node,int data){
        if(node == null) return false;
        if(node.val == data) return true;

        return find(node.left,data) || find(node.right,data);
    }

    public boolean rootToNodePath(TreeNode node,int val,ArrayList<TreeNode> ans){
        if(node == null) return false;
        if(node.val == val){
            ans.add(node);
            return true;
        }

        boolean res = rootToNodePath(node.left, val, ans) 
                      || rootToNodePath(node.right, val, ans);
        if(res) ans.add(node);

        return res;
    }

    public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> list1 = new ArrayList<>(); 
        rootToNodePath(node,p.val,list1);
        
        ArrayList<TreeNode> list2 = new ArrayList<>(); 
        rootToNodePath(node,q.val,list2);

        int i = list1.size() - 1;
        int j = list2.size() - 1;

        TreeNode LCA = null;
        while(i >= 0 && j >= 0){
            if(list1.get(i).val != list2.get(j).val) break;

            LCA = list1.get(i);
            i--;
            j--;
        }

        return LCA;
    }

    public void printBelowAtK(TreeNode node,int k,TreeNode block,List<Integer> ans){
        if(node == null || node == block || k < 0) return;
        if(k == 0){
            ans.add(node.val);
            return;
        }

        printBelowAtK(node.left,k - 1, block, ans);
        printBelowAtK(node.right,k - 1, block, ans);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        ArrayList<TreeNode> list = new ArrayList<>(); 
        rootToNodePath(root,target.val,list);    
        
        TreeNode block = null;
        List<Integer> ans = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){
            printBelowAtK(list.get(i),K - i, block, ans);
            block = list.get(i);
        }

        return ans;
    }

    // method 2
    public int distanceK(TreeNode node, TreeNode target, int K, List<Integer> ans) {
        if(node == null) return -1;
        if(node == target){
            printBelowAtK(node,K,null,ans);
            return 1;
        }

        int lRes = distanceK(node.left,target,K,ans);
        if(lRes != -1){
            printBelowAtK(node,K - lRes,node.left,ans);
            return lRes + 1;
        }

        int rRes = distanceK(node.right,target,K,ans);
        if(rRes != -1){
            printBelowAtK(node,K - rRes,node.right,ans);
            return rRes + 1;
        }

        return -1;
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        ArrayList<Integer> ans = new ArrayList<>(); 
        distanceK(root,target,K,ans);
        return ans;
    }

    // Burning Tree

    public void BurnTime(TreeNode node,int time,TreeNode block,List<List<Integer>> ans){
       if(node == null || node == block) return; 
       
       if(ans.size() == time) ans.add(new ArrayList<>());
       ans.get(time).add(node.val);

       BurnTime(node.left,time + 1, block, ans);
       BurnTime(node.right,time + 1, block, ans);
    
    }

    public List<Integer> BurningTree(TreeNode root, TreeNode target, int K) {
        ArrayList<TreeNode> list = new ArrayList<>(); 
        rootToNodePath(root,target.val,list);    
        
        TreeNode block = null;
        List<Integer> ans = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){
            BurnTime(list.get(i),i, block, ans);
            block = list.get(i);
        }

        return ans;
    }

    // -1 : need a camera, 0 : i have a camera, 1 : i'm covered.
    int camera = 0;
    public int minCameraCover_(TreeNode root) {
        if(root == null){
            return 1;
        }

        int lres = minCameraCover_(root.left);
        int rres = minCameraCover_(root.right);

        if(lres == -1 || rres == -1){
            camera++;
            return 0;
        }

        if(lres == 0 || rres == 0){
            return 1;
        }

        return -1;
    }

    public int minCameraCover(TreeNode root) {
        if(minCameraCover_(root) == -1) camera++;
        return camera;
    }

    //https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/

    int llMaxSum = -(int)1e9;
    public int leafToLeafSum(Node node){
        if(node == null) return -(int)1e9;
        if(node.left == null && node.right == null) return node.data;

        int leftMaxSum = leafToLeafSum(node.left);
        int rightMaxSum = leafToLeafSum(node.right);

        if(node.left != null && node.right != null) 
            llMaxSum = Math.max(llMaxSum, 
                                leftMaxSum + node.data
                                 + rightMaxSum);
        return Math.max(leftMaxSum,rightMaxSum) + node.data;
    }

    //124
    public int maxPathSum(Node root)
    { 
        leafToLeafSum(root);
        return llMaxSum;
    }

    int nnMaxSum = -(int)1e9;
    public int maxPathSum_(TreeNode node) {
        if(node == null) return 0;
        int leftNodeMaxSum = maxPathSum_(node.left);
        int rightNodeMaxSum = maxPathSum_(node.right);
        
        int maxSum = Math.max(leftNodeMaxSum,rightNodeMaxSum) + node.val;
        
       nnMaxSum = Math.max(Math.max(nnMaxSum,node.val),Math.max(leftNodeMaxSum + node.val + rightNodeMaxSum, maxSum));
        
        return Math.max(maxSum, node.val);
    }
    
    public int maxPathSum(TreeNode root) {
        
        maxPathSum_(root);
        return nnMaxSum;
    }

    













}