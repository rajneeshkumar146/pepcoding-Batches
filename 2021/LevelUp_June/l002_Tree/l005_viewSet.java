import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class l005_viewSet {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void levelOrderLineWise(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        int level = 0;

        while (que.size() != 0) {
            int size = que.size();
            System.out.print("Level " + level + ": ");
            while (size-- > 0) {
                TreeNode rn = que.removeFirst();

                System.out.print(rn.val + ", ");
                if (rn.left != null)
                    que.addLast(rn.left);
                if (rn.right != null)
                    que.addLast(rn.right);
            }
            level++;
        }
    }

    public static void leftView(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        ArrayList<Integer> ans = new ArrayList<>();
        while (que.size() != 0) {
            int size = que.size();
            ans.add(que.getFirst().val);
            while (size-- > 0) {
                TreeNode rn = que.removeFirst();

                if (rn.left != null)
                    que.addLast(rn.left);
                if (rn.right != null)
                    que.addLast(rn.right);
            }

        }
    }

    public static void rightView(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        ArrayList<Integer> ans = new ArrayList<>();
        while (que.size() != 0) {
            int size = que.size();
            ans.add(que.getFirst().val);
            while (size-- > 0) {
                TreeNode rn = que.removeFirst();

                if (rn.right != null)
                    que.addLast(rn.right);
                if (rn.left != null)
                    que.addLast(rn.left);
            }

        }
    }

    public static class vpair {
        TreeNode node = null;
        int vl = 0;

        vpair(TreeNode node, int vl) {
            this.node = node;
            this.vl = vl;
        }
    }

    // {min,max}
    public static void widthOfShadow(TreeNode root, int vl, int[] minMax) {
        if (root == null)
            return;

        minMax[0] = Math.min(minMax[0], vl);
        minMax[1] = Math.max(minMax[1], vl);

        widthOfShadow(root.left, vl - 1, minMax);
        widthOfShadow(root.right, vl + 1, minMax);
    }

    public static ArrayList<ArrayList<Integer>> verticalOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;

        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;
        for (int i = 0; i < width; i++)
            ans.add(new ArrayList<>());

        LinkedList<vpair> que = new LinkedList<>();
        que.addLast(new vpair(root, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vpair p = que.removeFirst();
                TreeNode node = p.node;
                int vl = p.vl;

                ans.get(vl).add(node.val);
                if (node.left != null)
                    que.addLast(new vpair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vpair(node.right, vl + 1));
            }
        }

        return ans;
    }

    public static ArrayList<Integer> bottomView(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;
        for (int i = 0; i < width; i++)
            ans.add(null);

        LinkedList<vpair> que = new LinkedList<>();
        que.addLast(new vpair(root, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vpair p = que.removeFirst();
                TreeNode node = p.node;
                int vl = p.vl;

                ans.set(vl, node.val);
                if (node.left != null)
                    que.addLast(new vpair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vpair(node.right, vl + 1));
            }
        }

        return ans;
    }

    public static ArrayList<Integer> topView(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;
        for (int i = 0; i < width; i++)
            ans.add(null);

        LinkedList<vpair> que = new LinkedList<>();
        que.addLast(new vpair(root, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vpair p = que.removeFirst();
                TreeNode node = p.node;
                int vl = p.vl;

                if (ans.get(vl) == null)
                    ans.set(vl, node.val);

                if (node.left != null)
                    que.addLast(new vpair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vpair(node.right, vl + 1));
            }
        }

        return ans;
    }

    public static ArrayList<Integer> verticalSum(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;
        for (int i = 0; i < width; i++)
            ans.add(0);

        LinkedList<vpair> que = new LinkedList<>();
        que.addLast(new vpair(root, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vpair p = que.removeFirst();
                TreeNode node = p.node;
                int vl = p.vl;

                ans.set(vl, ans.get(vl) + node.val);

                if (node.left != null)
                    que.addLast(new vpair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vpair(node.right, vl + 1));
            }
        }

        return ans;
    }

    public static ArrayList<ArrayList<Integer>> DigonalOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;

        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int width = 0 - minMax[0] + 1;
        for (int i = 0; i < width; i++)
            ans.add(new ArrayList<>());

        LinkedList<vpair> que = new LinkedList<>();
        que.addLast(new vpair(root, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vpair p = que.removeFirst();
                TreeNode node = p.node;
                int vl = p.vl;

                ans.get(vl).add(node.val);
                if (node.left != null)
                    que.addLast(new vpair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vpair(node.right, vl));
            }
        }

        return ans;
    }

    public static ArrayList<ArrayList<Integer>> DigonalOrder_geeks(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;

        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (que.size() != 0) {
            int size = que.size();

            ArrayList<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) {
                TreeNode rn = que.removeFirst();
                while (rn != null) {
                    smallAns.add(rn.val);
                    if (rn.left != null)
                        que.addLast(rn.left);

                    rn = rn.right;
                }
            }

            ans.add(smallAns);
        }

        return ans;
    }

    public static ArrayList<Integer> DigonalSum_geeks(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (que.size() != 0) {
            int size = que.size();

            int sum = 0;
            while (size-- > 0) {
                TreeNode rn = que.removeFirst();
                while (rn != null) {
                    sum += rn.val;
                    if (rn.left != null)
                        que.addLast(rn.left);

                    rn = rn.right;
                }
            }

            ans.add(sum);
        }

        return ans;
    }

    public static class v2pair {
        TreeNode node = null;
        int vl = 0;
        int hl = 0;

        v2pair(TreeNode node, int hl, int vl) {
            this.node = node;
            this.hl = hl;
            this.vl = vl;
        }
    }

    public static ArrayList<ArrayList<Integer>> VerticalOrder_ii(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;

        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int width = 0 - minMax[0] + 1;
        for (int i = 0; i < width; i++)
            ans.add(new ArrayList<>());

        PriorityQueue<v2pair> que = new PriorityQueue<>();
        que.add(new v2pair(root, 0, Math.abs(minMax[0])));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                v2pair p = que.remove();
                TreeNode node = p.node;
                int hl = p.hl;
                int vl = p.vl;

                ans.get(vl).add(node.val);
                if (node.left != null)
                    que.add(new v2pair(node.left, hl + 1, vl - 1));
                if (node.right != null)
                    que.add(new v2pair(node.right, hl + 1, vl + 1));
            }
        }

        return ans;
    }

}