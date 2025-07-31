import java.util.Arrays;
class Solution {
    private final static int MAX = 1_000_000;
    public int solution(int x, int y, int n) {
        int answer = 0;
        int[] dp = new int[MAX+1];
        Arrays.fill(dp, Integer.MAX_VALUE-1);
        dp[x] = 0;
        for(int i = x; i<= y;i++){
            if(i*2 <= MAX){
                dp[i*2] = Math.min(dp[i]+1, dp[i*2]);
            }
            if(i*3 <= MAX){
                dp[i*3] = Math.min(dp[i]+1, dp[i*3]);
            }
            if(i+n <= MAX){
                dp[i+n] = Math.min(dp[i]+1, dp[i+n]);
            }
        }
        
        return dp[y] == Integer.MAX_VALUE-1 ? -1: dp[y];
    }
}