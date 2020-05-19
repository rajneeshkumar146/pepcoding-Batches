public class l001{

    public static void main(String[] args){
        solve();
    }

   public static class Node{
       int data;  
       Node left=null;   // Node* left=nullptr;   
       Node right=null;  // Node* right=nullptr;

       Node(int data){
           this.data=data;
       } 
       
       Node(){
       }
   }

   static int idx=0;
   public static Node constructTree(int[] arr){
      if(idx >= arr.length || arr[idx]==-1) {
        idx++;  
        return null;
      }

      Node node=new Node(arr[idx++]);   // Node* node=new Node(arr[idx++]);
      node.left=constructTree(arr);
      node.right=constructTree(arr);
      
      return node;
   }

   public static void display(Node node){
       if(node==null) return;

       String str="";
       str += ((node.left!=null)?node.left.data:".");
       str+=  " <- " + node.data + " -> ";
       str += ((node.right!=null)?node.right.data:".");
       System.out.println(str);

       display(node.left);
       display(node.right);

   }

   //Basic.================================================================

   public static int size(Node node){
     if(node==null) return 0;
     return (size(node.left) + size(node.right) + 1);
   }

   
   public static int height(Node node){
    if(node==null) return -1; // return -1, height w.r.t edge, return 0, height w.r.t node.
    return Math.max(height(node.left), height(node.right)) + 1;
   }

   public static int Maximum(Node node){
    if(node==null) return (int)-1e8;  // java: Integer.MIN_VALUE, c++: INT_MIN; 
    return Math.max(Math.max(Maximum(node.left), Maximum(node.right)),node.data);  // max(leftSubtree,rightSubtree,myself);
   }

   
   public static int Minimum(Node node){
    if(node==null) return (int)1e8;  // java: Integer.MAX_VALUE, c++: INT_MAX; 
    return Math.min(Math.min(Minimum(node.left), Minimum(node.right)),node.data);
   }

   public static int Minimum_02(Node node){
       int min_=node.data;
       
       if(node.left!=null) min_=Math.min(min_,Minimum_02(node.left));
       if(node.right!=null) min_=Math.min(min_,Minimum_02(node.right));
       
       return min_;
   }

   public static boolean find(Node node,int data){
       if(node==null) return false;
       
       if(node.data==data) return true;
       return find(node.left,data) || find(node.right,data);
   }

   //Traversal.============================================================

    public static void preOrder(Node node){
         if(node==null) return;

         System.out.print(node.data+ " ");
         preOrder(node.left);
         preOrder(node.right);
    }

    
    public static void inOrder(Node node){
        if(node==null) return;

        inOrder(node.left);
        System.out.print(node.data+ " ");
        inOrder(node.right);
   }

   public static void postOrder(Node node){
    if(node==null) return;

    postOrder(node.left);
    postOrder(node.right);
    System.out.print(node.data+ " ");
}





   public static void solve(){
    //    int[] arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
       int[] arr={10,20};
       Node root=constructTree(arr);
       display(root);
   }

   


}