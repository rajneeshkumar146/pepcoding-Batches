public class client{
    public static void main(String[] args) throws Exception{
        int[] arr = {10,20,30,40};
        DStack st = new DStack(arr);
        st.push(100);
        st.push(100);
        st.push(110);
        st.push(130);
        st.top();


        System.out.println(st);
    }
}