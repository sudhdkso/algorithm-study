import java.util.Queue;
import java.util.LinkedList;

class Solution {
    
    private class Point{
        int x,y, dist;
        public Point(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
        
        public Point(int x, int y){
            this(x,y,0);
        }
        
    }
    
    private char START = 'S', END = 'E', LEVER = 'L', WALL = 'X', PASS = 'O';
    
    public int solution(String[] maps) {

        int sToL = bfs(START, maps, LEVER);
        int lToE = bfs(LEVER, maps, END);
        
        int answer = sToL == -1 || lToE == -1? -1 : sToL + lToE;
        return answer;
    }
    private int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
    
    private Point findPosition(char c, String[] maps){
        
        int n = maps.length;
        int m = maps[0].length();
        
        for(int i=0;i<n;i++){
            if(maps[i].indexOf(String.valueOf(c)) < 0) continue;
            
            for(int j=0;j<m;j++){
                if(maps[i].charAt(j) == c){
                    return new Point(i,j);
                }    
            }
        }
        return null;
    }
    
    private int bfs(char start, String[] maps, char find){
        Queue<Point> q = new LinkedList<>();
        
        int n = maps.length, m = maps[0].length();
        
        boolean[][] visited = new boolean[n][m];
        
        Point ps = findPosition(start, maps);
        q.offer(ps);
        
        
        visited[ps.x][ps.y] = true;
        
        while(!q.isEmpty()){
            Point p = q.poll();
            
            if(maps[p.x].charAt(p.y) == find){
                return p.dist;
            }
            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                
                if(nx >= 0 && ny >= 0 && nx < n && ny < m){
                    if(maps[nx].charAt(ny) != WALL && !visited[nx][ny]){
                        q.offer(new Point(nx,ny,p.dist+1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }
}