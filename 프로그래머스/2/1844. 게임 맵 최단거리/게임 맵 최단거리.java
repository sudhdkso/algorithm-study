import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Solution {
    private class Point{
        int x,y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    private static int SX = 0, SY = 0;
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        int answer = bfs(n, m, maps);
        return answer == Integer.MAX_VALUE-1 ? -1 : answer;
    }
    private int[] dx = {0, 1, 0, -1}, dy = {1, 0 , -1, 0};
    private int bfs(int n, int m, int[][] maps){
        Queue<Point> q = new LinkedList<>();
        
        q.offer(new Point(SX,SY));
        
        int[][] dist = new int[n][m];
        
        for(int i=0;i<n;i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE-1);
        }
        
        dist[SX][SY] = 1;
        while(!q.isEmpty()){
            Point now = q.poll();
            
            for(int i=0;i<4;i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx >= 0 && ny >= 0 && nx < n && ny < m){
                    if(maps[nx][ny] == 0) continue;
                    if(dist[nx][ny] > dist[now.x][now.y] + 1) {
                        dist[nx][ny] = dist[now.x][now.y]+1;
                        q.offer(new Point(nx,ny));
                    }
                }
            }
            
        }
        
        return dist[n-1][m-1];
    }
}