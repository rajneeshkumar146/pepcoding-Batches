import java.util.LinkedList;

public class question {
    public static boolean balancedBrackets(String str) {
        LinkedList<Character> st = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{')
                st.addFirst(ch);
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (st.size() == 0) // more closing brackets
                    return false;
                else if (ch == ')' && st.getFirst() != '(')
                    return false;
                else if (ch == ']' && st.getFirst() != '[')
                    return false;
                else if (ch == '}' && st.getFirst() != '{')
                    return false;
                else
                    st.removeFirst();
            }
        }

        return st.size() == 0; // more opening brackets
    }

    public static boolean duplicateBrackets(String str) {
        LinkedList<Character> st = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            boolean isLoopRun = false;
            while (st.size() != 0 && ch == ')' && st.getFirst() != '(') {
                isLoopRun = true;
                st.removeFirst();
            }

            if (!isLoopRun && ch == ')')
                return true;
            else if (isLoopRun)
                st.removeFirst();
            else
                st.addFirst(ch);
        }

        return false;
    }
}