import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        int[] deploy = new int[101];
        int day = 0;
        for(int i=0;i<progresses.length;i++){
            while(progresses[i] + (day*speeds[i]) < 100) {
                day++;
            }
            deploy[day]++;
        }
        
        return Arrays.stream(deploy)
            .filter(i -> i != 0)
            .toArray();
    }
}