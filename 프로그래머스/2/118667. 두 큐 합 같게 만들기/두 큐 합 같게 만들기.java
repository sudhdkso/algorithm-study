import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.stream.Collectors;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        Queue<Integer> q1 = new LinkedList<>(Arrays.stream(queue1).boxed().collect(Collectors.toList()));
        Queue<Integer> q2 = new LinkedList<>(Arrays.stream(queue2).boxed().collect(Collectors.toList()));
        
        long q1sum = Arrays.stream(queue1).sum();
        long q2sum = Arrays.stream(queue2).sum();
        
        while(q1sum != q2sum){
            
            if(queue1.length*3 < answer){
              return -1;
            }
            if(q1sum > q2sum){ 
              int num = q1.poll();
              q2.offer(num);
              q1sum -= num;
              q2sum += num;
            }
            else if(q2sum > q1sum){
                int num = q2.poll();
                q1.offer(num);
                q1sum += num;
                q2sum -= num;
            }
            answer++;
        }
        return answer;
    }
}