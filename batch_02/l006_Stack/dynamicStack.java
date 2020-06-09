public class dynamicStack extends stack {

    dynamicStack() {
        super();
    }

    dynamicStack(int size) {
        super(size);
    }

    dynamicStack(int[] arr) {
        super(arr);
    }

    @Override
    public void push(int data) {
        if (this.tos + 1 == st.length) {
            int[] temp = st;
            st = new int[2 * temp.length];

            for (int i = 0; i < temp.length; i++) {
                st[i] = temp[i];
            }
        }
        // System.out.println("Hello");
        super.push(data);
    }

    int b = 30;
    int c = 40;

    public void fun2() {
        System.out.println("hi!!!");
    }

    public void fun() {
        System.out.println(b);
        System.out.println("hello Dynamic Stack");
    }

}