import java.util.ArrayList;
import java.util.Collections;
public class BinaryTree{
    
    public static void main(String[] args){
        int[] arr={10,20,40,80,-1,-1,90,-1,-1,50,100,-1,-1,-1,30,
                   60,-1,110,-1,-1,70,120,-1,-1,-1};
        Node root=construct(arr);

        display(root);
        // System.out.println(size(root));
        // System.out.println(height(root));

        // preOder(root); System.out.println();
        // inOder(root); System.out.println();
        // postOder(root); System.out.println();
    
        // System.out.println(find(root,500));
        // System.out.println(max(root));
       
        ArrayList<Node> ans=new ArrayList<>();
        // rootToNodePath_01(root,50,ans);
    //     ans=rootToNodePath(root,500);
    //     if(ans!=null){
    //         Collections.reverse(ans);
    //         for(Node node:ans){
    //         System.out.print(node.data + " ");
    //     }
    // }else System.out.println("Empty ArrayList:");
    

    leafNodes(root);

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

    static int idx=0;
    public static Node construct(int[] arr) {
        if (idx >= arr.length || arr[idx] == -1) {
            idx++;
            return null;
        }

        Node node = new Node(arr[idx], null, null);
        idx++;
        node.left = construct(arr);
        node.right = construct(arr);

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

    public static boolean find(Node node,int data){
      if(node==null) return false;
      if(node.data==data) return true;

      return find(node.left,data) || find(node.right,data);
    }


    public static int max(Node node){
       if(node==null)
           return Integer.MIN_VALUE;
       return Math.max(max(node.left),Math.max(node.data,max(node.right)));
    }


    public static int min(Node node){
        if(node==null)
        return Integer.MAX_VALUE;
        
        return Math.min(min(node.left),Math.min(node.data,min(node.right)));
      }

    //display.===================================================

    public static void preOder(Node node){
     if(node==null) return;
     System.out.print(node.data+ " ");
     preOder(node.left);
     preOder(node.right);
    }


    public static void postOder(Node node){
        if(node==null) return;
        
        postOder(node.left);
        postOder(node.right);
        System.out.print(node.data+ " ");
    }


    public static void inOder(Node node){
        if(node==null) return;
        
        inOder(node.left);
        System.out.print(node.data+ " ");
        inOder(node.right);
    } 

    //questionSet1====================================

    public static ArrayList<Node> rootToNodePath(Node node,int data){
        if(node==null) return null;
        
        if(node.data==data){
            ArrayList<Node> base=new ArrayList<>();
            base.add(node);
            return base;
        }

        ArrayList<Node> leftAns=rootToNodePath(node.left,data);
        if(leftAns!=null){
            leftAns.add(node);
            return leftAns;
        }


        ArrayList<Node> rightAns=rootToNodePath(node.right,data);
        if(rightAns!=null){
            rightAns.add(node);
            return rightAns;
        }

        return null;
    } 


    public static boolean rootToNodePath_01(Node root,int data,ArrayList<Node> path){
        if(root==null) return false;
        if(root.data==data) {
            path.add(root);
            return true;
        }
        
        if(rootToNodePath_01(root.left,data,path)){
            path.add(root);
            return true;
        }
        if(rootToNodePath_01(root.right,data,path)){
            path.add(root);
            return true;
        }

        return false;
    }
    
    public static void leafNodes(Node node){
      if(node==null) return;
      if(node.left==null && node.right==null) {
        System.out.print(node.data + " "); 
        return;
      }
      leafNodes(node.left);
      leafNodes(node.right);


    }








}