public class l001{

    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data){
            this.data = data;
        }
    }

    static int idx = 0;
    public static Node constructTree(int[] arr){
        if(idx >= arr.length || arr[idx] == -1){
            idx++;
            return null;
        }


        Node node = new Node(arr[idx++]);
        node.left = constructTree(arr);
        node.right = constructTree(arr);

        return node;
    }

    public static void display(Node node){
        if(node==null) return;

        StringBuilder sb = new StringBuilder();
        sb.append((node.left == null ? "." : node.left.data + ""));
        sb.append(" <- " + node.data + " -> ");
        sb.append((node.right == null ? "." : node.right.data + ""));
        System.out.println(sb);


        display(node.left);
        display(node.right);
    }

    public static int size(Node node){
        return node==null?0:size(node.left) + size(node.right) + 1;
    }

    public static int height(Node node){
        return node==null ? -1 : Math.max(height(node.left,node.right)) + 1;
    }

    public static boolean find(Node node,int data){
        if(node==null) return false;

        if(node.data == data) return true;

        return find(node.left,data) || find(node.right,data);
    }

    public static boolean nodeToRootPath(Node node,int data,ArrayList<Node> ans){
        if(node==null) return false;
        
        if(node.data == data){
            ans.add(node);
            return true;
        }
        
        boolean res = nodeToRootPath(node.left,data,ans) || nodeToRootPath(node.right,data,ans);
        if(res){
            ans.add(node);
        }
        
        return res;
    }

    public static boolean rootToNodePath(Node node,int data,ArrayList<Node> ans){
        if(node==null) return false;
        
        if(node.data == data){
            ans.add(node);
            return true;
        }

        ans.add(node);
        boolean res = nodeToRootPath(node.left,data,ans) || nodeToRootPath(node.right,data,ans);
        if(!res){
            ans.remove(ans.size()-1);
        }
        
        return res;
    }

    public static ArrayList<Node> nodeToRootPath(Node node,int data){
        if(node==null) return null;

        if(node.data == data){
            ArrayList<Node> base = new ArrayList<>();
            base.add(node);
            return base;
        }

        ArrayList<Node> left = nodeToRootPath(node.left,data);
        if(left!=null){
            left.add(node);
            return left;
        }

        ArrayList<Node> right = nodeToRootPath(node.right,data);
        if(right!=null){
            right.add(node);
            return right;
        }

        return null;
    }

    public static void LCA(Node node,int a,int b){
        ArrayList<Node> list1 = new ArrayList<>();
        ArrayList<Node> list2 = new ArrayList<>();
        nodeToRootPath(node,a,list1);
        nodeToRootPath(node,b,list2);

        int i = list1.size()-1;
        int j = list2.size()-1;

        Node LCA = null;
        while(i>=0 && j>=0){
            if(list1.get(i)==list2.get(j)) LCA = list1.get(i);

            i--;
            j--;
        }

        System.out.println(LCA.data);
    }

    public static void kdown(Node node,Node block,int k,ArrayList<Integer> ans){
        if(node==null || node == block || k < 0) return;

        if(k==0){
            ans.add(node.data);
            return;
        }

        kdown(node.left,block,k-1,ans);
        kdown(node.right,block,k-1,ans);
    }

    public static void kFar(Node node,int data,int k){
       ArrayList<Node> list = new ArrayList<>();
       nodeToRootPath(node,data,list);

       ArrayList<Inreger> ans = new ArrayList<>();
       Node prev = null;
       for(int i=0;i<list.size();i++){
          kdown(list.get(i),prev,k-i,ans);
          prev = list.get(i);
       }
    }

    public static int kFar2(Node node,int data,int k,ArrayList<Integer> ans){
        if(node == null) return -1;

        if(node.data == data){
            kdown(node,null,k,ans);
            return 1;
        }

        int ld = kFar2(node.left, data, k, ans);
        if(ld != -1){
            kdown(node,node.left,k-ld,ans);
            return ld + 1;
        }

        int rd = kFar2(node.right, data, k, ans);
        if(rd != -1){
            kdown(node,node.right,k-rd,ans);
            return rd + 1;
        }

        return -1;
    }

    public static void kFar2(Node node,int data,int k){
        
    }

    public static int diameter_01(Node node){
        if(node == null) return 0;

        int ld = diameter_01(node.left);
        int rd = diameter_01(node.right);

        int lh = height(node.left);
        int rh = height(node.right);

        return Math.max(Math.max(ld,rd),lh + rh + 2);
    }

    public static int[] diameter_02(Node node){
        if(node == null) return new int[]{0,-1};   // {dia, height}

        int[] lres = diameter_02(node.left);
        int[] rres = diameter_02(node.left);
        
        int dia = Math.max(Math.max(lres[0],rres[0]),lres[1] + rres[1] + 2);
        int hei = Math.max(lres[1],rres[1]) + 1;
        
        return new int[]{dia,hei};
    }

    // static int diaAns = 0;
    public static int diameter_03(Node node,int[] diaAns){
        if(node == null) return -1;

        int lh = diameter_03(node.left,diaAns);
        int rh = diameter_03(node.right,diaAns);
        
        diaAns[0] = Math.max(diaAns[0],lh + rh + 2);
        return Math.max(lh,rh) + 1;
    }

     //BFS/LevelOrder.===================================================

     public static void BFS_01(Node node){
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);

        while(que.size()!=0){
            Node vtx = que.removeFirst();
            System.out.print(vtx.data + " ");
            if(vtx.left != null) que.addLast(vtx.left);
            if(vtx.right != null) que.addLast(vtx.right);
        }
    }

    public static void BFS_02(Node node){
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);
        que.addLast(null);

        while(que.size()!=1){
            Node vtx = que.removeFirst();
            
            System.out.print(vtx.data + " ");
        
            if(vtx.left != null) que.addLast(vtx.left);
            if(vtx.right != null) que.addLast(vtx.right);
            
            if(que.getFirst()==null){
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
            System.out.print("Level : " + level + " -> ")
            while(size-- > 0){
                Node vtx = que.removeFirst();
                System.out.print(vtx.data + " ");
                if(vtx.left != null) que.addLast(vtx.left);
                if(vtx.right != null) que.addLast(vtx.right);
            }

            System.out.println();
            level++;
        }
    }

    public static void width(Node node,int level, int[] maxMin){
        if(node == null) return;

        maxMin[0] = Math.max(maxMin[0],level);
        maxMin[1] = Math.min(maxMin[1],level);
        
        width(node.left, level-1,maxMin);
        width(node.right, level+1,maxMin);
    }

    public static class pair{

        Node node = null;
        int val = 0;

        pair(Node node,int val){
            this.node = node;
            this.val = val;
        }
    }

    public static List<Integer> leftView(Node node){
        LinkedList<pair> que = new LinkedList<>();
        que.addLast(new pair(node,0));

        int level = 0;
        ArrayList<pair> ans = new ArrayList<>();

        while(que.size()!=0){
            int size = que.size();
            while(size-- > 0){
                pair vtx = que.removeFirst();
                
                if(level == ans.size()) ans.add(vtx);   
                else if(vtx.val < ans.get(level).val) ans.set(level,vtx);

                if(vtx.node.left != null) que.addLast(new pair(vtx.node.left,vtx.val - 1));
                if(vtx.node.right != null) que.addLast(new pair(vtx.node.right,vtx.val + 1));
            
            }

            System.out.println();
            level++;
        }
    }

    
    public static List<Integer> leftViewInterview(Node node){
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);

        ArrayList<Integer> ans = new ArrayList<>();

        while(que.size()!=0){
            int size = que.size();
            ans.add(que.getFirst.data);
            while(size-- > 0){
                Node vtx = que.removeFirst();
                
                if(vtx.left != null) que.addLast(vtx.left);
                if(vtx.right != null) que.addLast(vtx.right);            
            }
        }

        return ans;
    }

    public static List<Integer> rightView(Node node){
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);

        ArrayList<Integer> ans = new ArrayList<>();

        while(que.size()!=0){
            int size = que.size();
            int prev = -1;
            while(size-- > 0){
                Node vtx = que.removeFirst();
                
                if(vtx.left != null) que.addLast(vtx.left);
                if(vtx.right != null) que.addLast(vtx.right);            
                
                prev = vtx.data;
            }
            ans.add(prev);
        }

        return ans;
    }

    public static void verticalOrderTraversal(Node node){
        int[] maxMin = new int[2];
        width(node,0,maxMin);

        int n = maxMin[0] - maxMin[1] + 1;
        ArrayList<Inreger>[] ans = new ArrayList[n];
        for(int i=0;i<n;i++) ans[i] = new ArrayList<>();

        LinkedList<pair> que = new LinkedList;
        que.addLast(new pair(node,-maxMin[1]));

        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                pair vtx = que.removeFirst();

                ans[vtx.val].add(ans.node.data);
                if(vtx.node.left!=null) que.addLast(new pair(vtx.node.left,vtx.val - 1));
                if(vtx.node.right!=null) que.addLast(new pair(vtx.node.right,vtx.val + 1));
            }
        }
    }

    public static void verticalSum(Node node){
        int[] maxMin = new int[2];
        width(node,0,maxMin);

        int n = maxMin[0] - maxMin[1] + 1;
        int[] ans = new int[n];
        
        LinkedList<pair> que = new LinkedList;
        que.addLast(new pair(node,-maxMin[1]));

        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                pair vtx = que.removeFirst();

                ans[vtx.val]+=ans.node.data;

                if(vtx.node.left!=null) que.addLast(new pair(vtx.node.left,vtx.val - 1));
                if(vtx.node.right!=null) que.addLast(new pair(vtx.node.right,vtx.val + 1));
            }
        }
    }

    
    public static void bottomSum(Node node){
        int[] maxMin = new int[2];
        width(node,0,maxMin);

        int n = maxMin[0] - maxMin[1] + 1;
        int[] ans = new int[n];
        
        LinkedList<pair> que = new LinkedList;
        que.addLast(new pair(node,-maxMin[1]));

        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                pair vtx = que.removeFirst();

                ans[vtx.val] = ans.node.data;
                
                if(vtx.node.left!=null) que.addLast(new pair(vtx.node.left,vtx.val - 1));
                if(vtx.node.right!=null) que.addLast(new pair(vtx.node.right,vtx.val + 1));
            }
        }
    }

    
    public static void topSum(Node node){
        int[] maxMin = new int[2];
        width(node,0,maxMin);

        int n = maxMin[0] - maxMin[1] + 1;
        Integer[] ans = new Integer[n];
        
        LinkedList<pair> que = new LinkedList;
        que.addLast(new pair(node,-maxMin[1]));

        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                pair vtx = que.removeFirst();
 
                if(ans[vtx.val]==null)
                   ans[vtx.val] = ans.node.val;
                
                if(vtx.node.left!=null) que.addLast(new pair(vtx.node.left,vtx.val - 1));
                if(vtx.node.right!=null) que.addLast(new pair(vtx.node.right,vtx.val + 1));
            }
        }
    }

    public static void widthDiagonal(Node node,int level,int[] res){
        if(node == null) return;

        res[0] = Math.min(level,res[0]);
        widthDiagonal(node.left,level-1,res);
        widthDiagonal(node.right,level,res);
    }

    public static void DiagonalView(Node node,int level,ArrayList<Integer>[] ans){
        if(node == null) return;

        ans[level].add(node.data);

        DiagonalView(node.left,level-1,ans);
        DiagonalView(node.right,level,ans);
    }

    public static void DiagonalView(Node node){
        int[] res = new int[1];
        widthDiagonal(node,0,res);

        ArrayList<Integer>[] ans = new ArrayList[0 - res[0] + 1];  // vector<vector<int>> ans(0 - res[0] + 1,vector<int>());
        for(int i=0;i<ans.length;i++) ans[i] = new ArrayList<>();

        DiagonalView(node,-res[0],ans);
    }

    public static void ViewSet(Node node){
                 
    }

    //set 2.=============================================================

    public static class allSolPair{
        Node prev = null;
        Node pred = null;
        Node succ = null;

        int ceil = (int) 1e9;
        int floor = -(int) 1e9;
    }

    public static void allSolution(Node node,int data,allSolPair ans){
        if(node == null) return;

        if(node.data > data) ans.ceil = Math.min(ans.ceil,node.data);
        if(node.data < data) ans.floor = Math.max(ans.floor,node.data);

        allSolution(node.left,data,ans);
        
        if(node.data == data)
            ans.pred = ans.prev;
        if(ans.prev != null && ans.prev.data == data) ans.succ = node;
        ans.prev = node;
        
        allSolution(node.right,data,ans);

    }

    




    public static void solve(){
        int[] arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
        Node root = constructTree(arr);
        display(root);
    }

    public static void main(String[] args){
        solve();
    } 
}