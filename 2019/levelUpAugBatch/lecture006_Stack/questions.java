import java.util.ArrayList;
public class questions{
    //402
    public String removeKdigits(String num, int k) {
        int n = num.length();
        ArrayList<Character> st= new ArrayList<>();

        for(int i=0;i<n;i++){
            char ch = num.charAt(i);
            while(st.size() != 0 && k > 0 && st.get(st.size()-1) > ch){
                k--;
                st.remove(st.size()-1);
            }

            if(st.size() == 0 && ch == '0') continue;
            st.add(ch);
        }

        while(st.size() != 0 && k-- > 0) st.remove(st.size()-1);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<st.size();i++) sb.append(st.get(i));

        return sb.length() == 0 ? "0":sb.toString();
    }

    class MinStack {
        Stack<Long> st = new Stack<>();
        long globalMin = 0; 
    
        public MinStack() {
            
        }
        
        public void push(int x) {
            long val = x;
            if(st.size()==0){
                st.push(val);
                globalMin = val;
                return;
            }
            
            if(val < globalMin){
                st.push((val - globalMin) + val);
                globalMin = val;
            }else{
                st.push(val);
            }
        }
        
        public void pop() {
            if(st.peek() < globalMin){
                globalMin = (globalMin - st.peek()) + globalMin;
            }
            
            st.pop();
            
        }
        
        public int top() {
            if(st.peek() < globalMin) return (int)globalMin; 
                
            return (int)((long)st.peek());
        }
        
        public int getMin() {
            return (int)globalMin;
        }
    }
}