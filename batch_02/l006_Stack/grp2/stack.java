public class stack {
    private int[] st;
    private int tos = -1;

    protected void resize(int size) {
        this.st = new int[size];
    }

    protected int Max_size() {
        return this.st.length;
    }

    public stack() {
        resize(10);
    }

    public stack(int size) {
        resize(size);
    }

    public stack(int[] arr) {
        resize(2 * arr.length);
        for (int i = 0; i < arr.length; i++) {
            st[i] = arr[i];
        }

        this.tos = arr.length - 1;
    }

    // Api.=======================

    public int size() {
        return this.tos + 1;
    }

    public boolean isEmpty() {
        return this.tos == -1;
    }

    protected void push_(int data) {
        st[++tos] = data;
    }

    public void push(int data) {
        if (this.tos + 1 == st.length) {
            System.out.println("StackOverflow");
            return;
        }

        push_(data);

    }

    public int pop() {
        if (tos == -1) {
            System.out.println("StackIsEmpty");
            return -1;
        }

        return st[tos--];
    }

    public int top() {
        if (tos == -1) {
            System.out.println("StackIsEmpty");
            return -1;
        }
        return st[tos];
    }
}