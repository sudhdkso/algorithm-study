import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;
        int x = 0;
        StringBuilder sb = new StringBuilder(s);
        if(isCorrBracket(s)){
            answer++;
        }
        
        for(int i=1;i<s.length();i++){
            char ch = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(ch);
            if(isCorrBracket(sb.toString())){
                answer++;
            }
        }
        
        return answer;
    }
    
    private static boolean isCorrBracket(String s){
        char[] arr = s.toCharArray();
        
        Stack<Character> bracket = new Stack<>();
        
        for(char ch : arr){
            if(ch == '[' || ch == '(' || ch == '{'){
                bracket.add(ch);
            } else {
                if(bracket.isEmpty()){
                    return false;
                }
                else if(bracket.peek() == '[' && ch == ']'){
                    bracket.pop();
                }
                else if(bracket.peek() == '(' && ch == ')'){
                    bracket.pop();
                }
                else if(bracket.peek() == '{' && ch == '}'){
                    bracket.pop();
                }    
            }
            
        }
        
        return bracket.isEmpty();
    }
}