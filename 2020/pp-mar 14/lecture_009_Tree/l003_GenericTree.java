import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class l003_GenericTree {

    public static class Node {
        int data = 0;
        ArrayList<Node> childs;

        Node(int data) {
            this.data = data;
            this.childs = new ArrayList<>();
        }
    }

    public static int height(Node root) {
        int h = -1;
        for (Node child : root.childs)
            h = Math.max(height(child), h);

        return h + 1;
    }

    public static int size(Node root) {
        int count = 0;
        for (int i = 0; i < root.childs.size(); i++) {
            Node child = root.childs.get(i);
            count += size(child);
        }

        return count + 1;
    }

    public static int size2(Node root) {
        int count = 0;
        for (Node child : root.childs) {
            count += size(child);
        }

        return count + 1;
    }

    public static int maximum(Node root) {
        int max = root.data;
        for (Node child : root.childs)
            max = Math.max(maximum(child), max);

        return max;
    }

    public static int minimum(Node root) {
        int min = root.data;
        for (Node child : root.childs)
            min = Math.min(minimum(child), min);

        return min;
    }

    public static int sum(Node root) {
        int sum = root.data;
        for (Node child : root.childs)
            sum += sum(child);

        return sum;
    }

    public static boolean find(Node root, int data) {
        if (root.data == data)
            return true;

        boolean res = false;
        for (Node child : root.childs)
            res = res || find(child, data);

        return res;
    }

    public static boolean find2(Node root, int data) {
        if (root.data == data)
            return true;

        boolean res = false;
        for (Node child : root.childs)
            if (find2(child, data)) {
                res = true;
                break;
            }

        return res;
    }

    public static int countLeaves(Node node) {
        if (node.childs.size() == 0)
            return 1;

        int count = 0;
        for (Node child : node.childs)
            count += countLeaves(child);

        return count;
    }

    public static boolean nodeToRootPath(Node root, int data, ArrayList<Node> ans) {

        if (root.data == data) {
            ans.add(root);
            return true;
        }

        boolean res = false;
        for (Node child : root.childs)
            res = res || nodeToRootPath(child, data, ans);

        if (res)
            ans.add(root);
        return res;
    }

    public static ArrayList<Node> nodeToRootPath(Node root, int data) {
        if (root.data == data) {
            ArrayList<Node> base = new ArrayList<>();
            base.add(root);
            return base;
        }

        ArrayList<Node> smallAns = new ArrayList<>();
        for (Node child : root.childs) {
            smallAns = nodeToRootPath(child, data);
            if (smallAns.size() != 0)
                break;
        }
        if (smallAns.size() != 0)
            smallAns.add(root);
        return smallAns;
    }

    public Node lowestCommonAncestor(Node root, int p, int q) {
        ArrayList<Node> list1 = nodeToRootPath(root, p);
        ArrayList<Node> list2 = nodeToRootPath(root, q);

        int i = list1.size() - 1;
        int j = list2.size() - 1;

        Node LCA = null;
        while (i >= 0 && j >= 0) {
            if (list1.get(i) != list2.get(j))
                break;
            LCA = list2.get(j);
            i--;
            j--;

        }

        return LCA;
    }

    public static boolean areSimilar(Node n1, Node n2) {
        if (n1.childs.size() != n2.childs.size())
            return false;

        boolean res = true;
        for (int i = 0; i < n1.childs.size(); i++) {
            Node c1 = n1.childs.get(i);
            Node c2 = n2.childs.get(i);

            res = res && areSimilar(c1, c2);
        }

        return res;
    }

    public static boolean areMirror(Node n1, Node n2) {
        if (n1.childs.size() != n2.childs.size())
            return false;

        boolean res = true;
        int size = n1.childs.size();
        for (int i = 0; i < size; i++) {
            Node c1 = n1.childs.get(i);
            Node c2 = n2.childs.get(size - i - 1);

            res = res && areMirror(c1, c2);
        }

        return res;
    }

    public static boolean IsSymmetric(Node node) {
        return areMirror(node, node);
    }

    static int ceil;
    static int floor;

    public static void ceilAndFloor_(Node node, int data) {
        if (node.data < data)
            floor = Math.max(floor, node.data);
        if (node.data > data)
            ceil = Math.min(ceil, node.data);

        for (Node child : node.childs) {
            ceilAndFloor_(child, data);
        }
    }

    public static void ceilAndFloor(Node node, int data) {
        ceil = (int) 1e9;
        floor = -(int) 1e9;
        ceilAndFloor_(node, data);
    }

    public static int floor(Node node, int ub) {
        int maxRes = -(int) 1e9;
        for (Node child : node.childs) {
            int recRes = floor(child, ub);
            maxRes = Math.max(maxRes, recRes);
        }

        return node.data < ub ? Math.max(node.data, maxRes) : maxRes;
    }

    public static int kthLargest(Node node, int k) {
        int ub = (int) 1e9;
        for (int i = 0; i < k; i++) {
            ub = floor(node, ub);
        }

        return ub;
    }

    public static Node getTail(Node node) {
        while (node.childs.size() != 0) {
            node = node.childs.get(0);
        }

        return node;
    }

    public static void linearize(Node node) {
        for (Node child : node.childs) {
            linearize(child);
        }

        for (int i = node.childs.size() - 1; i > 0; i--) {
            Node tail = getTail(node.childs.get(i - 1));
            tail.childs.add(node.childs.get(i));

            node.childs.remove(i);
        }
    }

    public static void levelOrderLineWise(Node root) {
        LinkedList<Node> que = new LinkedList<>(); // removeFirst, addLast
        que.addLast(root);
        int level = 0;

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                Node rn = que.removeFirst();
                System.out.print(rn.data + " ");

                for (Node child : rn.childs) {
                    que.addLast(child);
                }
            }
            System.out.println();
        }
    }

    public List<List<Integer>> levelOrderLineWise(Node root) {
        LinkedList<Node> que = new LinkedList<>(); // removeFirst, addLast
        que.addLast(root);
        int level = 0;

        List<List<Integer>> ans = new ArrayList<>();
        while (que.size() != 0) {
            int size = que.size();
            List<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) {
                Node rn = que.removeFirst();
                smallAns.add(rn.data);

                for (Node child : rn.childs) {
                    que.addLast(child);
                }
            }
            ans.add(smallAns);
        }

        return ans;
    }

    public List<List<Integer>> levelOrderLineWise(Node root) {
        LinkedList<Node> que = new LinkedList<>(); // removeFirst, addLast
        que.addLast(root);
        int level = 0;

        List<List<Integer>> ans = new ArrayList<>();
        while (que.size() != 0) {
            int size = que.size();
            List<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) {
                Node rn = que.removeFirst();
                smallAns.add(rn.data);

                for (Node child : rn.childs) {
                    que.addLast(child);
                }
            }
            ans.add(smallAns);
        }

        return ans;
    }

    public void zigZag(Node root) {
        if (root == null)
            return;
        LinkedList<Node> que = new LinkedList<>(); // removeFirst, addLast
        LinkedList<Node> st = new LinkedList<>(); // removeFirst, addFirst

        que.addLast(root);
        int level = 0;

        List<List<Integer>> ans = new ArrayList<>();
        while (que.size() != 0 ) {
            int size = que.size();
            List<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) {
                Node rn = que.removeFirst();
                smallAns.add(rn.data);

                if (level % 2 == 0) {
                    for (Node child : rn.childs)
                        st.addFirst(child);
                } else {
                    for (int i = rn.childs.size() - 1; i >= 0; i--) {
                        Node child = rn.childs.get(i);
                        st.addFirst(child);
                    }
                }
            }
            level++;
            ans.add(smallAns);
            LinkedList<Node> temp = que;
            que = st;
            st = temp;
        }

        for (List<Integer> a : ans) {
            for (int ele : a) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }

    }

}