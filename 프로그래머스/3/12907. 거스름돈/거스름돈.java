import java.util.Arrays;

class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        //money = {1,2,5}
        int[] dp = new int[n+1];
        
        Arrays.sort(dp);
        dp[0] = 1;
        for(int i=0;i<money.length;i++){
            for(int j=money[i]; j<=n; j++){
                dp[j] += dp[j-money[i]];
                dp[j] %= 1_000_000_007;
            }
        }
        
        return dp[n];
    }
}