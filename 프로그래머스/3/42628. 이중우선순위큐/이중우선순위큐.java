import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        LinkedList<Integer> dq = new LinkedList<>();
        
        for(String operation : operations){
            String[] op = operation.split(" ");
            if(op[0].equals("I")){
                dq.add(Integer.parseInt(op[1]));
                Collections.sort(dq);
            }
            else {
                if(dq.isEmpty()) continue;
                int num = Integer.parseInt(op[1]);
                if(num > 0){
                    dq.pollLast();
                }
                else {
                    dq.pollFirst();
                }
                
            }
        }
        if(!dq.isEmpty()){
            answer[0] = dq.peekLast();
            answer[1] = dq.peekFirst();
        }
        return answer;
    }
}