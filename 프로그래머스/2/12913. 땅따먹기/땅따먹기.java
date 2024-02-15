class Solution {
    int solution(int[][] land) {
        int answer = 0;

        int n = land.length;
        int m = 4;
        int[][] dp = new int[n][m];
        for(int i=0;i<m;i++){
            dp[0][i] = land[0][i];
        }
        
        for(int i=1;i<n;i++){
            for(int j=0;j<m;j++){
                dp[i][j] = land[i][j] + getPreviousRowMax(dp[i-1], j);
            }
        }
        
        return getPreviousRowMax(dp[n-1],-1);
    }
    
    private int getPreviousRowMax(int[] dp, int j){
        int max = 0;
        for(int i=0;i<4;i++){
            if(i != j){
                max = Math.max(dp[i], max);
            }
        }
        return max;
    }
}