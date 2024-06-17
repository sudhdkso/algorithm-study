class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int n = land.length, m = land[0].length;
        
        int[][] dp = new int[n][m];
        for(int i=0;i<m;i++){
            dp[0][i] = land[0][i];
        }
        
        for(int i=1;i<n;i++){   
            dp[i][0] = Math.max(dp[i-1][1], Math.max(dp[i-1][2],dp[i-1][3])) + land[i][0];
            dp[i][1] = Math.max(dp[i-1][0], Math.max(dp[i-1][2],dp[i-1][3])) + land[i][1];
            dp[i][2] = Math.max(dp[i-1][0], Math.max(dp[i-1][1],dp[i-1][3])) + land[i][2];
            dp[i][3] = Math.max(dp[i-1][0], Math.max(dp[i-1][1],dp[i-1][2])) + land[i][3];
        }

        return Math.max(dp[n-1][0],Math.max(dp[n-1][1], Math.max(dp[n-1][2], dp[n-1][3])));
    }
}