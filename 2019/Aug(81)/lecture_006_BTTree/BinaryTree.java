public class BinaryTree{
    
    public static void main(String[] args){
        int[] arr={10,20,40,80,-1,-1,90,-1,-1,50,100,-1,-1,-1,30,60,-1,110,-1,-1,70,120,-1,-1,-1};
        Node idx=new Node(0,null,null);
        Node root=construct(arr,idx);

        // display(root);
        System.out.println(size(root));
        System.out.println(height(root));

    }

    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static Node construct(int[] arr,Node idx) {
        if (idx.data >= arr.length || arr[idx.data] == -1) {
            idx.data++;
            return null;
        }

        Node node = new Node(arr[idx.data], null, null);
        idx.data++;
        node.left = construct(arr,idx);
        node.right = construct(arr,idx);

        return node;
    }
   

   public static void display(Node node){
        if(node==null) return;

        StringBuilder sb=new StringBuilder();

        sb.append(node.left!=null?node.left.data:".");
        sb.append(" -> " + node.data + " <- ");
        sb.append(node.right!=null?node.right.data:".");
         
        System.out.println(sb.toString());

        display(node.left);
        display(node.right);
    }

    //Util.=================================================

    public static int size(Node node){
      if(node==null) return 0;
      return size(node.left) + size(node.right) + 1;
    }


    public static int height(Node node){
     if(node==null) return 0;
     return Math.max(height(node.left),height(node.right))+1;
    }

    //display.===================================================

    public static void preOder(Node node){

    }


    public static void postOder(Node node){

    }


    public static void inOder(Node node){

    }






}