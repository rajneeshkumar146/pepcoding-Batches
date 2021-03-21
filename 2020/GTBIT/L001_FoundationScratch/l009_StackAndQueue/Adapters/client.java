public class client {
    public static void main(String[] args) {
        stackUsingQueue_push que = new stackUsingQueue_push();
        for (int i = 1; i <= 10; i++) {
            que.push(i * 10);
        }

        while (que.size() != 0) {
            System.out.println(que.pop());
        }
    }
}