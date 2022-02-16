public class dynamicStack extends stack {
    public dynamicStack(int size) {
        super(size);
    }

    public dynamicStack() {
        super();
    }

    @Override
    public void push(int val) throws Exception {
        if (super.sizeOfArray() == super.size()) {
            int[] temp = new int[super.size()];
            int idx = super.size() - 1;
            while (super.size() != 0)
                temp[idx--] = super.pop();

            super.initilize(super.sizeOfArray() * 2);

            for (int i = 0; i < temp.length; i++)
                super.push(temp[i]);
        }

        super.push(val);
    }

}