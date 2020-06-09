public class client{
    public static void main(String[] args) throws Exception{
    
        QueueOper();
    }

    public static void stackOper() throws Exception{
        stack st=new stack(10);
        st.push(10);
        st.push(20);
        st.push(30);
        st.push(40);
        st.push(50);
        st.push(60);
        System.out.println(st.pop());
        st.display();
    
    }

    public static void QueueOper() throws Exception{
       dynamicQueue que=new dynamicQueue(2);
       que.push(10);
       que.push(20);
       que.push(30);
       que.push(40);
       que.push(50);
       que.push(100);
       que.push(200);
       que.push(300);
       que.push(400);
       que.push(500);

       que.display();
       System.out.println(que.pop());
       que.display();
    }
}