import java.util.*;
class l001{
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data){
            this.data = data;
        }
    }  

    static int indx = 0;
    public static Node create_Tree(int [] arr){
        if(indx == arr.length || arr[indx] == -1){
            indx++;
            return null;
        }

        Node node = new Node(arr[indx++]);

        node.left = create_Tree(arr);
        node.right = create_Tree(arr);

        return node;
    }

    public static void display(Node node){
        if(node == null){
            return;
        }

        System.out.print(node.left!=null?node.left.data:".");
        System.out.print("<-"+node.data+"->");
        System.out.println(node.right!=null?node.right.data:".");
        display(node.left);
        display(node.right);
    }

    public static void preOrder(Node root){
        if(root == null) return;

        System.out.print(root.data+",");
        preOrder(root.left);
        preOrder(root.right);

    }

    public static void postOrder(Node root){
        if(root == null) return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data+",");
    }

    public static void inOrder(Node root){
        if(root == null) return;

        inOrder(root.left);
        System.out.print(root.data+",");
        inOrder(root.right);
    }

    public static int mysize(Node root){
        if(root == null) return 0;

        int count = 0;
        count+=mysize(root.left);
        count+=mysize(root.right);

        return count+1;
    }

    public static int height(Node root){
        if(root == null) return -1;

        int lp = height(root.left);
        int rp = height(root.right);

        int ans = Math.max(lp,rp)+1;

        return ans;
    }

    public static boolean find(Node root, int data){
        if(root == null) return false;

        if(root.data == data) return true;

        boolean lp = find(root.left, data);
        boolean rp = find(root.right, data);

        return lp||rp;
    }

    public static void LevelOrder(Node root){
        LinkedList<Node> queue = new LinkedList<>();

        queue.addLast(root);

        while(!queue.isEmpty()){
            Node rnode = queue.removeFirst();

            System.out.print(rnode.data+" ");

            if(rnode.left!=null){
                queue.addLast(rnode.left);
            }
            if(rnode.right!=null){
                queue.addLast(rnode.right);
            }
        }
    }

    public static class pair{
        int n = 0;
        String binary = "";

        pair(int n, String binary){
            this.n = n;
            this.binary = binary;
        }
    }

    public static void printBinary(int n){
        LinkedList<pair> queue = new LinkedList<>();

        queue.addLast(new pair(1,"1"));

        while(queue.size()!=0){
            pair rnode = queue.removeFirst();
            System.out.println(rnode.n+"->"+rnode.binary);

            if(2*rnode.n<=n){
                queue.addLast(new pair(2*rnode.n,rnode.binary+"0"));
            }                  
            if(2*rnode.n+1<=n){
                queue.addLast(new pair(2*rnode.n+1,rnode.binary+"1"));
            }
        }
    }

    public static class Ipair{
        Node node;
        boolean print = false;
        boolean lcall = false;
        boolean rcall = false;

        Ipair(Node node){
            this.node = node;
        }
    }

    public static void PreOrderItervative(Node root){
        LinkedList<Ipair> stack = new LinkedList<>();

        stack.addFirst(new Ipair(root));
        while(stack.size()>0){
            Ipair rnode = stack.getFirst();

            if(rnode.node == null){
                stack.removeFirst();
                continue;
            }
            if(!rnode.print){
                rnode.print = true;
                System.out.print(rnode.node.data+" ");
            }else if(!rnode.lcall){
                rnode.lcall = true;
                stack.addFirst(new Ipair(rnode.node.left));
            }else if(!rnode.rcall){
                rnode.rcall = true;
                stack.addFirst(new Ipair(rnode.node.right));
            }else{
                stack.removeFirst();
            }
        }
    }

    public static void PostOrderItervative(Node root){
        LinkedList<Ipair> stack = new LinkedList<>();

        stack.addFirst(new Ipair(root));
        while(stack.size()>0){
            Ipair rnode = stack.getFirst();

            if(rnode.node == null){
                stack.removeFirst();
                continue;
            }
            
            if(!rnode.lcall){
                rnode.lcall = true;
                stack.addFirst(new Ipair(rnode.node.left));
            }else if(!rnode.rcall){
                rnode.rcall = true;
                stack.addFirst(new Ipair(rnode.node.right));
            }else if(!rnode.print){
                rnode.print = true;
                System.out.print(rnode.node.data+" ");
            }else{
                stack.removeFirst();
            }
        }
    }

    public static void InOrderItervative(Node root){
        LinkedList<Ipair> stack = new LinkedList<>();

        stack.addFirst(new Ipair(root));
        while(stack.size()>0){
            Ipair rnode = stack.getFirst();

            if(rnode.node == null){
                stack.removeFirst();
                continue;
            }
            
            if(!rnode.lcall){
                rnode.lcall = true;
                stack.addFirst(new Ipair(rnode.node.left));
            }else if(!rnode.print){
                rnode.print = true;
                System.out.print(rnode.node.data+" ");
            }else if(!rnode.rcall){
                rnode.rcall = true;
                stack.addFirst(new Ipair(rnode.node.right));
            }else{
                stack.removeFirst();
            }
        }
    }

    public static void singleChild(Node root){
        if(root == null){
            return;
        }
        if(root.left!=null && root.right == null){
            System.out.print(root.left.data+" ");
        }
        if(root.left==null && root.right!=null){
            System.out.print(root.right.data+" ");
        }

        singleChild(root.left);
        singleChild(root.right);
    }

    public static Node removeLeaf(Node root){
        if(root == null){
            return null;
        }

        if(root.left == null && root.right == null){
            return null;
        }

        root.left = removeLeaf(root.left);
        root.right = removeLeaf(root.right);

        return root;
    }
    
    public static ArrayList<Node> n2rPath(Node root, int data){
        if(root == null){
            return new ArrayList<>();
        }
        if(root.data == data){
            ArrayList<Node> base = new ArrayList<>();
            base.add(root);
            return base;
        }

        ArrayList<Node> leftcall = n2rPath(root.left, data);
        if(leftcall.size()>0){
            leftcall.add(root);
            return leftcall;
        }

        ArrayList<Node> rightcall = n2rPath(root.right, data);
        if(rightcall.size()>0){
            rightcall.add(root);
            return rightcall;
        }

        return new ArrayList<>();

    }

    public static void printKDown(Node root,Node avoid, int k){
        if(root == null || root==avoid){
            return;
        }
        if(k == 0){
            System.out.print(root.data+" ");
            return;
        }
        printKDown(root.left, avoid, k-1);
        printKDown(root.right, avoid, k-1);
    }

    public static void printKfar(Node root,int data, int k){
        if(root == null){
            return;
        }
        ArrayList<Node> path=n2rPath(root,data);
        Node avoid=null;
        for(int i=0;i<path.size();i++){
            printKDown(path.get(i),avoid,k-i);
            avoid=path.get(i);
        }
    }

    public static Node LCA(Node root, int data1, int data2){
        if(root == null){
            return null;
        }
        ArrayList<Node> list1 = n2rPath(root, data1);
        ArrayList<Node> list2 = n2rPath(root, data2);
        Node lca = null;
        int i = list1.size()-1;
        int j = list2.size()-1;
        while(i>=0 && j>=0 && list1.get(i) == list2.get(j)){
            lca = list1.get(i);
            i--;
            j--;
        }
        return lca;
    }

    public static int distanceBtw2Nodes(Node root, int data1, int data2){
        if(root == null){
            return 0;
        }
        int count = 0;
        ArrayList<Node> list1 = n2rPath(root, data1);
        ArrayList<Node> list2 = n2rPath(root, data2);
        int i = list1.size()-1;
        int j = list2.size()-1;
        while(i >= 0 && j>=0 && list1.get(i) == list2.get(j)){
            count++;
            i--;
            j--;
        }
        int distance = list1.size()+list2.size()-2*count+1;
        return distance;
    }

    static class allSolPair{

       int height=0;
       int size=0;
       boolean find=false;
 
        int ceil=Integer.MAX_VALUE;
        int floor=Integer.MIN_VALUE;

        Node pred=null;
        Node succ=null;
        Node prev=null;

    }

    public static void allSolution(Node node,int data,int level,allSolPair pair){
        if(node==null) return;
        
        pair.height=Math.max(pair.height,level);
        pair.size++;
        pair.find = pair.find || node.data==data;

        if(node.data<data) pair.floor=Math.max(pair.floor,node.data);
        if(node.data>data) pair.ceil=Math.min(pair.ceil,node.data);


        if(node.data==data) pair.pred=pair.prev;
        if(pair.prev!=null && pair.prev.data==data) pair.succ=node;
        
        pair.prev=node;
        allSolution(node.left,data,level+1,pair);
        allSolution(node.right,data,level+1,pair);
    }

   
    

    public static int diameter(Node root){
        if(root == null){
            return 0;
        }
        int ld = diameter(root.left);
        int rd = diameter(root.right);
        int lh = height(root.left);
        int rh = height(root.right);

        return Math.max(lh+rh+2,Math.max(ld,rd));
    }

    static int maxDia = 0; 

    public static int diameter2(Node root){
        if(root == null) return -1;
        int ld = diameter2(root.left);
        int rd = diameter2(root.right);

        maxDia = Math.max(ld+rd+2,maxDia);
        return Math.max(ld, rd)+1;
    }
    public static Node createTreeByPreIn(int [] pre, int[] in , int plo, int phi, int ilo, int ihi){
        if(plo>phi || ilo>ihi){
            return null;
        }
        Node root = new Node(pre[plo]);
        int gap = 0;
        while(in[ilo+gap]!=pre[plo]){
            gap++;
        }
        root.left = createTreeByPreIn(pre, in, plo+1, plo+gap, ilo, ilo+gap-1);
        root.right = createTreeByPreIn(pre, in, plo+gap+1, phi, ilo+gap+1, ihi);
        return root;
    }

    public static Node createTreeByPostIn(int [] post, int [] in, int plo, int phi, int ilo, int ihi){
        if(plo>phi || ilo>ihi){
            return null;
        }
        Node root = new Node(post[phi]);
        int gap = 0;
        while(in[ilo+gap]!=post[phi]){
            gap++;
        }
        root.left = createTreeByPostIn(post, in, plo, plo+gap-1, ilo, ilo+gap-1);
        root.right = createTreeByPostIn(post, in, plo+gap, phi-1, ilo+gap+1, ihi);
        return root;
    }

    //.============================================================
    
    public static void solve(){
        int [] arr = {10,20,30,-1,-1,40,-1,-1,50,60,70,-1,-1,80,90,-1,-1,-1,100,-1,-1};
        Node root = create_Tree(arr);
        // display(root);
        // preOrder(root);
        // postOrder(root);
        // inOrder(root);
        // System.out.println(mysize(root));
        // System.out.print(height(root));
        // System.out.println(find(root,40));
        // System.out.println(find(root,110));
        // LevelOrder(root);
        // printBinary(15);
        // System.out.println();
        // PreOrderItervative(root);
        // PostOrderItervative(root);
        // InOrderItervative(root);
        // singleChild(root);
        // ArrayList<Node> ans = n2rPath(root, 90);
        // for(Node n:ans){
        //     System.out.println(n.data);
        // }
        
        // printKDown(root,null,3);
        // printKfar(root,50,3);
        // Node ans = LCA(root,70,90);
        // System.out.println(ans.data);
        // System.out.print(distanceBtw2Nodes(root,90,100));
    
    
        // allSolPair pair=new allSolPair();
        // allSolution(root,40,0,pair);
        // System.out.println("height: "+pair.height);
        // System.out.println("size: "+pair.size);
        // System.out.println("find: "+pair.find);
        // System.out.println("ceil: "+pair.ceil);
        // System.out.println("floor: "+pair.floor);
        // System.out.println("pred: "+pair.pred.data);
        // System.out.println("succ: "+pair.succ.data);

        //    viewSet(root);

        // System.out.println(diameter(root));
        System.out.println(diameter2(root) + " -> " + maxDia);
    
        // int [] pre = {10,20,30,40,50,60,70,80,90,100};
        // int [] in =  {30,20,40,10,70,60,90,80,50,100};
        // int [] post = {30,40,20,70,90,80,60,100,50,10};
        // Node root1 = createTreeByPreIn(pre, in, 0, pre.length-1, 0, in.length-1);
        // Node root2 = createTreeByPostIn(post, in, 0, post.length-1, 0, in.length-1);
        // display(root1);
        // display(root2);
    
    }

    public static void main(String [] args){
        solve();
    }
}