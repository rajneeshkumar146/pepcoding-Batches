public class client{
    public static void main(String[] args) throws Exception{
        exper1();
    }

    public static void exper1() throws Exception{
        DStack<String> st = new DStack<>();
        for(int i = 0;i<10;i++){
            st.push((char)(Math.random()*10 + 'A')  + "pep");
        }

        System.out.println(st);

        st.pop();

        System.out.println(st);

    }
}