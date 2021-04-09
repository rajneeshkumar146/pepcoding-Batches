import java.util.ArrayList;

public class heap {
    private ArrayList<Integer> arr;
    private int size = 0;
    private boolean isMax = true;

    private void intialize(boolean isMax) {
        this.arr = new ArrayList<>();
        this.size = 0;
        this.isMax = isMax;
    }

    public heap() {
        intialize(true);
    }

    public heap(int[] arr, boolean isMax) { // O(n)
        intialize(isMax);
        for (int ele : arr)
            this.arr.add(ele);

        for (int i = this.arr.size() - 1; i >= 0; i--) {
            downHeapify(i);
        }

        this.size = arr.length;
    }

    // General Functions.

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void add(int data) { // O(logN)
        this.arr.add(data);
        this.size++;
        upheapify(this.size - 1);
    }

    public int remove() { // O(logN)
        int n = this.arr.size();
        int rv = this.arr.get(0);

        swap(0, n - 1);
        this.arr.remove(n - 1);
        this.size--;

        downHeapify(0);
        return rv;
    }

    public int peek() { // O(1)
        return this.arr.get(0);
    }

    private int compareTo(int a,int b){
        if(isMax){
            return this.arr.get(a) - this.arr.get(b);
        }else{
            return this.arr.get(b) - this.arr.get(a);
        }

    }

    private void swap(int i, int j) { // O(1)
        int ei = arr.get(i);
        int ej = arr.get(j);

        arr.set(i, ej);
        arr.set(j, ei);
    }

    private void upheapify(int ci) { // O(logn)
        int pi = (ci - 1) / 2;
        if (pi >= 0 && compareTo(ci,pi) > 0) {
            swap(pi, ci);
            upheapify(pi);
        }
    }

    private void downHeapify(int pi) { // O(logn)
        int maxIdx = pi;
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;

        if (lci < this.arr.size() && compareTo(lci,maxIdx) > 0)
            maxIdx = lci;
        if (rci < this.arr.size() && compareTo(rci,maxIdx) > 0)
            maxIdx = rci;

        if (maxIdx != pi) {
            swap(maxIdx, pi);
            downHeapify(maxIdx);
        }
    }

}