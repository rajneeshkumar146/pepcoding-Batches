public class client {
    public static void main(String[] args) {
        queueUsingStack_pop que = new queueUsingStack_pop();
        for (int i = 1; i <= 10; i++) {
            que.add(i * 10);
        }

        while (que.size() != 0) {
            System.out.println(que.remove());
        }
    }
}