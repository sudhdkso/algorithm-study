import java.util.Stack;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> st = new Stack<>();
        
        for(Character ch: s.toCharArray()){
            if(ch == '('){
                st.push('(');
                continue;
            }
            
            if(!st.isEmpty() && ch ==')'){
                st.pop();
            }
            else{
                return false;
            }
        }
    

        return st.isEmpty();
    }
}