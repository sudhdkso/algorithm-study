import java.util.Arrays;
class Solution {
    static int[][] arr;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        init(n, fares);

        
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    arr[i][j] = Math.min(arr[i][k]+arr[k][j],arr[i][j]);
                }
            }
        }

        for(int i=0;i<n;i++){
            answer = Math.min(answer, arr[s-1][i]+arr[i][a-1]+arr[i][b-1]);
        }
        
        return answer;
    }
    
    private static void init(int n, int[][] fares){
        arr = new int[n][n];

        int len = fares.length;
        
        for(int i=0;i<n;i++){
            Arrays.fill(arr[i], 1_000_000);
        }
        
        for(int i=0;i<n;i++){
            arr[i][i] = 0;
        }
        
        for(int i=0;i<len;i++){
            int a = fares[i][0]-1;
            int b = fares[i][1]-1;
            arr[a][b] = fares[i][2];
            arr[b][a] = fares[i][2];
        }

    }
}