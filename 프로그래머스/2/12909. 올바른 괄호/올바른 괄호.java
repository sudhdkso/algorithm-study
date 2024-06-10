import java.util.Stack;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Stack<Integer> bracket = new Stack<>();
        char[] ch = s.toCharArray();
        
        for(char a : ch) {
            if(a == '(') {
                bracket.push(1);
            }
            else if(a == ')' && !bracket.isEmpty()){
                bracket.pop();
            }
            else {
                return false;
            }
        }
        
        return bracket.isEmpty();
    }
}