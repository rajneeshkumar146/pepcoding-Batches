public class avl{

public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        int height=-1;
        int bal=0;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
}

public static void updateHeight(Node node){
    int lh=-1;
    int rh=-1;

    if(node.left!=null) lh=node.left.height;
    if(node.right!=null) rh=node.right.height;

    node.height=Math.max(lh,rh)+1;
}

public static void updateBalance(Node node){
    int lh=-1;
    int rh=-1;

    if(node.left!=null) lh=node.left.height;
    if(node.right!=null) rh=node.right.height;

    node.bal=lh-rh;
}

//ll_rot
public static Node ll(Node x){
    Node y=x.left;
    Node yKaRight=y.right;
    
    y.right=x;
    x.left=yKaRight;

    return y;
}

public static Node rr(Node x){
    Node y=x.right;
    Node yKaLeft= y.left;

    y.left=x;
    x.right=yKaLeft;
    
    

    return y;
}

public static Node getRotate(Node node){
    
    if(node.bal==2){  //ll,lr
        if(node.left.bal==1){  //ll
           return ll(node);
        }else{  //lr
          node.left=rr(node.left);
          return ll(node);
        }
    }else if(node.bal==-2){  //rr,rl
        if(node.right.bal==-1){  //rr
           return rr(node);
        }else{  //rl
          node.right=ll(node.right);
          return rr(node);
        }
    }
}













}