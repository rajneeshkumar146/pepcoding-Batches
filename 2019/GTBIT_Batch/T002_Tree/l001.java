import java.util.ArrayList;
import java.util.HashMap;
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
    
    //863
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

    // https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/
    public static void BurnTree(TreeNode node,TreeNode block,int k,ArrayList<ArrayList<Integer>> ans){
        if(node == null || node == block) return;
        
        if(k == ans.size())
            ans.add(new ArrayList<>());
        
        ans.get(k).add(node.val);
        
        BurnTree(node.left,block,k+1,ans);
        BurnTree(node.right,block,k+1,ans);
    }

    public static int burningTree(TreeNode node,int tar){
        ArrayList<TreeNode> list = new ArrayList<>(); 
        rootToNodePath(root,tar,list);

        TreeNode block = null;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        for(int i=0;i<list.size();i++){
            BurnTree(list.get(i),block,i,ans);
            block = list.get(i);
        }
    }

    public static int burningTree2(TreeNode node,int tar,ArrayList<ArrayList<Integer>> ans){
        if(node == null) return -1;
        if(node.val == tar){
            BurnTree(node,null,0,ans);
            return 1;
        }

        int lt = burningTree2(node.left,tar,ans);
        if(lt != -1){
            BurnTree(node,node.left,lt,ans);
            return lt + 1;
        }

        int rt = burningTree2(node.right,tar,ans);
        if(rt != -1){
            BurnTree(node,node.right,lt,ans);
            return rt + 1;
        }

        return -1;
    }

    //BFS/LevelOrder.========================================================

    public static void bfs(TreeNode node,ArrayList<ArrayList<Integer>> ans){
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(node);
        int level = 0;

        while(que.size() != 0){
            int size = que.size();
            while(size-->0){
                Node rn = que.removeFirst();
                if(level == ans.size()) ans.add(new ArrayList<>());
                ans.get(level).add(rn.val);

                if(rn.left != null) que.add(rn.left);
                if(rn.right != null) que.add(rn.right);
            }

            level++;
        }
    }

    public static void leftView(TreeNode node){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(ArrayList<Integer> l : ans){
            System.out.println(l.get(0));
        }
    }

    public static void rightView(TreeNode node){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(ArrayList<Integer> l : ans){
            System.out.println(l.get(l.size() - 1));
        }
    }

    public static class verticalPair{
        TreeNode node = null;
        int Hlevel = 0;

        verticalPair(TreeNode node,int Hlevel){
            this.node = node;
            this.Hlevel  Hlevel;
        }
    }

    public static void verticalView(TreeNode root){
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        LinkedList<verticalPair> que = new LinkedList<>();
        que.add(new verticalPair(root,0));

        int minHLev = 0,maxHLev = 0;

        while(que.size() != 0){
            verticalPair rp = que.removeFirst();

            map.putIfAbsent(rp.Hlevel,new ArrayList<>());
            map.get(rp.Hlevel).add(rp.node.val);

            minHLev = Math.min(rp.Hlevel,minHLev);
            maxHLev = Math.max(rp.Hlevel,maxHLev);

            if(rp.node.left != null) que.add(new verticalPair(rp.node.left,rp.Hlevel - 1));
            if(rp.node.right != null) que.add(new verticalPair(rp.node.right,rp.Hlevel + 1));
        }

        for(int i = minHLev;i<=maxHLev;i++){
            System.out.println(map.get(i));
        }
    }

    public static void verticalSum(TreeNode root){
        LinkedList<verticalPair> que = new LinkedList<>();
        que.add(new verticalPair(root,0));

        int minHLev = 0,maxHLev = 0;

        while(que.size() != 0){
            verticalPair rp = que.removeFirst();

            map.put(rp.Hlevel,map.getOrDefault(rp.HLevel,0) + rp.node.val);

            minHLev = Math.min(rp.Hlevel,minHLev);
            maxHLev = Math.max(rp.Hlevel,maxHLev);

            if(rp.node.left != null) que.add(new verticalPair(rp.node.left,rp.Hlevel - 1));
            if(rp.node.right != null) que.add(new verticalPair(rp.node.right,rp.Hlevel + 1));
        }

        for(int i = minHLev;i<=maxHLev;i++){
            System.out.println(map.get(i));
        }
    }

    public static void BottomView(TreeNode root){
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        LinkedList<verticalPair> que = new LinkedList<>();
        que.add(new verticalPair(root,0));

        int minHLev = 0,maxHLev = 0;

        while(que.size() != 0){
            verticalPair rp = que.removeFirst();

            map.putIfAbsent(rp.Hlevel,new ArrayList<>());
            map.get(rp.Hlevel).add(rp.node.val);

            minHLev = Math.min(rp.Hlevel,minHLev);
            maxHLev = Math.max(rp.Hlevel,maxHLev);

            if(rp.node.left != null) que.add(new verticalPair(rp.node.left,rp.Hlevel - 1));
            if(rp.node.right != null) que.add(new verticalPair(rp.node.right,rp.Hlevel + 1));
        }

        for(int i = minHLev;i<=maxHLev;i++){
            ArrayList<Integer> list = map.get(i);
            System.out.println(list.get(list.size() - 1));
        }
    }

    public static void TopView(TreeNode root){
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        LinkedList<verticalPair> que = new LinkedList<>();
        que.add(new verticalPair(root,0));

        int minHLev = 0,maxHLev = 0;

        while(que.size() != 0){
            verticalPair rp = que.removeFirst();

            map.putIfAbsent(rp.Hlevel,new ArrayList<>());
            map.get(rp.Hlevel).add(rp.node.val);

            minHLev = Math.min(rp.Hlevel,minHLev);
            maxHLev = Math.max(rp.Hlevel,maxHLev);

            if(rp.node.left != null) que.add(new verticalPair(rp.node.left,rp.Hlevel - 1));
            if(rp.node.right != null) que.add(new verticalPair(rp.node.right,rp.Hlevel + 1));
        }

        for(int i = minHLev;i<=maxHLev;i++){
            ArrayList<Integer> list = map.get(i);
            System.out.println(list.get(0));
        }
    }

    //BounadryTraversal.
    public static void leftBounadry(TreeNode node,ArrayList<Integer> ans){
        if(node == null || (node.left == null && node.right == null)) return;

        ans.add(node.val);
        if(node.left != null) leftBounadry(node.left,ans);
        else leftBounadry(node.right,ans);
    }

    public static void BottomBounadry(TreeNode node,ArrayList<Integer> ans){
        if(node == null) return;

        BottomBounadry(node.left,ans);
        if(node.left == null && node.right == null) ans.add(node.val);
        BottomBounadry(node.right,ans);
    }

    public static void rightBounadry(TreeNode node,ArrayList<Integer> ans){
        if(node == null || (node.left == null && node.right == null)) return;

        if(node.right != null) rightBounadry(node.right,ans);
        ans.add(node.val);
        else rightBounadry(node.left,ans);
    }

    public static void boundaryTraveral(TreeNode node){
        if(node==null) return;

        ArrayList<Integer> ans = new ArrayList<>();
        leftBounadry(node,ans);
        BottomBounadry(node,ans);
        rightBounadry(node,ans);

        ans.remove(ans.size() - 1);
    }

    //968
    // -1 : need, 0 : camera, 1 : covered
    int cameraCount = 0;
    public int minCameraCover_(TreeNode root) {
        if(root == null) return 1;

        int lr = minCameraCover_(root.left);
        int rr = minCameraCover_(root.right);
         
        if(lr == -1 || rr == -1){
            cameraCount++;
            return 0;
        }

        if(lr == 0 || rr == 0){
            return 1;
        }

        return -1;
    }

    public int minCameraCover(TreeNode root) {
        if(root == null) return 0;
        if(minCameraCover_(root) == -1) cameraCount++;
        return cameraCount;
    }

    // diameter
    public static void diameter_01(TreeNode node){
        if(node == null) return 0;

        int ld = diameter_01(node.left);
        int rd = diameter_01(node.right);

        int lh = height(node.left);
        int rh = height(node.right);

        return Math.max(Math.max(ld,rd),lh + rh + 2);
    }

    // [dia, height]
    public static int[] diameter_02(TreeNode node){
        if(node == null) return new int[]{0.-1};

        int[] lr = diameter_02(node.left);
        int[] rr = diameter_02(node.right);

        int dia = Math.max(Math.max(lr[0],rr[0]), lr[1] + rr[1] + 2);
        int hei = Math.max(lr[1],rr[1]) + 1;

        return new int[]{dia,hei};
    }

    static int diaMax = 0;
    public static int diameter_03(TreeNode node){
        if(node == null) return -1;

        int lh = diameter_03(node.left);
        int rh = diameter_03(node.right);

        diaMax = Math.max(diaMax, lh + rh + 2);
        return Math.max(lh,rh) + 1;
    }

    // https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/

    static int maxLeafToLeaf = -(int)1e8;
    public static int leafToLeafSum(TreeNode node){
        if(node == null) return -(int) 1e8;
        if(node.left == null && node.right == null) return node.val;

        int lres = leafToLeafSum(node.left);
        int rres = leafToLeafSum(node.right);

        if(node.left != null && node.right != null)
           maxLeafToLeaf = Math.max(maxLeafToLeaf, lres + rres + node.val);

        return Math.max(lres,rres) + node.val;
    }

    public static int maxPathSum(Node root)
    { 
        leafToLeafSum(root);
        return maxLeafToLeaf;
    }

    //124
    int maxNodeToNode = -(int)1e8;
    public int maxPathSum_(TreeNode root) {
        if(root==null) return -(int)1e8;
        int lres = maxPathSum_(root.left);
        int rres = maxPathSum_(root.right);
        
        int maxLR = Math.max(lres,rres) + root.val;
        maxNodeToNode = Math.max(Math.max(root.val,lres + rres + root.val),Math.max(maxLR,maxNodeToNode));
        
        return Math.max(maxLR,root.val);
    }
    
    public int maxPathSum(TreeNode root) {
        maxPathSum_(root);
        return maxNodeToNode;
    }

    public static void zigZagTraversal(TreeNode node){
        LinkedList<TreeNode> que = new LinkedList<>();
        LinkedList<TreeNode> st = new LinkedList<>();
        
        que.addLast(node);
        int level = 0;

        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                TreeNode rnode = que.removeFirst();
                System.out.print(rnode.val + " ")
                if(level % 2 == 0){
                    if(rnode.left != null) st.addFirst(rnode.left);
                    if(rnode.right != null) st.addFirst(rnode.right);
                }else{
                    if(rnode.right != null) st.addFirst(rnode.right);
                    if(rnode.left != null) st.addFirst(rnode.left);
                }
            }
            
            LinkedList<TreeNode> temp = que;
            que= st;
            st = temp;

            level++; 
        }
    }

    //Leetcode 116 (Next Right Pointer)
    public static void nextRightPointer(TreeNode node){
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(node);
        
        while(que.size()!=0){
            int size = que.size();
            
            while(size-->0){
                TreeNode rnode = que.removeFirst();
                rnode.right = que.size() > 0 ? que.getFirst() : null;
                if(rnode.left != null) que.addFirst(rnode.left);
                if(rnode.right != null) que.addFirst(rnode.right);
            }
        }
    }

    //Leetcode 116 (Next Right Pointer)
    public static void nextRightPointer(TreeNode node){
        if(node == null) return;

        node.left = node.right;
        if(node.right!= null) node.right = if(node.next != null) node.next.left;

        nextRightPointer(node.left);
        nextRightPointer(node.right);
    }

}