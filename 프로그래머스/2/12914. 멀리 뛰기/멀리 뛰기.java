class Solution {
    public long solution(int n) {
        long answer = 0;
        long[] dp = new long[n+1];
        
        if(n <= 2){
            return n;
        }
        long a = 1L, b = 2L;
        for(int i=3;i<=n;i++){
            answer = (a + b)%1234567;
            a = b;
            b = answer;
        }
        
        
        
        return answer;
    }
}