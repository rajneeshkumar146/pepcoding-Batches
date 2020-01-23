import java.util.LinkedList;
import java.util.Stack;

public class SNQ {
    public static void main(String[] args) {
        System.out.println(infixEval("8+4*3-9/3^(2-1)"));
    }

    public static boolean isOperator(Character ch) {

        if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^')
            return true;
        return false;
    }

    public static int priority(Character ch) {
        if (ch == '+' || ch == '-')
            return 0;
        else if (ch == '*' || ch == '/')
            return 1;
        else if (ch == '^')
            return 2;

        return -1;
    }

    public static int performOperation(int val1, int val2, Character ch) {

        if (ch == '+')
            return val2 + val1;
        else if (ch == '-')
            return val2 - val1;
        else if (ch == '*')
            return val2 * val1;
        else if (ch == '/')
            return val2 / val1;
        else
            return (int) Math.pow(val2, val1);

    }

    public static int infixEval(String str) {
        Stack<Integer> numSt = new Stack<>();
        Stack<Character> opSt = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9')
                numSt.push(ch - '0');
            else if (ch == '(')
                opSt.push(ch);
            else if (isOperator(ch)) {
                while (opSt.size() != 0 && opSt.peek() != '(' && priority(opSt.peek()) > priority(ch)) {
                    int val1 = numSt.pop();
                    int val2 = numSt.pop();
                    char c = opSt.pop();

                    int ans = performOperation(val1, val2, c);
                    numSt.push(ans);
                }
                opSt.push(ch);
            } else {
                while (opSt.peek() != '(') {
                    int val1 = numSt.pop();
                    int val2 = numSt.pop();
                    char c = opSt.pop();

                    int ans = performOperation(val1, val2, c);
                    numSt.push(ans);
                }

                opSt.pop();
            }
        }

        while (opSt.size() != 0) {
            int val1 = numSt.pop();
            int val2 = numSt.pop();
            Character c = opSt.pop();

            int ans = performOperation(val1, val2, c);
            numSt.push(ans);

        }

        return numSt.pop();
    }

}