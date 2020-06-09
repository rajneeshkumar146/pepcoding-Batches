public class stack {
    private int[] st;
    private int tos = -1;
    private int size = 0;

    // constructor's.------------------------

    protected void resize(int size) {
        this.st = new int[size];
        this.tos = -1;
        this.size = 0;

    }

    protected int maxSize() {
        return this.st.length;
    }

    public stack() {
        resize(10);
    }

    public stack(int size) {
        resize(size);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    protected void push_(int val) {
        this.st[++tos] = val;
        this.size++;
    }

    public void push(int val) throws Exception {

        System.out.println("Hello");
        if (this.size == maxSize()) {
            throw new Exception("StackOverFlow");
        }

        push_(val);
    }

    protected void pop_() {
        this.st[this.tos] = 0;
        this.tos--;
        this.size--;
    }

    public void pop() throws Exception {
        if (this.size == 0) {
            throw new Exception("StackIsEmpty: " + -1);
        }

        pop_();

    }

    protected int top_() {
        return this.st[this.tos];
    }

    public int top() throws Exception {
        if (this.size == 0) {
            throw new Exception("StackIsEmpty: " + -1);
        }

        return top_();
    }

}