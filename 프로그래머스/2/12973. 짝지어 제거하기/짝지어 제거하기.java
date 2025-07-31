import java.util.Stack;

class Solution
{
    public int solution(String s)
    {
        int answer = -1;

        Stack<Character> st = new Stack<>();
        
        for(Character ch : s.toCharArray()){
            if(st.isEmpty() || st.peek() != ch){
                st.push(ch);
            }
            else if(st.peek() == ch){
                st.pop();
            }
        }
        
        return st.isEmpty() ? 1 : 0;
    }
}