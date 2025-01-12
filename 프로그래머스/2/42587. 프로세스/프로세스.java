import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    private static class Process{
        int index, priority;
        public Process(int index, int priority){
            this.index = index;
            this.priority = priority;
        }
    }
    
    public int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> o2-o1);
        pq.addAll(Arrays.stream(priorities).boxed().collect(Collectors.toList()));
        
        Queue<Process> q = new LinkedList<>();
        
        for(int i=0;i<priorities.length;i++){
            q.offer(new Process(i, priorities[i]));
        }
        
        while(!q.isEmpty()){
            Process now = q.poll();
            if(now.priority == pq.peek()){
                pq.poll();
                answer++;
                if(now.index == location){
                    break;
                }
            }
            else{
                q.offer(now);

            }
        }
        return answer;
    }
}