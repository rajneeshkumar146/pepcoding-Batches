import java.util.*;

public class heap {
    private boolean isMaxHeap = true;
    private ArrayList<Integer> arr;

    // constructor==================================================

    public heap() {
        this(new int[0], true);
    }

    public heap(int[] data) {
        this(data, true);
    }

    // O(N)
    public heap(int[] data, boolean isMaxHeap) {
        this.arr = new ArrayList<>();
        this.isMaxHeap = isMaxHeap;
        for (int ele : data)
            this.arr.add(ele);

        // O(NLogN) -> O(N)
        for (int i = this.arr.size() - 1; i >= 0; i--) {
            downheapify(i, this.arr.size() - 1);
        }
    }

    // basic functions===========================================
    // O(1)
    public int size() {
        return this.arr.size();
    }

    // O(1)
    public boolean isEmpty() {
        return this.arr.size() == 0;
    }

    // private heap helpers============================================

    // O(1)
    private void swap(int i, int j) {
        int eith = this.arr.get(i);
        int ejth = this.arr.get(j);

        this.arr.set(i, ejth);
        this.arr.set(j, eith);
    }

    private int compareTo(int i, int j, boolean isMaxHeap) {
        if (isMaxHeap) {
            return this.arr.get(i) - this.arr.get(j);
        } else {
            return this.arr.get(j) - this.arr.get(i);
        }
    }

    // O(LogN)
    private void downheapify(int pi, int ei) {
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;
        int maxIdx = pi;

        if (lci <= ei && compareTo(lci, maxIdx, isMaxHeap) > 0)
            maxIdx = lci;
        if (rci <= ei && compareTo(rci, maxIdx, isMaxHeap) > 0)
            maxIdx = rci;

        if (pi != maxIdx) {
            swap(pi, maxIdx);
            downheapify(maxIdx, ei);

        }
    }

    // O(LogN)
    private void upheapify(int ci) {
        int pi = (ci - 1) / 2;
        if (pi >= 0 && compareTo(ci, pi, isMaxHeap) > 0) {
            swap(pi, ci);
            upheapify(pi);
        }
    }

    // heap functions====================================================

    public void add(int data) {
        this.arr.add(data);
        upheapify(this.arr.size() - 1);
    }

    // log(N)
    public int remove() {
        int rele = this.arr.get(0);

        swap(0, this.arr.size() - 1);
        this.arr.remove(this.arr.size() - 1);

        downheapify(0, this.arr.size() - 1);

        return rele;
    }

    // O(1)
    public int top() {
        return this.arr.get(0);
    }
}