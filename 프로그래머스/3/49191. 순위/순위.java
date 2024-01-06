import java.util.*;
class Solution {
    
    private static int[][] count;
    private static ArrayList<Integer>[] list;
    private static boolean[] visited;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        visited = new boolean[n+1];
        list = new ArrayList[n+1];
        count = new int[n+1][2];
        for(int i=1;i<=n;i++){
            list[i] = new ArrayList<>();
        }
        
        for(int i=0;i< results.length;i++){
            list[results[i][0]].add(results[i][1]);
        }
        
        for(int i=1;i<=n;i++){
            Collections.sort(list[i]);
        }
        
        for(int i=1;i<=n;i++){
            visited = new boolean[n+1];
            dfs(i,i);
        }
        for(int i=1;i<=n;i++){
            if(count[i][0] + count[i][1] == n-1){
                answer++;
            }
            //System.out.println(Arrays.toString(count[i]));
        }

        return answer;
    }
    
    private static void dfs(int node, int start){
        visited[node] = true;
        if(node != start){
            count[node][0]++;
            count[start][1]++;
        }
        for(int n : list[node]){
            if(!visited[n]){
                dfs(n, start);
            }
        }
    }
}