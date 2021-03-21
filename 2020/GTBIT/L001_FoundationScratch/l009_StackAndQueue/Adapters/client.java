public class client {
    public static void main(String[] args) {
        queueUsingStack_push que = new queueUsingStack_push();
        for (int i = 1; i <= 10; i++) {
            que.add(i * 10);
        }

        while (que.size() != 0) {
            System.out.println(que.remove());
        }
    }
}