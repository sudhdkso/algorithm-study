import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.stream.Collectors;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o2,o1));
        
        pq.addAll(Arrays.stream(works).boxed().collect(Collectors.toList()));
        while(n -- > 0){
            int num = pq.poll()-1;
            if(num < 0){
                break;
            }
            pq.offer(num);
        }
        
        while(!pq.isEmpty()){
            answer += Math.pow(pq.poll(),2);
        }
        return answer;
    }
}