class Solution {
    public int solution(int storey) {
        int answer = 0;

        while(storey != 0){
            int nextNumber = (storey%100)/10;
            int current = storey%10;
            storey/=10;
            if(current > 5 || (current == 5 && nextNumber >= 5)){
                storey++;
                answer += (10-current);
            }
            else {  
                answer += current;
            }

        }
        return answer;
    }
}