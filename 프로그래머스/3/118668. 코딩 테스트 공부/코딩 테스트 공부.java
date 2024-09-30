import java.util.Arrays;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int answer = Integer.MAX_VALUE;
        
        int maxAlp = 0, maxCop = 0;
        
        for(int i=0;i<problems.length;i++){
            maxAlp = Math.max(maxAlp, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
        }
        
        int[][] dp = new int[501][501];
        
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                dp[i][j] = 123456789;
            }
        }
        
        dp[alp][cop] = 0;
        
        for(int i=alp;i<dp.length;i++){
            for(int j=cop;j<dp[0].length;j++){
                if(alp == i && cop == j) continue;
                if(i-1 >= 0){
                    dp[i][j] = Math.min(dp[i][j],dp[i-1][j]+1);
                }
                if(j-1 >= 0){
                    dp[i][j] = Math.min(dp[i][j],dp[i][j-1]+1);
                }
                
                solvedProblem(dp, problems, new int[]{i,j});
            }
        }
        
        
        //dp[i][j] : 알고력 i와 코딩력 j를 하기 위한 최소 시간
        
        for(int i=maxAlp;i<dp.length;i++){
            for(int j=maxCop;j<dp.length;j++){
                answer = Math.min(answer, dp[i][j]);
            }
        }
        
        return answer;
    }
    
    private static void solvedProblem(int[][] dp, int[][] problems, int[] arr){
        for(int i=0;i<problems.length;i++){
            int alp_req = problems[i][0];
            int cop_req = problems[i][1];
            int alp_rwd = problems[i][2];
            int cop_rwd = problems[i][3];
            int cost = problems[i][4];
            
            int i_req = arr[0] - alp_rwd;
            int j_req = arr[1] - cop_rwd;
            
            if(i_req < alp_req || j_req < cop_req) continue;
            
            
            
            dp[arr[0]][arr[1]] = Math.min(dp[arr[0]][arr[1]], dp[i_req][j_req] + cost);
        }
    }
    
    private static int getAnswer(int[][] dp, int maxAlp, int maxCop){
        int answer = Integer.MAX_VALUE;
        
        for(int i=maxAlp;i<dp.length;i++){
            for(int j=maxCop;j<dp.length;j++){
                answer = Math.min(answer, dp[i][j]);
            }
        }
        return answer;
    }
}