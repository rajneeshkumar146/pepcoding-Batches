import java.util.ArrayList;

public class heap {
    private ArrayList<Integer> arr;
    private boolean isMax = true;

    public heap(boolean isMax) {
        this.arr = new ArrayList<>();
        this.isMax = isMax;
    }

    // O(n + nLog(n)) -> O(n)
    public heap(int[] arr, boolean isMax) {
        this(isMax);
        for (int ele : arr)
            this.arr.add(ele);

        for (int i = this.arr.size() - 1; i >= 0; i--)
            downHeapify(i);
    }

    // O(1)
    public boolean compareTo(int x, int y) {
        if (isMax)
            return this.arr.get(x) > this.arr.get(y);
        else
            return this.arr.get(y) > this.arr.get(x);
    }

    // O(1)
    private void swap(int x, int y) {
        int v1 = this.arr.get(x);
        int v2 = this.arr.get(y);

        this.arr.set(x, v2);
        this.arr.set(y, v1);
    }

    // Log(n)
    private void downHeapify(int pi) {
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;
        int maxIdx = pi;

        if (lci < arr.size() && compareTo(lci, maxIdx))
            maxIdx = lci;

        if (rci < arr.size() && compareTo(rci, maxIdx))
            maxIdx = rci;

        if (pi != maxIdx) {
            swap(maxIdx, pi);
            downHeapify(maxIdx);
        }
    }

    // Log(n)
    private void upHeapify(int ci) {
        int pi = (ci - 1) / 2;
        if (pi >= 0 && compareTo(ci, pi)) {
            swap(ci, pi);
            upHeapify(pi);
        }
    }

    public int size() {
        return this.arr.size();
    }

    // Log(n)
    public int remove() {
        int re = this.arr.get(0);
        swap(0, this.arr.size() - 1);
        this.arr.remove(this.arr.size() - 1);
        downHeapify(0);
        return re;
    }

    // Log(n)
    public void add(int data) {
        this.arr.add(data);
        upHeapify(this.arr.size() - 1);
    }

    // O(1)
    public int peek() {
        return this.arr.get(0);
    }

    public static void input(ArrayList<ArrayList<Integer>> list) {
        int n = list.size(), m = list.get(0).size();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = list.get(i).get(j);
            }
        }
    }

}