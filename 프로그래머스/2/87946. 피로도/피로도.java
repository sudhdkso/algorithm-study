class Solution {
    private static int max = 0;
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        return go(k, dungeons, new boolean[dungeons.length], 0);
    }
    
    private static int go(int k, int[][] dungeons, boolean[] visited, int count){
        for(int i=0;i<dungeons.length;i++){
            if(!visited[i] && k >= dungeons[i][0]){
                visited[i] = true;
                go(k-dungeons[i][1], dungeons, visited, count+1);
                visited[i] = false;
                
            }
        }
        max = Math.max(max, count);
        return max;
        
    }
}