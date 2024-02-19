import java.util.*;

class Solution {
    public class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    private Map<Integer, Integer> map = new HashMap<>();
    
    public int solution(int[][] land) {
        int answer = 0;
        int n = land.length, m = land[0].length;
        boolean[][] visited = new boolean[n][m];
        for(int i=0;i<n;i++){

            for(int j=0;j<m;j++){
                if(!visited[i][j] && land[i][j] == 1){
                    bfs(i,j,land,visited);
                }
            }
        }

        for(int col : map.keySet()){
            answer = Math.max(map.get(col), answer);
        }
        return answer;
    }
    private int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    
    private void bfs(int x, int y, int[][] land, boolean[][] visited){
        Queue<Point> q = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        
        int n = land.length, m = land[0].length;
        int count = 1;
        
        visited[x][y] = true;
        
        q.offer(new Point(x,y));
        set.add(y);
        
        while(!q.isEmpty()){
            Point p = q.poll();
            
            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                
                if(nx >=0 && ny >=0 && nx < n && ny < m){
                    if(!visited[nx][ny] && land[nx][ny] == 1){
                        count++;
                        visited[nx][ny] = true;
                        q.offer(new Point(nx,ny));
                        set.add(ny);
                    }
                }
            }
        }

        for(int col : set){
            map.put(col, map.getOrDefault(col,0)+count);
        }    
    }
    
}