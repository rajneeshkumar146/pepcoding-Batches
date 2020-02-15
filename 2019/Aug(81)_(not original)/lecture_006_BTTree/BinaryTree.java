import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class BinaryTree{
    
    public static void main(String[] args){
        int[] pre={10,20,40,50,60,30,70,90,80};
        int[] in={40,20,60,50,10,70,90,30,80};
        int[] post= {40,60,50,20,90,70,80,30,10};

        // int[] arr={10,20,40,80,-1,-1,90,-1,-1,50,100,-1,-1,-1,30,
        //            60,-1,110,-1,-1,70,120,-1,-1,-1};



        // int[] arr={50,30,10,-1,20,-1,-1,40,-1,-1,80,50,60,-1,-1,-1,90};
        // Node root=construct(arr);
        
        Node root=preIn(pre,in,0,pre.length-1,0,in.length-1);
        display(root);

        // System.out.println(size(root));
        // System.out.println(height(root));

        // preOder(root); System.out.println();
        // inOder(root); System.out.println();
        // postOder(root); System.out.println();
    
        // System.out.println(find(root,500));
        // System.out.println(max(root));
       
        // ArrayList<Node> ans=new ArrayList<>();
        // rootToNodePath_01(root,50,ans);
    //     ans=rootToNodePath(root,500);
    //     if(ans!=null){
    //         Collections.reverse(ans);
    //         for(Node node:ans){
    //         System.out.print(node.data + " ");
    //     }
    // }else System.out.println("Empty ArrayList:");
    

    // leafNodes(root);
    // levelOder_01(root);
    // levelOder_02(root);
    // levelOder_03(root);

    // System.out.println(BST(root).isBst);


      DLL(root);
      Node itr=prev1;
      while(itr!=null){
       System.out.print(itr.data + " ");
       itr=itr.left;
      }

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

        Node(int data){
         this.data=data;
             
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

    //simple.
    public static void levelOder_01(Node root){
      LinkedList<Node> que=new LinkedList<>();
      que.addLast(root);
      while(!que.isEmpty()){
          Node proc=que.removeFirst();
          System.out.print(proc.data+" ");

          if(proc.left!=null) que.addLast(proc.left);
          if(proc.right!=null) que.addLast(proc.right);
      }

      System.out.println();
    }


    //line Wise
    public static void levelOder_02(Node root){
        LinkedList<Node> que=new LinkedList<>();
        que.addLast(root);

        while(!que.isEmpty()){
           int size=que.size();

           while(size--> 0){
            Node proc=que.removeFirst();
            System.out.print(proc.data+" ");

            if(proc.left!=null) que.addLast(proc.left);
            if(proc.right!=null) que.addLast(proc.right);
           }
           System.out.println();
        }

        System.out.println();
    }

    public static void swap(LinkedList<Node>[] arr){
        LinkedList<Node> temp=arr[0];
        arr[0]=arr[1];
        arr[1]=temp;
    }

    public static void swapInt(int a,int b)
    {
         int temp=a;
         a=b;
         b=temp;
    }

    public static void levelOder_03(Node root){
        LinkedList<Node> que=new LinkedList<>();
        LinkedList<Node> childQue=new LinkedList<>();
        
        que.addLast(root);

        while(!que.isEmpty()){
           Node proc=que.removeFirst();
            System.out.print(proc.data+" ");

            if(proc.left!=null) childQue.addLast(proc.left);
            if(proc.right!=null) childQue.addLast(proc.right);
            
            if(que.size()==0){
              System.out.println();
              LinkedList<Node> temp=que;
              que=childQue;
              childQue=temp;
          }
        } 

        System.out.println();
    }

    //LCA.=======================================

    public static Node LCA_01(Node root,int data1,int data2){
        ArrayList<Node> list1=rootToNodePath(root,data1);
        ArrayList<Node> list2=rootToNodePath(root,data2);

        if(list1==null || list2==null) return null;
        Node ans=null;

        int i=list1.size()-1;
        int j=list2.size()-1;
        while(i>=0 && j>=0){
            if(list1.get(i).data==list2.get(j).data){
                ans=list1.get(i);
            }else{
                break;
            }
            i--;
            j--;
        }
        
        return ans;

    }



    static Node LCA=null;
    public static boolean LCA_02(Node node,int data1,int data2){
      if(node==null) return false;
       
      boolean selfDone = node.data==data1 || node.data==data2;
      
      boolean left=LCA_02(node.left,data1,data2);
      boolean right=LCA_02(node.right,data1,data2);

      if((left&& right) || (left && selfDone) || (right&& selfDone))
          LCA=node;
        
    return left || right || selfDone;
    }

    public static class pair{
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        boolean isBst=true;
        int countBst=0;
    }

    public static pair BST(Node node){
        if(node==null) return new pair();
         
        pair left=BST(node.left);
        pair right=BST(node.right);
        pair mypair=new pair();
        
        mypair.isBst=false;
        if(left.isBst && right.isBst && left.max<= node.data && right.min>=node.data){
            mypair.isBst=true;
            mypair.countBst = 1;
        }

        mypair.min=Math.min(node.data,Math.min(left.min,right.min));
        mypair.max=Math.max(node.data,Math.max(left.max,right.max));
        mypair.countBst +=left.countBst + right.countBst;

        return mypair;

        
    }

    static int prev=Integer.MIN_VALUE;
    public static boolean isBST(Node curr){
      if(curr==null) return true;

      boolean left=isBST(curr.left);
      if(!left) return false;
      
      if(prev<curr.data) prev=curr.data;
      else return false;
      boolean right=isBST(curr.right);
      if(!right) return false;

      return true;

    }


    public static int diameter_01(Node node){
    if(node==null) return 0;

    int lh=height(node.left);
    int rh=height(node.right);

    int ld=diameter_01(node.left);
    int rd=diameter_01(node.right);

    return Math.max(Math.max(ld,rd),lh+rh+1);
    }

    public static class diaPair{
        int height=0;
        int dia=0;
    }

    public static diaPair diameter_02(Node node){
        if(node==null) return new diaPair();

        diaPair left=diameter_02(node.left);
        diaPair right=diameter_02(node.right);

        diaPair mypair=new diaPair();
        mypair.height=Math.max(left.height,right.height)+1;
        mypair.dia=Math.max(Math.max(left.dia,right.dia),left.height+right.height+1);

        return mypair;
    }

    public static void deleteLeaf_01(Node node,int leaf){
     if(node==null) return ;

     if(node.left!=null && node.left.data==leaf) node.left=null;
     if(node.right!=null && node.right.data==leaf) node.right=null;

     deleteLeaf_01(node.left,leaf);
     deleteLeaf_01(node.right,leaf);
    }


    public static Node deleteLeaf_02(Node node,int leaf){
        if(node==null) return null;
   
        if(node.left==null && node.right==null && node.data==leaf)   return null;

        node.left= deleteLeaf_02(node.left,leaf);
        node.right=deleteLeaf_02(node.right,leaf);
        
        return node;
    }


    public static void addLeaf_01(Node node,int par,int leaf,boolean isLeft){
        if(node==null) return;
   
        if(node.data==par){
            if(isLeft) node.left=new Node(leaf);
            else node.right=new Node(leaf);
        }

        addLeaf_01(node.left,par,leaf,isLeft);
        addLeaf_01(node.right,par,leaf,isLeft);
    
    }

    public static Node preIn(int[] pre,int[] in,int ps,int pe,int is,int ie){
      if(ps>pe || is>ie){
          return null;
      }

       Node node=new Node(pre[ps]);
       int idx=is;
       while(idx<=ie){
           if(in[idx]==pre[ps]) break;
           idx++;
       }

       int tne=idx-is;

       node.left=preIn(pre,in,ps+1,ps+tne,is,idx-1);
       node.right=preIn(pre,in,ps+tne+1,pe,idx+1,ie);
       
       return node;
    }

    public static Node postIn(int[] post,int[] in,int ps,int pe,int is,int ie){
        if(ps>pe || is>ie){
            return null;
        }
  
         Node node=new Node(post[pe]);
         int idx=is;
         while(idx<=ie){
             if(in[idx]==post[pe]) break;
             idx++;
         }
  
         int tne=idx-is;
  
         node.left=postIn(post,in,ps,ps+tne-1,is,idx-1);
         node.right=postIn(post,in,ps+tne,pe-1,idx+1,ie);
         
         return node;
      }

    static Node head=null;
    static Node prev1=null;
    
     public static void DLL(Node curr){
         if(curr==null) return;

        DLL(curr.left);

         if(head==null){
             head=curr;
         }else{
             prev1.right=curr;
             curr.left=prev1;
         }

         prev1=curr;


        DLL(curr.right);




     }


  


















}