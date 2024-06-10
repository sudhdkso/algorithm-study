import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;

class Solution {
    private class Process implements Comparable<Process>{
        int index;
        int priority;
        public Process(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
        
        @Override
        public int compareTo(Process e) {
            return Integer.compare(e.priority, this.priority);
        }
        
        @Override
        public String toString(){
            return "[index = "+index+" ,priority = "+priority+"]\n";
        }
    }
    
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<Process> q = new LinkedList<>();
        PriorityQueue<Process> pq = new PriorityQueue<>();
        
        for(int i=0;i<priorities.length;i++){
            Process process = new Process(i, priorities[i]);
            q.offer(process);
            pq.offer(process);
        }

        while(!q.isEmpty()){
            if(q.peek().priority < pq.peek().priority) {
                q.offer(q.poll());
            }
            else if(q.peek().priority == pq.peek().priority) {
                answer++;
                if(q.peek().index == location){
                    break;
                }
                q.poll();
                pq.poll();
            }
        }
        
        
        return answer;
    }
}