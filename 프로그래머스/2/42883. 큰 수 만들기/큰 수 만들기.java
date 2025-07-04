import java.util.Stack;

class Solution {
    public String solution(String number, int k) {
        Stack<Character> s = new Stack<>();
        String answer = "";
        
        for(char ch : number.toCharArray()){
            while(!s.isEmpty() && s.peek() < ch && k > 0){
                s.pop();
                k--;
            }
            s.push(ch);
        }
        for(int i=0;i<s.size() - k;i++){
            answer += s.get(i);
        }
        return answer;
    }
}