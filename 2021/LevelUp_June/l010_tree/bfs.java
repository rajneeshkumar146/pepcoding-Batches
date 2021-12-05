import java.util.ArrayList;
import java.util.LinkedList;

public class bfs {
    public class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode() {}
             TreeNode(int val) { this.val = val; }
             TreeNode(int val, TreeNode left, TreeNode right) {
                 this.val = val;
                 this.left = left;
                 this.right = right;
             }
        }


    public void level_order(TreeNode root){
        Queue<TreeNode> q=new LinkedList<>();

        q.addLast(root);

        while(q.size()!=0){
            TreeNode top=q.removeFirst();

            System.out.print(top.val+" ");

            if(top.left!=null){
                q.addLast(top.left);
            }

            if(top.right!=null){
                q.addLast(top.right);
            }
        }
    }

    public void level_order_2(TreeNode root){
        Queue<TreeNode> q=new LinkedList<>();

        q.addLast(root);
        q.addLast(null);
        int level=1;

        while(q.size()!=0){
            TreeNode top=q.removeFirst();
            if(top==null){
                System.out.println(level+" -> ");
                level++;
                if(q.size()>0) q.addLast(null);
                continue;
            }

            System.out.print(top.val+" ");

            if(top.left!=null){
                q.addLast(top.left);
            }

            if(top.right!=null){
                q.addLast(top.right);
            }
        }
    }

    public void level_order_3(TreeNode root){
        Queue<TreeNode> q=new LinkedList<>();

        q.addLast(root);
        int level=1;

        while(q.size()!=0){
            int size=q.size();
            while(size-->0){
                TreeNode top=q.removeFirst();

                System.out.print(top.val+" ");

                if(top.left!=null){
                    q.addLast(top.left);
                }

                if(top.right!=null){
                    q.addLast(top.right);
                }
            }
            level++;
            System.out.println(level+"->");
        }
    }


    // vertical order ======================================================= 

    class pair{
        TreeNode node;
        int vl;

        public pair(TreeNode node, int vl){
            this.node=node;
            this.vl=vl;
        }
    }


    void findWidth(TreeNode root, int[] minMax, int vl){
        if(root==null) return;

        minMax[0]=Math.min(minMax[0],vl);
        minMax[1]=Math.max(minMax[1],vl);

        findWidth(root.left, minMax, vl-1);
        findWidth(root.right, minMax, vl+1);
    }

    vertical_rec(TreeNode root, int vl,ArrayList<ArrayList<Integer>> ans, int shift){
        if(root==null) return;

        ans.get(vl+shift).add(root.val);

        vertical_rec(root.left,vl-1,ans,shift);
        vertical_rec(root.right,vl+1,ans,shift);
    }

    void verticalOrder(TreeNode root){
        int[] minMax=new int[2];

        findWidth(root,minMax,0);

        int width=minMax[1] - minMax[0] + 1;

        ArrayList<ArrayList<Integer>> ans=new ArrayList<>();

        for(int i=0; i<width; i++){
            ans.add(new ArrayList<>());
        }
        
        // recursively vertical order ================ 


        vertical_rec(root,0,ans,Math.abs(minMax[0]));


        // without recursion (using level order)
        // insert those elements in vertcial order first which are coming first in level order 

        Queue<pair> q=new LinkedList<>();

        q.addLast(new pair(root,0));

        while(q.size()){
            int size=q.size();

            while(size-->0){
                pair top=q.removeFirst();

                ans.add(top.vl+shift).add(top.node.val);

                if(top.node.left!=null){
                    q.addLast(new pair(top.node.left,top.vl-1));
                }

                if(top.node.right!=null){
                    q.addLast(new pair(top.node.right,top.vl+1));
                }
            }
        }

    }
}
