import java.util.Stack;
import java.util.Arrays;
public class questionStack{

    public int[] ngor(int[] arr){
        int n = arr.length();
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans,n);

        for(int i=0;i<n;i++){
            while(st.size()!=0 && arr[st.peek()] < arr[i])
                ans[st.pop()] = i;
            
            st.push(i);
        }

        return ans;
    }

    public int[] ngol(int[] arr){
        int n = arr.length();
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans,-1);

        for(int i= n-1;i>=0;i--){
            while(st.size()!=0 && arr[st.peek()] < arr[i])
                ans[st.pop()] = i;
            
            st.push(i);
        }

        return ans;
    }

    
    public int[] nsor(int[] arr){
        int n = arr.length();
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans,n);

        for(int i=0;i<n;i++){
            while(st.size()!=0 && arr[st.peek()] > arr[i])
                ans[st.pop()] = i;
            
            st.push(i);
        }

        return ans;
    }

    public int[] nsol(int[] arr){
        int n = arr.length();
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans,-1);

        for(int i= n-1;i>=0;i--){
            while(st.size()!=0 && arr[st.peek()] > arr[i])
                ans[st.pop()] = i;
            
            st.push(i);
        }

        return ans;
    }

    //Leetcode 20
    public boolean isValid(String str) {
        Stack<Character> st = new Stack<>();
        int n = str.length();

        for(int i=0;i<n;i++){
            char ch = str.charAt(i);

            if(ch == '(' || ch == '[' || ch == '{') st.push(ch);
            else{
                if(st.size()==0) return false;  // more no of closing brackets
                else if(ch == ')' && st.peek() != '(') return false;
                else if(ch == ']' && st.peek() != '[') return false;
                else if(ch == '}' && st.peek() != '{') return false;
                else st.pop();
            }
        }

        return st.size()==0;
    }

    //Leetcode 1021
    public String removeOuterParentheses(String str) {
        int n = str.length();
        StringBuilder sb = new StringBuilder();

        int ob = 0;
        for(int i=0;i<n;i++){
            char ch = str.charAt(i);

            if(ch =='(' && ob++ > 0) sb.append('(');
            else if(ch==')' && ob-- > 1) sb.append(')'); 
        }

        return sb.toString();
    }

    public int[] nextGreaterElements(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans,-1);

        for(int i=0;i < n * 2;i++){
            int idx = i % n; 
            while(st.size()!=0 && arr[st.peek()] < arr[idx])
                ans[st.pop()] = arr[idx];
            
            if(i < n)
               st.push(idx);
        }

        return ans;
    }

    //Leetcode 921
    public int minAddToMakeValid(String str) {
        int obr = 0; // opening brackets requir.
        int cbr = 0; // closing brackets requir.

        int n = str.length();
        for(int i=0;i<n;i++){
            char ch = str.charAt(i);
            if( ch == '(') cbr++;
            else if(cbr>0) cbr--;
            else obr++;
        }

        return obr + cbr;
    }

    public int minAddToMakeValid_02(String str) {
        Stack<Character> st = new Stack<>();
        int n = str.length();
        for(int i=0;i<n;i++){
            char ch = str.charAt(i);
            if(st.size()!=0 && st.peek() == '(' && ch ==')') st.pop();
            else st.push(ch);
        }

        return st.size();
    }

    //Leetcode 32
    public int longestValidParentheses(String str) {
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int n = str.length();
        int len = 0;

        for(int i=0;i<n;i++){
            if(st.peek() != -1 && str.charAt(st.peek())=='(' && str.charAt(i)==')'){
                st.pop();
                len = Math.max(len,i-st.peek());
            }else st.push(i);
        }

        return len;
    }

    //Leetcode 1249
    public String minRemoveToMakeValid(String str) {
        Stack<Integer> st = new Stack<>();
        int n = str.length();
        StringBuilder sb = new StringBuilder(str);
        for(int i=0;i<n;i++){
            char ch = str.charAt(i);
            if(ch == ')'){
                if(st.size()!=0) st.pop();
                else sb.setCharAt(i,'#');
            }else if(ch == '(')
               st.push(i);
        }

        while(st.size()!=0){
            int i = st.pop();
            sb.setCharAt(i,'#');
        }

        StringBuilder ans = new StringBuilder();
        for(int i=0;i<n;i++){
            char ch = sb.charAt(i);
            if(ch!='#') ans.append(ch);
        }

        return ans.toString();
    }
    public static int[] asteroidCollision(int[] arr) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > 0) st.push(arr[i]);
            else{

                while(st.size()!=0 && st.peek() > 0 && st.peek() < -arr[i]) st.pop();

                if(st.size()!=0 && st.peek() == -arr[i]) st.pop();
                else if(st.size() == 0 || st.peek() < 0 ) st.push(arr[i]);
                else{
                    // astroid will distroy
                }
            }
        }

        int[] ans = new int[st.size()];
        int i = st.size()-1;
        while(st.size()!=0) ans[i--] = st.pop();

        return ans;
    }

    //Leetcode 84
    public int largestRectangleArea(int[] heights) {
        int[] nsol = nsol(heights);
        int[] nsor = nsor(heights);

        int area = 0;
        for(int i=0;i<heights.length;i++){
            int w = nsor[i] - nsol[i] -1;
            int h heights[i];
            area = Math.max(area,w*h);
        }

        return area;
    }

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int area = 0;
        for(int i=0;i<heights.length;i++){
            while(st.peek() != -1 && heights[st.peek()] > heights[i]){
                int idx = st.pop();
                int w = i - st.peek() - 1;
                area = Math.max(area, w * heights[idx]);
            }
            st.push(i);
        }

        while(st.peek() !=-1){
            int idx = st.pop();
            int w = heights.length - st.peek() - 1;
            area = Math.max(area, w * heights[idx]);
        }

        return area;
    }

    public int maximalRectangle(char[][] matrix) {
     if( matrix.length == 0 ||  matrix[0].length == 0)  return 0;
     int n = matrix.length;
     int m = matrix[0].length;   

     int[] heights = new int[m];
     int area = 0;
     for(int i=0;i<n;i++){
         for(int j=0;j<m;j++){
            heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
         }

         area = Math.max(area, largestRectangleArea(heights));
     }
     
     return area;
    }


    public int trap(int[] height) {
      if(height.length == 0) return 0;
      int n = heights.length;

      int[] lmax = new int[n];
      int[] rmax = new int[n];
      
      int prev = 0;
      for(int i=0;i<n;i++){
          lmax[i]= Math.max(prev,height[i]);
          prev = lmax[i];
      }

      prev = 0;
      for(int i=n-1;i>=0;i--){
          rmax[i]= Math.max(prev,height[i]);
          prev = rmax[i];
      }


      int water = 0;
      int prev = 0;
      for(int i=0;i<n;i++){
          h = Math.min(lmax[i],rmax[i]);
          water += h-height[i];
      }

      return water;
    }

    public int trap(int[] height) {
        if(height.length == 0) return 0;
        int n = height.length;
  
        Stack<Integer> st = new Stack<>();
        int water = 0;
        for(int i=0;i<n;i++){
            while(st.size()!=0 && height[st.peek()] <= height[i]){
                int h = height[st.peek()];
                st.pop();

                if(st.size()==0) break;

                int W = (i - st.peek() - 1);
                
                int h1 = height[i];
                int h2 = height[st.peek()];
                int H = Math.min(h1,h2) - h;
                
                water += H * W;   
            }

            st.push(i);
        }

        return water;
    }

    public int trap(int[] height) {
        if(height.length == 0) return 0;
        int n = height.length;
  
        int water = 0, lmax = 0,rmax = 0, li = 0, ri = n-1;
        while(li < ri){
            lmax = Math.max(lmax,height[li]);
            rmax = Math.max(rmax,height[ri]);

            if(lmax <= rmax) water += lmax - height[li++];
            else water += rmax - height[ri--];
        }

        return water;
    }

    class MinStack {
        Stack<Long> st = new Stack<>();
        long minSf = 0; 
        public MinStack() {
            
        }
        
        public void push(int val) {
            long x= val;
            if(st.size()==0){
                st.push(x);
                minSf = x;
                return;
            }

            if(x < minSf){
                st.push( ( x - minSf ) + x);
                minSf = x;
            }else{
                st.push(x);
            }
        }
        
        public void pop() {   
            if(st.peek() < minSf){
                minSf = (minSf - st.peek()) + minSf;
            }

            st.pop();
        }
        
        public int top() {
            if(st.peek() < minSf) return (int)minSf;
            return (int)((long)st.peek());
        }
        
        public int getMin() {
            return (int)minSf;
        }
    }

    // 134
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int extraGas = 0;
        int sp = 0;
        int dificit =0;
        
        for(int i=0;i<gas.length;i++){
            extraGas += gas[i] - cost[i];
            if(extraGas < 0){
                dificit += extraGas;
                extraGas = 0;
                sp = i + 1;
            }    
        }

        return (sp == gas.length || extraGas + dificit < 0) ? -1 : sp;
    }
}
