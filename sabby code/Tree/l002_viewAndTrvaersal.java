import java.util.*;

public class l002_viewAndTrvaersal {

    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }
    }

    static int indx = 0;

    public static Node create_Tree(int[] arr) {
        if (indx == arr.length || arr[indx] == -1) {
            indx++;
            return null;
        }

        Node node = new Node(arr[indx++]);

        node.left = create_Tree(arr);
        node.right = create_Tree(arr);

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

    // ============================================

    public static void levelOrderLineWise(Node node) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);
        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            System.out.print("level: " + level + " -> ");
            while (size-- > 0) {
                Node rnode = que.removeFirst();
                System.out.print(rnode.data + " ");

                if (rnode.left != null)
                    que.addLast(rnode.left);
                if (rnode.right != null)
                    que.addLast(rnode.right);
            }

            level++;
            System.out.println();
        }
    }

    // viewSet.====================================

    public static void leftView(Node node) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);

        while (que.size() != 0) {

            int size = que.size();
            System.out.print(que.getFirst().data + " ");

            while (size-- > 0) {
                Node rnode = que.removeFirst();

                if (rnode.left != null)
                    que.addLast(rnode.left);
                if (rnode.right != null)
                    que.addLast(rnode.right);
            }
        }
        System.out.println();

    }

    public static void rightView(Node node) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);

        while (que.size() != 0) {

            int size = que.size();
            Node prev = null;

            while (size-- > 0) {
                Node rnode = que.removeFirst();
                prev = rnode;
                if (rnode.left != null)
                    que.addLast(rnode.left);
                if (rnode.right != null)
                    que.addLast(rnode.right);
            }

            System.out.print(prev.data + " ");
        }
        System.out.println();

    }

    public static void width(Node node, int level, int[] minMax) { // minmax[0] is min(left most) and index 1 is
                                                                   // max(right most)
        if (node == null)
            return;

        minMax[0] = Math.min(minMax[0], level);
        minMax[1] = Math.max(minMax[1], level);

        width(node.left, level - 1, minMax);
        width(node.right, level + 1, minMax);
    }

    public static class Viewpair {
        Node node = null;
        int level = 0;

        Viewpair(Node node, int level) {
            this.node = node;
            this.level = level;
        }

    }

    public static Node[] topView(Node node) {
        int[] minMax = new int[2];
        width(node, 0, minMax);
        Node[] topViewEle = new Node[minMax[1] - minMax[0] + 1];

        LinkedList<Viewpair> queue = new LinkedList<>();
        queue.addLast(new Viewpair(node, -minMax[0]));

        while (queue.size() != 0) {

            int size = queue.size();
            while (size-- > 0) {
                Viewpair rpair = queue.removeFirst();
                if (topViewEle[rpair.level] == null) // ekk baar value set hogyi to bs hogyi abb change nahi hoga.
                    topViewEle[rpair.level] = rpair.node;

                if (rpair.node.left != null)
                    queue.addLast(new Viewpair(rpair.node.left, rpair.level - 1));
                if (rpair.node.right != null)
                    queue.addLast(new Viewpair(rpair.node.right, rpair.level + 1));

            }
        }
        System.out.println();
        return topViewEle;
    }

    public static Node[] bottomView(Node root) {
        int[] minMax = new int[2];
        width(root, 0, minMax);
        Node[] bottomViewEle = new Node[minMax[1] - minMax[0] + 1];

        LinkedList<Viewpair> queue = new LinkedList<>();
        queue.addLast(new Viewpair(root, -minMax[0]));

        while (queue.size() > 0) {
            int size = queue.size();
            while (size-- > 0) {
                Viewpair rpair = queue.removeFirst();
                bottomViewEle[rpair.level] = rpair.node; // update is allowed.
                if (rpair.node.left != null)
                    queue.addLast(new Viewpair(rpair.node.left, rpair.level - 1));
                if (rpair.node.right != null)
                    queue.addLast((new Viewpair(rpair.node.right, rpair.level + 1)));
            }
        }
        System.out.println();
        return bottomViewEle;
    }

    public static void verticalView(Node node) {
        LinkedList<Node> queN = new LinkedList<>();// queN = queue of nodes
        LinkedList<Integer> queL = new LinkedList<>();// queL = queue of levels
        queN.addLast(node);
        queL.addLast(0);

        int min = 0;
        int max = 0;

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();// key: Integer, Value: ArrayList

        while (queN.size() > 0) {

            int size = queN.size();
            while (size-- > 0) {
                Node rnode = queN.removeFirst();
                Integer level = queL.removeFirst();

                if (!map.containsKey(level)) {
                    map.put(level, new ArrayList<>());
                    map.get(level).add(rnode.data);
                } else {
                    map.get(level).add(rnode.data);
                }

                min = Math.min(level, min);
                max = Math.max(level, max);

                if (rnode.left != null) {
                    queN.addLast(rnode.left);
                    queL.addLast(level - 1);
                }

                if (rnode.right != null) {
                    queN.addLast(rnode.right);
                    queL.addLast(level + 1);
                }
            }
        }

        for (int i = min; i <= max; i++) {
            System.out.println(i + " -> " + map.get(i));
        }
        System.out.println();

    }

    public static void verticalView_02(Node node) {
        LinkedList<Node> queN = new LinkedList<>();// queN = queue of nodes
        LinkedList<Integer> queL = new LinkedList<>();// queL = queue of levels

        int[] minMax = new int[2];
        width(node, 0, minMax);
        ArrayList<Integer>[] ans = new ArrayList[minMax[1] - minMax[0] + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = new ArrayList<>();
        }

        queN.addLast(node);
        queL.addLast(-minMax[0]);

        while (queN.size() > 0) {

            int size = queN.size();
            while (size-- > 0) {
                Node rnode = queN.removeFirst();
                Integer level = queL.removeFirst();

                ans[level].add(rnode.data);
                if (rnode.left != null) {
                    queN.addLast(rnode.left);
                    queL.addLast(level - 1);
                }

                if (rnode.right != null) {
                    queN.addLast(rnode.right);
                    queL.addLast(level + 1);
                }
            }
        }

        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
        System.out.println();

    }

    public static void verticalSum_02(Node node) {
        LinkedList<Node> queN = new LinkedList<>();// queN = queue of nodes
        LinkedList<Integer> queL = new LinkedList<>();// queL = queue of levels

        int[] minMax = new int[2];
        width(node, 0, minMax);
        int[] ans = new int[minMax[1] - minMax[0] + 1];

        queN.addLast(node);
        queL.addLast(-minMax[0]);

        while (queN.size() != 0) {

            int size = queN.size();
            while (size-- > 0) {
                Node rnode = queN.removeFirst();
                Integer level = queL.removeFirst();

                ans[level] += rnode.data;

                if (rnode.left != null) {
                    queN.addLast(rnode.left);
                    queL.addLast(level - 1);
                }

                if (rnode.right != null) {
                    queN.addLast(rnode.right);
                    queL.addLast(level + 1);
                }
            }
        }

        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }

        System.out.println();
    }

    public static void diagonalView(Node root) {
        LinkedList<Node> queN = new LinkedList<>();
        LinkedList<Integer> queI = new LinkedList<>();

        int[] minMax = new int[2];
        width(root, 0, minMax);
        queN.addLast(root);
        queI.addLast(-minMax[0]);

        ArrayList<Integer>[] ans = new ArrayList[-minMax[0] + 1]; // phele negative ko positive bnao and then usme 1 add
                                                                  // karo
        // taki size bnjaye.
        for (int i = 0; i < ans.length; i++) {
            ans[i] = new ArrayList<>();
        }

        while (queN.size() != 0) {
            int size = queN.size();
            while (size-- > 0) {
                Node rnode = queN.removeFirst();
                int level = queI.removeFirst();

                ans[level].add(rnode.data);

                if (rnode.left != null) {
                    queN.addLast(rnode.left);
                    queI.addLast(level - 1);
                }

                if (rnode.right != null) {
                    queN.addLast(rnode.right);
                    queI.addLast(level);
                }
            }
        }

        for (int i = ans.length - 1; i >= 0; i--) {
            System.out.println(ans[i]);
        }
    }

    public static void diagonalSum(Node root) {
        LinkedList<Node> queN = new LinkedList<>();
        LinkedList<Integer> queI = new LinkedList<>();

        int[] minMax = new int[2];
        width(root, 0, minMax);
        queN.addLast(root);
        queI.addLast(-minMax[0]);

        int[] ans = new int[-minMax[0] + 1];

        while (queN.size() != 0) {
            int size = queN.size();
            while (size-- > 0) {
                Node rnode = queN.removeFirst();
                int level = queI.removeFirst();

                ans[level] += rnode.data;

                if (rnode.left != null) {
                    queN.addLast(rnode.left);
                    queI.addLast(level - 1);
                }

                if (rnode.right != null) {
                    queN.addLast(rnode.right);
                    queI.addLast(level);
                }
            }
        }

        for (int i = ans.length - 1; i >= 0; i--) {
            System.out.println(ans[i]);
        }
    }

    public static void viewSet(Node root) {
        // levelOrderLineWise(root);
        // leftView(root);
        // rightView(root);
        // Node [] ans = topView(root);
        // Node [] ans = bottomView(root);
        // for(Node ele:ans){
        // System.out.print(ele.data+" ");
        // }
        // verticalView(root);
        // verticalSum(root);
        diagonalView(root);
        

        // System.out.print(minCameraCover(root));
    }

    public static void solve() {
        int[] arr = { 10, 20, 30, -1, -1, 40, -1, -1, 50, 60, 70, -1, -1, 80, 90, -1, -1, -1, 100, -1, -1 };
        Node root = create_Tree(arr);
        display(root);
        viewSet(root);

    }

    public static void main(String[] args) {
        solve();
    }

}