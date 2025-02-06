import java.util.Arrays;

class Solution {
    private static int MOD = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] dp = new int[n+1][m+1];
        
        //물웅덩이 세팅
        for(int i=0;i<puddles.length;i++){
            dp[puddles[i][1]][puddles[i][0]] = -1;
        }
        
        dp[1][1] = 1;
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(dp[i][j] == -1 || (i == 1 && j == 1)) continue;
                
                if((i-1 <= 0 && j-1>0) || dp[i-1][j] == -1){
                    dp[i][j]= dp[i][j-1];
                }    
                else if((i-1 > 0 && j-1<=0) || dp[i][j-1] == -1){
                    dp[i][j] = dp[i-1][j];
                } 
                else {
                    dp[i][j] = (dp[i-1][j] + dp[i][j-1])%MOD;
                }
            }
        }
        return dp[n][m];
    }
}