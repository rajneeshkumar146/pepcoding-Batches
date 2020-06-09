import java.util.*;
public class l003_BST{

    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node CreateTree(int[] arr,int li,int ri){
        if(li>ri) return null;
        int mid=(li+ri)/2;
        Node node=new Node(arr[mid]);
        node.left=CreateTree(arr,li,mid-1);
        node.right=CreateTree(arr,mid+1,ri);

        return node;

    }

    public static void display(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.left != null ? node.left.data : ".");
        System.out.print("<-" + node.data + "->");
        System.out.println(node.right != null ? node.right.data : ".");
        display(node.left);
        display(node.right);
    }

    //700 leetcode
    public static boolean find(Node root,int data){
        while(root!=null){
            if(root.data==data){
                return true;
            }else if(data<root.data){
                root=root.left;
            }else 
                root=root.right;
        }

        return false;
    }

    //235 leetcode.
    public static Node LCAInBST(Node root,int a,int b){  // a < b

        while(root!=null){
            if(b<root.data && a<root.data){
                root=root.left;
            }else if(b>root.data && a>root.data)
                root=root.right;
            else
               return root;
        }

        return null;

    }

    //938. Range Sum of BST
    public static int RangeSumInBST(Node root,int l,int r){
        if(root==null) return 0;

        int left=RangeSumInBST(root.left,l,r);
        int right=RangeSumInBST(root.right,l,r);

        return left+right + ((root.data>=l && root.data<=r )?root.data:0);
    }

    public Node addNode(Node node,int data){
        if(node==null) return new Node(data);
        if(data<node.data)
          node.left=addNode(node.left,data);
        else
        node.right=addNode(node.right,data);

        return node;
    }
    
    public static int maximum(Node node){
        while(node.right!=null)
           node=node.right;
        return node.data;
    }

    public static int minimum(Node node){
        while(node.left!=null)
           node=node.left;
        return node.data;
    }

    static Node prev=null;
    public static boolean isBST(Node node){
        if(node==null) return true;

        boolean left=isBST(node.left);
        if(left==false) return false;
        
        if(prev!=null && prev.data>node.data) return false;
        prev=node;
        
        boolean right=isBST(node.right);
        if(!right) return false;

        return true;

    }

    //set1.==================================================

    //173 leetcode.
   static class BSTIterator {
       Stack<Node> st=new Stack<>();
        public BSTIterator(Node root) {
            insertAllLeftMost(root);
        }
        
        public int next() {
            // node ko nikalo stack mai se.
            Node rn=st.pop();

            // call karo insert all left most ko for my right node(jiko nikala hai uske right ko)
            insertAllLeftMost(rn.right);

            //return karo nikale hue node ko.
            return rn.data;
        }
        
        public boolean hasNext() {
            return st.size()!=0;  // agar uske pass next element hua to true nahi to false.
        }

        private void insertAllLeftMost(Node node){  //agar right ko bheja jaye to usme right or left most daldeta hai.
            while(node!=null){
                st.push(node);
                node=node.left;
            }
        }
    }

    public static void set1(Node root){
        BSTIterator itr=new BSTIterator(root);
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }

    //Construction================================================
     
    static int PreOrderidx=0;
    public static Node ConstructByPre(int[] arr,int lb,int rb){
        if(PreOrderidx==arr.length) return null;

        int val=arr[PreOrderidx];
        if(val>lb || val<rb) return null;
        
        Node node=new Node(val);
        PreOrderidx++;
        node.left=ConstructByPre(arr,lb,val);
        node.right=ConstructByPre(arr,val,rb);

        return node;
    }

    static int postIndx = 0;
    public static Node ConstructByPost(int [] arr, int lb, int rb){
        if(postIndx == -1) return null;

        int val = arr[postIndx];
        if(val<lb || val>rb) return null;

        Node root = new Node(val);
        postIndx--;
        root.right = ConstructByPost(arr, val, rb);
        root.left = ConstructByPost(arr, lb, val);

        return root;
    }



    public static Node ConstructByLevelOrder(int [] arr){
    
     class pair{
      Node node=null;
      int lb=0;
      int rb=0;
      pair(Node node,int lb,int rb){
          this.node=node;
          this.lb=lb;
          this.rb=rb;
      }
     }

     
     LinkedList<pair> que=new LinkedList<>();
     Node root=new Node(arr[0]);
     que.addLast(new pair(root,Integer.MIN_VALUE,arr[0]));
     que.addLast(new pair(root,arr[0],Integer.MAX_VALUE));
     int idx=1;

     while(que.size() !=0 && idx<arr.length){
         pair p=que.removeFirst();
         if(arr[idx]>=p.lb && arr[idx]<=p.rb){   //left child
            Node node=new Node(arr[idx]);
            p.node.left=node;

            que.addLast(new pair(node,p.lb,arr[idx])); //possible left child 
             que.addLast(new pair(node,arr[idx],p.rb)); //possible right child
             idx++;
         }

         p=que.removeFirst();
         if(idx<arr.length && arr[idx]>=p.lb && arr[idx]<=p.rb){   //right child
            Node node=new Node(arr[idx]);
            p.node.right=node;
             que.addLast(new pair(node,p.lb,arr[idx])); //possible left child 
             que.addLast(new pair(node,arr[idx],p.rb)); //possible right child
             idx++;
         }
     }
    
    return root;
    
    }



    public static void contructionOfTree(){
        // int [] arr = {50,25,10,5,15,45,35,27,30,48,85,65,70,68,69};  //pre
        // int [] arr = {5,15,10,30,20,35,48,45,25,69,68,70,65,85,50};  //post
        int[] arr={50,25,85,10,35,95};
        postIndx = arr.length-1;
        // Node root = ConstructByPre(arr, Integer.MIN_VALUE, Integer.MAX_VALUE);
        // Node root = ConstructByPost(arr, Integer.MIN_VALUE, Integer.MAX_VALUE);

        Node root=ConstructByLevelOrder(arr);

        display(root);
    }

   
    
    
    
    
    public static void solve() {
        int[] arr = { 10,20,30,40,50,60,70,80,90,100};
        Node root = CreateTree(arr,0,arr.length-1);
        // display(root);
        contructionOfTree();
        // set1(root);

    }

    public static void main(String[] args){
         solve();

    }





}