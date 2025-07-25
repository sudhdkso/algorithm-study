import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> o2-o1);
        
        List<Integer> workList = Arrays.stream(works).boxed().collect(Collectors.toList());
        
        pq.addAll(workList);
        
        while(n-- > 0){
            int remain = pq.poll() - 1;
            if(remain < 0) break;
            pq.offer(remain);
            
        }
        
        while(!pq.isEmpty()){
            int now = pq.poll();
            answer += (now*now);
        }
        return answer;
    }
}