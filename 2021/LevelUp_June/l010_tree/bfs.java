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
            System.out.println(level+"->")
        }
    }
}
