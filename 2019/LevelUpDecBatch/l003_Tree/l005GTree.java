import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class l005GTree {

    public static class Node {
        int val = 0;
        ArrayList<Node> childs;

        Node(int val) {
            this.val = val;
            this.childs = new ArrayList<>();
        }
    }

    public static int size(Node node) {
        // if (node == null)
        // return 0;

        int sz = 0;
        for (Node child : node.childs) {
            sz += size(child);
        }

        return sz + 1;
    }

    // Height on the basis of Edge
    public static int height(Node node) {
        int h = -1;
        for (Node child : node.childs) {
            h = Math.max(h, height(child));
        }

        return h + 1;
    }

    public static int maximum(Node node) {
        int maxEle = node.val;
        for (Node child : node.childs) {
            maxEle = Math.max(maxEle, maximum(child));
        }

        return maxEle;
    }

    public static boolean find(Node node, int data) {
        if (node.val == data)
            return true;

        for (Node child : node.childs) {
            if (find(child, data))
                return true;
        }

        return false;
    }

    public static int find01(Node node, int data) {
        if (node.val == data)
            return 0;

        int depth = -1;
        for (Node child : node.childs) {
            depth = find01(child, data);
            if (depth != -1)
                break;
        }

        if (depth != -1)
            depth++;

        return depth;
    }

    public static boolean rootToNodePath(Node root, int data, ArrayList<Node> ans) {
        if (root.val == data) {
            ans.add(root);
            return true;
        }

        boolean res = false;
        for (Node child : root.childs) {
            res = res || rootToNodePath(child, data, ans);
            // if(res) break;
        }

        if (res)
            ans.add(root);

        return res;
    }

    public static ArrayList<Node> rootToNodePath(Node root, int data) {
        ArrayList<Node> ans = new ArrayList<>();
        rootToNodePath(root, data, ans);

        return ans;

    }

    public static Node LCA(Node root, int d1, int d2) {
        ArrayList<Node> l1 = new ArrayList<>();
        rootToNodePath(root, d1, l1);

        ArrayList<Node> l2 = new ArrayList<>();
        rootToNodePath(root, d2, l2);

        int i = l1.size() - 1;
        int j = l2.size() - 1;

        Node LCA = null;
        while (i >= 0 && j >= 0) {
            if (l1.get(i) != l2.get(j))
                break;

            LCA = l1.get(i);
            i--;
            j--;
        }

        return LCA;
    }

    public void kDown(Node root, Node blockNode, int time, List<List<Integer>> ans) {
        if (root == blockNode)
            return;

        if (ans.size() == time)
            ans.add(new ArrayList<>());

        ans.get(time).add(root.val);
        for (Node child : root.childs)
            kDown(child, blockNode, time + 1, ans);

    }

    // Method_01
    public List<List<Integer>> burningTree_01(Node root, int target) {
        ArrayList<Node> list = new ArrayList<>();
        rootToNodePath(root, target, list);

        List<List<Integer>> ans = new ArrayList<>();
        Node blockNode = null;

        for (int i = 0; i < list.size(); i++) {
            kDown(list.get(i), blockNode, i, ans);
            blockNode = list.get(i);
        }

        return ans;
    }

    // Method_02
    public int burningTree_02(Node root, int target, List<List<Integer>> ans) {
        if (root.val == target) {
            kDown(root, null, 0, ans);
            return 1;
        }

        int time = -1;
        Node blockNode = null;
        for (Node child : root.childs) {
            time = burningTree_02(child, target, ans);
            if (time != -1) {
                blockNode = child;
                break;
            }
        }

        if (time != -1) {
            kDown(root, blockNode, time, ans);
            time++;
        }
        return time;
    }

    public static void lineWiseLevelOrder(Node root) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        int level = 0;

        while (que.size() != 0) {
            int sz = que.size();
            System.out.print("Level : " + level + " -> ");
            while (sz-- > 0) {
                Node rn = que.removeFirst();
                System.out.print(rn.val + ", ");

                for (Node child : rn.childs) {
                    que.addLast(child);
                }

            }

            level++;
            System.out.println();
        }
    }

    public static boolean isMirror(Node node1, Node node2) {
        if (node1.childs.size() != node2.childs.size())
            return false;
        if (node1.val != node2.val)
            return false;

        for (int i = 0, j = node1.childs.size() - 1; j >= 0; i++, j--) {
            Node child1 = node1.childs.get(i);
            Node child2 = node2.childs.get(j);
            if (!isMirror(child1, child2))
                return false;
        }

        return true;

    }

    // diameter of GT

    public static Node flattern(Node node) {
        if (node.childs.size() == 0)
            return node;

        int n = node.childs.size();
        Node lchild = node.childs.get(n - 1);
        Node gTail = flattern(lchild);

        for (int i = n - 2; i >= 0; i--) {
            Node tempTail = flattern(node.childs.get(i));
            tempTail.childs.add(node.childs.get(i + 1));
            node.childs.remove(i + 1);
        }

        return gTail;
    }

    public static void main(String[] args) {

    }
}