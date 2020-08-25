import java.util.LinkedList;
import java.util.ArrayList;
public class l001{

    public static void main(String[] args){
        solve();
    }

    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data){
            this.data = data;
        }
    }

    //Basic.==================================================================================

    static int idx = 0;
    public static Node constructTree(int[] arr){
        if(idx==arr.length || arr[idx]==-1){
            idx++;
            return null;
        }

        Node node=new Node(arr[idx++]);

        node.left = constructTree(arr);
        node.right = constructTree(arr);

        return node;
    }

    public static void display(Node node){
        if(node==null) return;
        StringBuilder sb = new StringBuilder();
        
        sb.append(node.left!=null?node.left.data+"" : ".");
        sb.append(" <- " + node.data + " -> ");
        sb.append(node.right!=null?node.right.data+"" : ".");

        System.out.println(sb.toString());

        display(node.left);
        display(node.right);
    }

    public static int size(Node node){
        return node==null? 0 : size(node.left) + size(node.right) + 1; 
    }

    public static int height(Node node){
        return node==null? -1 : Math.max(height(node.left) , height(node.right)) + 1; 
    }

    public static void preOrder(Node node){
        if(node == null) return ;

        System.out.print(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void InOrder(Node node){
        if(node == null) return ;

        InOrder(node.left);
        System.out.print(node.data);
        InOrder(node.right);
    }

    public static void postOrder(Node node){
        if(node == null) return ;

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data);
    }

    // Set1.===========================================================================

    public static int diameter(Node node){
        if(node==null) return 0;

        int ld = diameter(node.left);
        int rd = diameter(node.right);
        
        int lh = height(node.left);
        int rh = height(node.right);

        return Math.max(Math.max(ld,rd),lh+rh+2);
    }
    
    // d, h
    public static int[] diameter_02(Node node){
        if(node==null) return new int[]{0,-1};
        
        int[] lr = diameter_02(node.left);   // left result
        int[] rr = diameter_02(node.right);  // right result

        int dia = Math.max(Math.max(lr[0],rr[0]), lr[1] + rr[1] + 2);
        int height = Math.max(lr[1],rr[1]) + 1;
       
        return new int[]{dia , height};
    }

    
    public static int diameter_03(Node node,int[] res){
        if(node==null) return -1;
        
        int lh = diameter_03(node.left,res);   // left result
        int rh = diameter_03(node.right,res);  // right result

        res[0] = Math.max(res[0],lh + rh + 2);

        return Math.max(lh,rh) + 1;
    }

    public static void diameter(){
        int[] res=new int[1];
        
    }

    // https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/

    public int LeafToLeafAns = -(int)1e8;
    public int leafToLeafSum_(Node node){
        if(node == null) return -(int)1e8;
        if(node.left == null && node.right == null) return node.data;

        int lMax = leafToLeafSum_(node.left); 
        int rMax = leafToLeafSum_(node.right);
        
        if(node.left != null && node.right != null){
            LeafToLeafAns = Math.max(LeafToLeafAns, lMax + node.data +rMax);
        }

        return Math.max(lMax,rMax) + node.data;
    }

    int maxPathSum(Node root)
    { 
        leafToLeafSum_(root);
        return LeafToLeafAns;
    } 

    public static void width(Node node,int level,int[] minMax){
        if(node==null) return;

        minMax[0] = Math.min(minMax[0],level);
        minMax[1] = Math.max(minMax[1],level);

        width(node.left, level - 1, minMax);
        width(node.right, level + 1, minMax);
    }

    public static void kdown(Node node,Node block,int k){
        if(node == null || node == block || k < 0) return;

        if(k == 0){
            System.out.print(node.data+" ");
            return;
        }

        kdown(node.left,block, k - 1);
        kdown(node.right,block, k - 1);
    }

    public static boolean rootToNodePath(Node node,int data,ArrayList<Node> path){

        if(node==null) return false;

        if(node.data == data){
            path.add(node);
            return true;
        }

        boolean res = false;
        res = res || rootToNodePath(node.left,data,path);
        res = res || rootToNodePath(node.right,data,path);

        if(res) {
            path.add(node);
            return true;
        }

        return false;
    }

    public static void kFar(Node node,int tar,int k){
        ArrayList<Node> path = new ArrayList<>();
        rootToNodePath(node,tar,path);

        Node prev = null;
        for(int i=0;i<path.size();i++){
            kdown(path.get(i),prev,k-i);
            prev= path.get(i);
        }
    }

    public static int kFar_better(Node node,int data,int k){
        if(node==null) return -1;

        if(node.data == data){
            kdown(node,null,k);
            return 1;
        }

        int ld = kFar_better(node.left, data, k);
        if(ld != -1){
            kdown(node,node.left, k - ld);
            return ld + 1;
        }

        int rd = kFar_better(node.right, data, k);
        if(rd != -1){
            kdown(node,node.right, k - rd);
            return rd + 1;
        }

        return -1;
    }

    // https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/

    public static void BurningNodes(Node node,int time,ArrayList<ArrayList<Integer>> ans){
        if(node == null) return;

        if(time == ans.size()) ans.add(new ArrayList<>());
        ans.get(time).add(node.data);

        BurningNodes(node.left, time + 1, ans);
        BurningNodes(node.right, time + 1, ans);
    }

    public static int burningOfTree_(Node node,int data,ArrayList<ArrayList<Integer>> ans)
    {
        if(node==null) return -1;

        if(node.data == data){
            BurningNodes(node,0,ans);
            return 1;
        }

        int ld = burningOfTree_(node.left, data, ans);
        if(ld != -1){
        
            if(ld == ans.size()) ans.add(new ArrayList<>());    
            ans.get(ld).add(node.data);
            BurningNodes(node.right,ld + 1,ans);
            return ld + 1;
        
        }

        int rd = burningOfTree_(node.right, data, ans);
        if(rd != -1){

            if(rd == ans.size()) ans.add(new ArrayList<>());    
            ans.get(rd).add(node.data);
            BurningNodes(node.left,rd + 1,ans);
            return rd + 1;
        
        }

        return -1;
    }

    public static Node lowestCommonAncestor(Node root,int p, int q) {

        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();
        
        rootToNodePath(root,p,path1);
        rootToNodePath(root,q,path2);

        int i = path1.size()-1;
        int j = path2.size()-1;

        Node LCA = null;

        while(i >= 0 && j >= 0){
            if(path1.get(i)!=path2.get(j)) break;
            
            LCA = path1.get(i);
            i--;
            j--;
        }

        return LCA;
    }

    //LevelOrder.==================================================

    public static void BFS_01(Node node){
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);

        while(que.size()!=0){
            Node rvtx = que.removeFirst();
            System.out.print(rvtx.data+" ");

            if(rvtx.left!=null) que.addLast(rvtx.left);
            if(rvtx.right!=null) que.addLast(rvtx.right);
        }
    }

    public static void BFS_02(Node node){
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);
        que.addLast(null);

        while(que.size() != 1){
            Node rvtx = que.removeFirst();
            System.out.print(rvtx.data+" ");

            if(rvtx.left!=null) que.addLast(rvtx.left);
            if(rvtx.right!=null) que.addLast(rvtx.right);

            if(que.getFirst() == null){
                System.out.println();
                que.removeFirst();
                que.addLast(null);
            }
        }
    }

    public static void BFS_03(Node node){
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);
        
        int level = 0;
        while(que.size()!=0){
            int size = que.size();
            System.out.print("Level " + level + " : ");
            while(size-->0){
                Node rvtx = que.removeFirst();
                System.out.print(rvtx.data+" ");

                if(rvtx.left!=null) que.addLast(rvtx.left);
                if(rvtx.right!=null) que.addLast(rvtx.right);
            }

            level++;
            System.out.println();
        }
    }

    public static void zigzag(Node node){
        LinkedList<Node> st1 = new LinkedList<>();
        LinkedList<Node> st2 = new LinkedList<>();

        st1.addFirst(node);
        int level=0;

        while(st1.size()!=0){
            int size = st1.size();
            while(size-->0){
                Node rvtx = st1.removeFirst();
                System.out.print(rvtx.data+" ");

                if((level & 1) == 0){
                    if(rvtx.left!=null) st2.addFirst(rvtx.left);
                    if(rvtx.right!=null) st2.addFirst(rvtx.right);
                }else{
                    if(rvtx.right!=null) st2.addFirst(rvtx.right);
                    if(rvtx.left!=null) st2.addFirst(rvtx.left);   
                }
            }

            LinkedList<Node> temp = st1;
            st1 = st2;
            st2 = temp;

            System.out.println();
            level++;
        }

    }


    //View_Set.=============================================================

    public static void leftView(Node node){
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);
        
        while(que.size()!=0){
            int size = que.size();
            System.out.print(que.getFirst().data+" ");
            while(size-->0){
                Node rvtx = que.removeFirst();

                if(rvtx.left!=null) que.addLast(rvtx.left);
                if(rvtx.right!=null) que.addLast(rvtx.right);
            }
        }
    }

    public static void rightView(Node node){
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);
        
        while(que.size()!=0){
            int size = que.size();
            Node prev = null;
            while(size-->0){
                Node rvtx = que.removeFirst();
                prev = rvtx;

                if(rvtx.left!=null) que.addLast(rvtx.left);
                if(rvtx.right!=null) que.addLast(rvtx.right);
            }
            System.out.print(prev.data+" ");
        }
    }

    public static class vPair{
        Node node = null;
        int level = 0;

        public vPair(Node node,int level){
            this.node = node;
            this.level = level;
        }
    }

    public static void verticalOrder(Node root){

        int[] minMax = new int[2];
        width(root,0,minMax);

        ArrayList<Integer>[] ans = new ArrayList[minMax[1] - minMax[0] + 1];
        for(int i = 0; i < ans.length; i++) ans[i] = new ArrayList<>();
        
        LinkedList<vPair> que = new LinkedList<>();
        que.addLast(new vPair(root,Math.abs(minMax[0])));

        while(que.size() != 0){
            int size = que.size();
            while(size-->0){

                vPair rvtx = que.removeFirst();
                Node node = rvtx.node;
                int level = rvtx.level;

                ans[level].add(node.data);

                if(node.left!=null) que.addLast(new vPair(node.left,level - 1));
                if(node.right!=null) que.addLast(new vPair(node.right,level + 1));
            }
        }
    }

    public static void verticalOrderSum(Node root){

        int[] minMax = new int[2];
        width(root,0,minMax);

        int[] ans = new int[minMax[1] - minMax[0] + 1];
        
        LinkedList<vPair> que = new LinkedList<>();
        que.addLast(new vPair(root,Math.abs(minMax[0])));

        while(que.size() != 0){
            int size = que.size();
            while(size-->0){

                vPair rvtx = que.removeFirst();
                Node node = rvtx.node;
                int level = rvtx.level;

                ans[level]+=node.data;

                if(node.left!=null) que.addLast(new vPair(node.left,level - 1));
                if(node.right!=null) que.addLast(new vPair(node.right,level + 1));
            }
        }
    }

    public static void BottomView_RightPrefer(Node root){
        int[] minMax = new int[2];
        width(root,0,minMax);

        int[] ans = new int[minMax[1] - minMax[0] + 1];
        
        LinkedList<vPair> que = new LinkedList<>();
        que.addLast(new vPair(root,Math.abs(minMax[0])));

        while(que.size() != 0){
            int size = que.size();
            while(size-->0){

                vPair rvtx = que.removeFirst();
                Node node = rvtx.node;
                int level = rvtx.level;

                ans[level] =node.data;

                if(node.left!=null) que.addLast(new vPair(node.left,level - 1));
                if(node.right!=null) que.addLast(new vPair(node.right,level + 1));
            }
        }

        for(int ele : ans) System.out.print(ele + " ");
        System.out.println();
    }

    public static class bPair{
        Node node = null;
        int level = 0;
        int height = 0;

        public bPair(Node node,int level,int height){
            this.node = node;
            this.level = level;
            this.height = height;
        }
    }

    public static void BottomView_LeftPrefer(Node root){
        int[] minMax = new int[2];
        width(root,0,minMax);

        bPair[] ans = new bPair[minMax[1] - minMax[0] + 1];
        
        LinkedList<bPair> que = new LinkedList<>();
        que.addLast(new bPair(root,Math.abs(minMax[0]),0));

        while(que.size() != 0){
            int size = que.size();
            while(size-->0){

                bPair rvtx = que.removeFirst();
                Node node = rvtx.node;
                int level = rvtx.level;
                int height = rvtx.height;

                if(ans[level] == null) ans[level] = rvtx;
                else if(height > ans[level].height) ans[level] = rvtx;


                if(node.left!=null) que.addLast(new bPair(node.left,level - 1,height + 1));
                if(node.right!=null) que.addLast(new bPair(node.right,level + 1,height + 1));
            }
        }

        for(bPair ele : ans) System.out.print(ele.node.data + " ");
        System.out.println();
    }

    public static void TopView(Node root){
        int[] minMax = new int[2];
        width(root,0,minMax);

        Node[] ans = new Node[minMax[1] - minMax[0] + 1];
        
        LinkedList<vPair> que = new LinkedList<>();
        que.addLast(new vPair(root,Math.abs(minMax[0])));

        while(que.size() != 0){
            int size = que.size();
            while(size-->0){

                vPair rvtx = que.removeFirst();
                Node node = rvtx.node;
                int level = rvtx.level;

                if(ans[level] == null)
                  ans[level] = node;

                if(node.left!=null) que.addLast(new vPair(node.left,level - 1));
                if(node.right!=null) que.addLast(new vPair(node.right,level + 1));
            }
        }

        for(Node ele : ans) System.out.print(ele.data + " ");
        System.out.println();
    }

    public static void diagonalOrder(Node root){

        int[] minMax = new int[2];
        width(root,0,minMax);

        ArrayList<Integer>[] ans = new ArrayList[0 - minMax[0] + 1];
        for(int i = 0; i < ans.length; i++) ans[i] = new ArrayList<>();
        
        LinkedList<vPair> que = new LinkedList<>();
        que.addLast(new vPair(root,Math.abs(minMax[0])));

        while(que.size() != 0){
            int size = que.size();
            while(size-->0){

                vPair rvtx = que.removeFirst();
                Node node = rvtx.node;
                int level = rvtx.level;

                ans[level].add(node.data);

                if(node.left!=null) que.addLast(new vPair(node.left,level - 1));
                if(node.right!=null) que.addLast(new vPair(node.right,level));
            }
        }
    }

    static Node head = null, prev = null;
    public static void bTreeToClist_(Node root){
        if(root==null) return;


        bTreeToClist_(root.left);
        
        if(head == null) head = root;
        else{
            root.left = prev;
            prev.right = root;
        }

        prev = root;
        bTreeToClist_(root.right);
    }

    public static Node bTreeToClist(Node root){

        bTreeToClist_(root);
        prev.right = head;
        head.left = prev;
        return head;
    }
    
    public static void BFS(Node node){
        // BFS_01(node);
        // BFS_02(node);
        // BFS_03(node);
        // zigzag(node);


        BottomView_RightPrefer(node);
        BottomView_LeftPrefer(node);
    }

    public static class allSolutionPair{
        int height = 0;
        int size = 0;
        boolean find = false;

        int ceil  = (int)1e8;
        int floor = -(int)1e8;

        Node pred=null, Succ = null, Prev = null;
    }

    public static void allSolutions(Node node,int level,int data,allSolutionPair pair){
        if(node == null) return;

        pair.height = Math.max(pair.height,level);
        pair.size++; 
        pair.find = pair.find || node.data == data;

        if(node.data > data) pair.ceil = Math.min(pair.ceil, node.data);

        if(node.data < data) pair.floor = Math.max(pair.floor, node.data);
        
        if(node.data == data) pair.pred = prev;
        if(pair.prev!=null && pair.prev.data == data) pair.succ = node;
        pair.prev = node;

        allSolutions(node.left,level+1,data,pair);
        allSolutions(node.right,level+1,data,pair);
    }

    //Traversal.=============================================================

    public static class tPair{
        Node node = null;
        boolean selfDone = false;
        boolean leftDone = false;
        boolean rightDone = false;
        
        tpair(Node node,boolean selfDone,boolean leftDone, boolean rightDone){
            this.node = node;
            this.selfDone = selfDone;
            this.leftDone = leftDone;
            this.rightDone = rightDone;
        }


        // char state = '';
        // // state: a = selfDone, b = leftDone, c = rightDone.
        // tpair(Node node,char state){
        //     this.node = node;
        //     this.state = state;
        // }
    }

    public static void traversal(Node node){
        Stack<tPair> st = new Stack<>();
        st.push(tPair(node,false,false,false));
        while(st.size()!=0){
            tapir rvtx = st.peek();

            if(!rvtx.leftDone){
                rvtx.leftDone = true;
                if(rvtx.node.left != null) st.push(new tPair(rvtx.node.left,false,false,false));
            }else if(!rvtx.rightDone){
                rvtx.rightDone = true;
                if(rvtx.node.right != null) st.push(new tPair(rvtx.node.right,false,false,false));
            }else if(!rvtx.selfDone){
                rvtx.selfDone = true;
                System.out.println(rvtx.node.data + " ");
            }else{
                st.pop();
            }
        }
    }

    public static void rightMost(Node node,Node curr){
        while(node.right!=null && node.right != curr) node=node.right;
        return node;
    }

    public static void morrisINTraversal(Node node){

        Node curr = node;
        while(curr!=null){
            Node next = curr.left;
            if(next == null){
                System.out.print(curr.data+" ");
                curr = curr.right;
            }else{
                Node rightMost = rightMost(next,curr);
                if(rightMost.right == null){
                    rightMost.right = curr;
                    curr = curr.left;
                }else{
                    System.out.print(curr.data+" ");
                    rightMost.right = null;
                    curr = curr.right;
                }
            }
        }
    }

    public static void morrisPreTraversal(Node node){

        Node curr = node;
        while(curr!=null){
            Node next = curr.left;
            if(next == null){
                System.out.print(curr.data+" ");
                curr = curr.right;
            }else{

                Node rightMost = rightMost(next,curr);
                if(rightMost.right == null){
                    System.out.print(curr.data+" ");
                    rightMost.right = curr;
                    curr = curr.left;
                }else{
                    rightMost.right = null;
                    curr = curr.right;
                }
            }
        }
    }

    static Node LeafHead = null, LeafPrev = null;
    public static void connectLeafs(Node node){
        if(node == null) return;

        connectLeafs(node.left);

        if(node.left == null && node.right == null){
            if(LeafHead == null) LeafHead = node;
            else{
                LeafPrev.right = node;
                node.left = LeafPrev;
            }

            LeafPrev = node;
        }

        connectLeafs(node.right);
    }





    public static void solve(){
        int[] arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
        Node root = constructTree(arr);
        display(root);

        BFS(root);
    }
}