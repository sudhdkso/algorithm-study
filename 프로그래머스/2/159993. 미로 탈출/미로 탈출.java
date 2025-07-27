import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Solution {
    static class Point{
        int x,y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static char START = 'S', LEVER = 'L', END = 'E';
    
    public int solution(String[] maps) {
        int answer = 0;
        
        int n = maps.length, m = maps[0].length();
        
        char[][] map = new char[n][m];
        
        for(int i=0;i<n;i++){
            map[i] = maps[i].toCharArray();
        }
        
        Point st = findPosition(map, START);
        Point le = findPosition(map, LEVER);
        Point ed = findPosition(map, END);

        int sToL = bfs(map, n,m,st,le);
        int lToE = bfs(map, n,m,le,ed);
        if(sToL == -1 || lToE == -1) return -1;
        
        return sToL+lToE;
    }
    
    private static Point findPosition(char[][] map, char ch){
        
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if(map[i][j] == ch){
                    return new Point(i,j);
                }
            }
        }
        return new Point(-1,-1);
    }
    private static int bfs(char[][] map, int n, int m, Point src, Point dst){
        boolean[][] visited = new boolean[n][m];
        int[][] dist = new int[n][m];
        Queue<Point> q = new LinkedList<>();
        
        for(int i=0;i<n;i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE-1);
        }
        visited[src.x][src.y] = true;
        dist[src.x][src.y] = 0;
        
        q.offer(src);
        
        while(!q.isEmpty()){
            Point now = q.poll();
            if(now.x == dst.x && now.y == dst.y){
                return dist[dst.x][dst.y];
            }
            
            for(int i=0;i<4;i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(!visited[nx][ny] && map[nx][ny] != 'X' && dist[nx][ny] > dist[now.x][now.y] + 1){
                    dist[nx][ny] =  dist[now.x][now.y]+1;
                    visited[nx][ny] = true;
                    q.offer(new Point(nx,ny));
                }
            }
        }
        return -1;
        
    }
}