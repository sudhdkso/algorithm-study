
import java.util.Arrays;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String convert = Integer.toString(n,k);

        String[] strs = convert.split("0");
        for(String s: strs) {
            if(s.equals("")) continue;
            long p = Long.parseLong(s);
            if(isPrime(p)){
                answer++;
            }
        }
        return answer;
    }
    
    private boolean isPrime(Long n){
        if(n <= 1) return false;
        
        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i == 0) return false;
        }
        return true;
    }
    
}