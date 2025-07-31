import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        return bfs(x,y,n);
    }
    
    private static int bfs(int x, int y, int n){
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[1_000_001];
        visited[x] = true;
        q.offer(new int[]{x,0});
        
        while(!q.isEmpty()){
            int[] now = q.poll();
            int cur = now[0];
            int cnt = now[1];
            if(cur == y){
                return cnt;
            }
            
            for(int next: new int[]{cur*2,cur*3,cur+n}){
                if(next > 1_000_000 || visited[next]) continue;
                q.offer(new int[]{next, cnt+1});
                visited[next] = true;
            }
        }
        return -1;
    }
}