import java.util.Arrays;
class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int h = triangle.length;
        //0,0
        //1,0 1,1
        //2,0 2,1, 2,2
        //3,0 3,1, 3,2, 3,3
        int[][] dp = new int[h][h];
        //(i,j) 현재 위치를 선택할 때 최적
        dp[0][0] = triangle[0][0];
        
        for(int i=1;i<h;i++){
            for(int j=0;j<=i;j++){
                if(j==0){
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                }
                else if(i==j){
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j])+triangle[i][j];        
                }
            }
        }

        for(int i=0;i<dp[h-1].length;i++){
            answer = Math.max(dp[h-1][i], answer);
        }
        return answer;
    }
}