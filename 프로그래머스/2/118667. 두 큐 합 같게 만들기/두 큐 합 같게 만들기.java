import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.stream.Collectors;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        Queue<Integer> q1 = new LinkedList<>(Arrays.stream(queue1).boxed().collect(Collectors.toList()));
        Queue<Integer> q2 = new LinkedList<>(Arrays.stream(queue2).boxed().collect(Collectors.toList()));
        long q1Sum = 0, q2Sum = 0;
        for(int i=0;i<queue1.length;i++){
            q1Sum += queue1[i];
            q2Sum += queue2[i];
        }
        
        while(q1Sum != q2Sum){
            if(queue1.length * 3 < answer){
                return -1;
            }
            if(q1Sum > q2Sum){
                int num = q1.poll();
                q2.offer(num);
                q1Sum -= num;
                q2Sum += num;
            }
            else {
                int num = q2.poll();
                q1.offer(num);
                q1Sum += num;
                q2Sum -= num;
            }
            answer++;
        }
        
        
        return answer;
    }
}