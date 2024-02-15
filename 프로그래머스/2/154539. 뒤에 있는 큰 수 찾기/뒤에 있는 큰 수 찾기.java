import java.util.Stack;
import java.util.Arrays;
class Solution {
    private class Pair{
        int index, value;
        public Pair(int index, int value){
            this.index = index;
            this.value = value;
        }
    }
    
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Pair> s = new Stack<>();
        
        Arrays.fill(answer, -1);
        
        for(int i=0;i<numbers.length;i++){
            int next = numbers[i];
            while(!s.isEmpty() && s.peek().value < next){
                answer[s.pop().index] = next;
            }
            s.push(new Pair(i, next));
        }
        
        return answer;
    }
}