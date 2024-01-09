import java.util.PriorityQueue;
import java.util.Arrays;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        Arrays.sort(book_time, (o1,o2) -> {
            int s1 = convertTime(o1[0]);
            int s2 = convertTime(o2[0]);
            if(s1 == s2) {
                return Integer.compare(convertTime(o1[1]), convertTime(o2[1]));
            }
            return Integer.compare(s1,s2);
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(String[] book : book_time){
            int start = convertTime(book[0]);
            int end = convertTime(book[1]);
            if(!pq.isEmpty() && start >= pq.peek() + 10){
                pq.poll();
            }   
            pq.offer(end);
        }
        return pq.size();
    }
    
    private int convertTime(String time){
        return Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]);
    }
}