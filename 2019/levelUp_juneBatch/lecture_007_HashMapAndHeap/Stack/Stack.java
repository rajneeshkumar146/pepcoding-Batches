public class Stack{
        private int[] st;
        private int tos = -1;
        private int size = 0;

        Stack(){
            assignSize(10);
        }

        Stack(int n){
            assignSize(n);
        }

        protected void assignSize(int n){
            this.st = new int[n];
        }
        
        public boolean isEmpty(){
            return this.size == 0;
        }

        public int size(){
            return this.size;
        }

        protected int capacity(){
            return this.st.length;
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            for(int i = 0; i < this.size ;i++){
                sb.append(this.st[i]);
                if(i != this.size() -1 )
                   sb.append(',');
            }

            sb.append("]");
            return sb.toString();
        }

        public void push(int val) throws Exception{
            if(this.size == this.st.length){
                throw new Exception("StackIsFULL");
            }

            push_(val);
        }

        protected void push_(int val){
            this.st[++this.tos] = val;
            this.size++;
        }

        protected int top() throws Exception{
            if(this.size == 0){
                throw new Exception("NullPointerException: " + -1);
            }

            return this.st[this.tos];
        }


        public int pop()throws Exception{
            if(this.size == 0){
                throw new Exception("NullPointerException: " + -1);
            }

            int rv = this.st[this.tos];
            this.st[this.tos--] = 0;
            this.size--;

            return rv;
        }
}
