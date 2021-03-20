public class dynamicStack extends stack {

    public dynamicStack() {
        super();
    }

    public dynamicStack(int size) {
        super(size);
    }

    public dynamicStack(int[] arr) {
        super.intializeVariables(arr.length);

        for (int ele : arr)
            super.push_(ele);
    }

    protected void push_(int data) {
        if (super.capacity == super.elementCount) {
            int[] temp = super.arr;
            super.intializeVariables(2 * super.capacity);
            for (int ele : temp)
                super.push_(ele);
        }

        super.push_(data);
    }

    public void push(int data) {
        push_(data);
    }

}