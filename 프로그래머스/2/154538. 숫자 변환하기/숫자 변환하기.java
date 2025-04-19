import java.util.Arrays;

class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        // x -> y
        //
        int[] dp = new int[y+1];
        
        Arrays.fill(dp, Integer.MAX_VALUE-1);
        dp[y] = 0;
        
        for(int i=y;i>=x;i--){
            if(i%3 == 0){
                dp[i/3] = Math.min(dp[i/3], dp[i]+1);
            }
            if(i%2 == 0){
                dp[i/2] = Math.min(dp[i/2], dp[i]+1);
            }
            if(i-n >= 0){
                dp[i-n] = Math.min(dp[i-n], dp[i]+1);
            }
        }

        return dp[x] == Integer.MAX_VALUE-1 ? -1 : dp[x];
    }
}