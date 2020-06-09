public class MyQueue {
    private int[] arr;

    private int st = 0;
    private int end = 0;
    private int size = 0;

    MyQueue() {
        arr = new int[10];
    }

    MyQueue(int size) {
        arr = new int[size];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void print() {
        System.out.print("[");
        for (int i = 0; i < this.size; i++) {
            int idx = (this.st + i) % arr.length;
            System.out.print(arr[idx] + ",");
        }

        System.out.println("]");
    }

    public int front() {
        if (this.size == 0) {
            System.err.println("QueueIsEmpty");
            return -1;
        }

        return arr[st];
    }

    public void push(int data) {
        if (this.size == this.arr.length) {
            System.err.println("QueueIsOverFlow");
            return;
        }

        this.arr[this.end] = data;

        this.end = (this.end + 1) % this.arr.length;
        this.size++;
    }

    public int pop() {
        if (this.size == 0) {
            System.err.println("QueueisEmpty");
            return -1;
        }

        int rv = this.arr[this.st];
        this.arr[this.st] = 0;

        this.size--;
        this.st = (this.st + 1) % arr.length;
        return rv;

    }

}