import java.util.PriorityQueue;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        List<Integer> scovilleList = IntStream.of(scoville)
                                             .boxed()
                                             .collect(Collectors.toList());
        pq.addAll(scovilleList);
        
        while(pq.peek() < K && pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            pq.offer(first + (second*2));
            answer++;
        }
        
        return pq.peek() < K ? -1 : answer;
    }
}