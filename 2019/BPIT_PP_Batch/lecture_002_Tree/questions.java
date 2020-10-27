import java.util.Stack;
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

    // Leetcode 863
    public void kDown(TreeNode node,TreeNode block,int level,List<Integer> ans){
        if(node==null || node == block || level < 0) return;

        if(level == 0 ) {
            ans.add(node.val);
            return;
        }

        kDown(node.left,block,level-1,ans);
        kDown(node.right,block,level-1,ans);
    }


    public int kFar(TreeNode node, TreeNode target, int K,List<Integer> ans) {
        if(node == null) return -1;

        if(node.val == target.val){
            kDown(node,null,K,ans);
            return 1;
        }


        int ld = kFar(node.left, target, K, ans);
        if(ld != -1){
            kDown(node,node.left,K - ld, ans);
            return ld + 1;
        }

        
        int rd = kFar(node.right, target, K, ans);
        if(rd != -1){
            kDown(node,node.right,K - rd, ans);
            return rd + 1;
        }

        return -1;
    }

    public List<Integer> distanceK(TreeNode node, TreeNode target, int K) {
        List<Integer> ans=new ArrayList<>();
        kFar(node,target,K,ans);
        return ans;
    }

    //Leetcode 236
    TreeNode LCANode = null;
    
    public boolean lowestCommonAncestor_(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return false;

        boolean selfDone = false;
        if(root == p || root == q) selfDone = true;

        boolean leftDone = lowestCommonAncestor_(root.left, p, q);
        if(LCANode!=null) return true;
        
        boolean rightDone = lowestCommonAncestor_(root.right, p, q);
        if(LCANode!=null) return true;

        if((leftDone && rightDone) ||(leftDone && selfDone) || (rightDone && selfDone)) LCANode = root;
        
        return selfDone || leftDone || rightDone;
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestor_(root,p,q);
        return LCANode;
    }

    //Leetcode 235.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        TreeNode curr = root;
        while(curr != null){

            if(p.val < curr.val && q.val < curr.val) curr = curr.left;
            else if (p.val > curr.val && q.val > curr.val) curr = curr.right;
            else return curr;
        }

        return null;
    }

    // Leetcode 173
    class BSTIterator {

        Stack<TreeNode> st=new Stack<>();

        public BSTIterator(TreeNode root) {
            pushAllNextElements(root);
        }

        public void pushAllNextElements(TreeNode node){
            while(node!=null){
                st.push(node);
                node = node.left;
            }
        }
        
        public int next() {
            TreeNode rv = st.pop();
            pushAllNextElements(rv.right);

            return rv.val;
        }
        
        public boolean hasNext() {
            return st.size() != 0;
        }
    }

    TreeNode A = null, B =null;
    TreeNode prev = null;
    public boolean recoverTree_(TreeNode root) {
        if(root == null) return false;

        if(recoverTree_(root.left)) return true;
        if(prev != null && prev.val > root.val){
            B = root;
            if(A == null) A = prev;
            else return true;
        }
        
        prev = root;
        if(recoverTree_(root.right)) return true;
        
        return false;
    }

    
    public void recoverTree(TreeNode root) {

        recoverTree_(root);
        if(A!=null){
            int temp = A.val;
            A.val = B.val;
            B.val = temp;
        }
    
    }
    
    //Leetcode 510.
    /*
    // Definition for a Node.
    class Node {
       public int val;
       public Node left;
       public Node right;
       public Node parent;
    };
    */

    public Node inorderSuccessor(Node node) {
        Node curr = node;
        Node succ = null;
        if(curr.right!=null){
            succ = curr.right;
            while(succ.left != null) succ=succ.left;
            
            return succ;
        }
        
        Node prev = null;
        while(curr.parent!=null){
            prev = curr;
            curr = curr.parent;
            if(curr.left == prev) return curr;
        }
        
        return succ;
    }

    
    //Leetcode 230
    int KthSmallestAns = -1;
    int Kth=0;
    public boolean kthSmallest_(TreeNode root) {
        if(root == null) return false;

        if(kthSmallest_(root.left)) return true;

        if(--kth == 0){
            KthSmallestAns = root.val;
            return true;
        }

        if(kthSmallest_(root.right)) return true;
        return false;
    }
    
    public int kthSmallest(TreeNode root, int k) {
        Kth = k;
        kthSmallest_(root);
        return KthSmallestAns;
    }

    public void pushAllNext(Stack<TreeNode> st,TreeNode node){
        while(node!=null){
            st.push(node);
            node = node.left;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> st = new Stack<>();
        pushAllNext(st,root);

        while(--k != 0){
            TreeNode rNode = st.pop();
            pushAllNext(st,rNode.right);
        }
        return st.peek().val;
    }

    //leetcode l05
    public TreeNode buildTree(int[] preorder,int psi,int pei, int[] inorder,int isi,int iei) {
        if(psi>pei) return null;
        int idx = isi;
        while(inorder[idx]!=preorder[psi]) idx++;
        int len = idx - isi;
        
        TreeNode node = new TreeNode(preorder[psi]);
        node.left = buildTree(preorder, psi+1,psi+len,inorder,isi,idx-1);
        node.right = buildTree(preorder, psi+len+1,pei,inorder,idx + 1, iei);

        return node;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0) return null;
        int n = preorder.length;

        return buildTree(preorder,0,n-1,inorder,0,n-1);
    }

    //Leetcode 106
    public TreeNode buildTree(int[] postorder,int psi,int pei, int[] inorder,int isi,int iei) {
        if(psi>pei) return null;
        int idx = isi;
        while(inorder[idx]!=postorder[pei]) idx++;
        int len = idx - isi;
            
        TreeNode node = new TreeNode(postorder[pei]);
        node.left = buildTree(postorder, psi,psi + len - 1,inorder,isi,idx-1);
        node.right = buildTree(postorder, psi + len,pei - 1,inorder,idx + 1, iei);
    
        return node;
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
            if(postorder.length==0) return null;
            int n = postorder.length;
    
            return buildTree(postorder,0,n-1,inorder,0,n-1);
    }

    //Leetcode 979
    
    int totalCoins = 0;
    public int distributeCoins_(TreeNode root) {
        if(root = null) return 0;
        int leftDefeGain = distributeCoins_(root.left);
        int rightDefeGain = distributeCoins_(root.right);

        totalCoins += Math.abs(leftDefeGain) + Math.abs(rightDefeGain);

        return root.val - 1 + leftDefeGain + rightDefeGain;
    }
    
    public int distributeCoins(TreeNode root) {
        if(distributeCoins_(root) != 0) return -1;
        return totalCoins;
    }

    //Leetcode 968
    // -1 : camera Required, 0 : camera , 1 : child already covered
    int cameras = 0;
    public int minCameraCover_(TreeNode root) {
        if(root == null) return 1;
        int lans = minCameraCover_(root.left);
        int rans = minCameraCover_(root.right);

        if(lans == -1 || rans == -1){
            cameras++;
            return 0;
        }

        if(land == 0 || rans == 0) return 1;

        return -1;
    }

    public int minCameraCover(TreeNode root) {
        if(root == null) return 0;

        if(minCameraCover_(root) == -1) cameras++;
        return cameras;
    }

    //337
      // [] - >  {store the max value without robbing current node, store the max value with robing current node}
      public int[] rob_(TreeNode node){
        if(node == null) return new int[]{0,0};
        
        int[] left = rob_(node.left);
        int[] right = rob_(node.right);
        
        int[] myAns = new int[2];
        myAns[0] = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);
        myAns[1] = left[0] + node.val + right[0];
        
        return myAns;
         
    }
    public int rob(TreeNode root) {
        int[] ans = rob_(root);
        return Math.max(ans[0],ans[1]);
    }

    public static Node midNode(Node node){

        Node slow = node,fast = node;
        while(fast.right!=null && fast.right.right!=null){
            slow = slow.right;
            fast = fast.right.right;
        }

        return slow;
    }

    // ---> right
    // <--- left
    public static Node BST(Node head,Node tail){
        if(head == tail) return head;

        Node mid = midNode(head);
        Node prev = mid.left;
        Node next = mid.right;
        if(prev != null) prev.right = null;
        if(next != null) next.left = null;
        
        mid.left = BST(head,prev);
        mid.right = BST(next,tail);

        return mid;
    }

    // 1325
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        
        if(root==null) return null;
        root.left = removeLeafNodes(root.left,target);
        root.right = removeLeafNodes(root.right,target);

        if(root.left == null && root.right==null && root.val == target) return null;
        return root;
    }

    //116
    public Node connect(Node node) {
        if(node == null) return null;
        
        if(node.left != null) node.left.next = node.right;
        if(node.right != null && node.next != null) node.right.next = node.next.left; 
        
        node.left = connect(node.left);
        node.right=connect(node.right);
        
        return node;
    }
    
    //116
    public Node connect(Node nnode) {
        if(nnode == null ) return nnode;
        Node curr = nnode;
        while(curr.left!=null){
            Node node = curr;
            while(node != null){
               
                node.left.next = node.right;
                if(node.next != null)node.right.next = node.next.left;
                
                node = node.next;
            }
            
            curr = curr.left;
        }
        
        return nnode;
    }

    // using HashMap
    public static Node createTree_HM_dfs1(Node node,HashMap<Node,Node> map){
        if(node == null) return null;

        Node nnode = new Node(node.data);
        map.put(node,nnode);
        nnode.left = createTree_HM_dfs1(node.left,map);
        nnode.right = createTree_HM_dfs1(node.right,map);

        return nnode;
    }

    public static void setRandom_HM_dfs1(Node node,HashMap<Node,Node> map){
        if(node == null) return;

        if(node.random!=null) map.get(node).random = map.get(node.random);
        setRandom_HM_dfs1(node.left,map);
        setRandom_HM_dfs1(node.right,map);
    }

    public static void cloneBT(Node node){
        HashMap<Node,Node> map = new HashMap<>();

        Node nroot = createTree_HM_dfs1(node,map);
        setRandom_HM_dfs1(node,map);

        return root;
    }

    // without using hashmap.

    public static Node cloneBT_WithoutRandom(Node root){
        if(root == null) return null;

        Node node = new Node(root.data);
        node.left = cloneBT_WithoutRandom(root.left);
        root.right = cloneBT_WithoutRandom(root.right);

        root.left = node;
        return root;
    }

    public static void setRandoms(Node root){
        if(root==null) return;

        if(root.random != null) root.left.random = root.random.left;

        setRandoms(root.left.left);
        setRandoms(root.right);
    }

    public static Node extractTree(Node root){
        Node lcNode = null;
        Node rcNode = null;
        Node cloneNode = root.left;

        if(root.left.left != null) lcNode = extractTree(root.left.left);
        if(root.right != null) rcNode = extractTree(root.right);

        root.left = cloneNode.left;
        cloneNode.left = lcNode;
        cloneNode.right = rcNode;

        return cloneNode;
    }

    public static void cloneBT(Node node){
        cloneBT_WithoutRandom(node);
        setRandoms(node);
        return extractTree(node);
    }    
}