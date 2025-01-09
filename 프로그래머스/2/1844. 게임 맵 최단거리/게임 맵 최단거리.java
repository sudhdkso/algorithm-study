import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Solution {
    
    private static class Point {
        int x,y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    private static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
    
    public int solution(int[][] maps) {
        int answer = 0;
        return bfs(maps, maps.length, maps[0].length);
    }
    
    private static int bfs(int[][] map, int n, int m){
        int[][] dist = new int[n][m];
        
        Queue<Point> q = new LinkedList<>();
        
        for(int i=0;i<n;i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE-1);
        }
        
        dist[0][0] = 1;
        q.offer(new Point(0,0));
        
        while(!q.isEmpty()){
            Point now = q.poll();
            if(now.x == n-1 && now.y == m-1){
                return dist[now.x][now.y];
            }
            
            for(int i=0;i<4;i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < n && ny < m){
                    if(dist[nx][ny] > dist[now.x][now.y] + 1 && map[nx][ny] == 1){
                        dist[nx][ny] = dist[now.x][now.y] + 1;
                        q.offer(new Point(nx, ny));
                    }
                }
            }
        }
        return -1;
    }
}