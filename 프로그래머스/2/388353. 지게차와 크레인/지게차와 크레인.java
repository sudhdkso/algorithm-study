import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Stack;

class Solution {
    private static class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        int n = storage.length, m = storage[0].length();
        
        char[][] containers = new char[n+2][m+2];
        
        for(int i=0;i<=n+1;i++){
            Arrays.fill(containers[i], 'x');
        }
        
        for(int i=0;i<n;i++){
            char[] ch = storage[i].toCharArray();
            for(int j=0;j<ch.length;j++){
                containers[i+1][j+1] = ch[j];
            }
        }
        
        
        for(String request: requests){
            char target = request.charAt(0);
            if(request.length() == 1){
                moveForkLift(containers, target);
            }
            else {
                moveCrane(containers, target);
            }
            
        }
     
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(containers[i][j] == 'x'){
                    answer++;
                }
            }
        }
        return n*m - answer;
    }
    
    
    private static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    
    public static void moveForkLift(char[][] containers, char target){
        int n = containers.length, m = containers[0].length;
        
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        Stack<Point> s = new Stack<>();
        q.offer(new Point(1,1));
        
        while(!q.isEmpty()){
            Point p = q.poll();
            
            for(int i=0;i<4;i++){
                int nx = p.x+dx[i];
                int ny = p.y+dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) continue;
                if(containers[nx][ny] != 'x' && containers[nx][ny] != target) continue;
                if(containers[nx][ny] == target){
                    s.push(new Point(nx,ny));
                    continue;
                }
                q.offer(new Point(nx,ny));
                visited[nx][ny] = true;
                 
            }
        }
        
        while(!s.isEmpty()){
            Point p = s.pop();
            containers[p.x][p.y] = 'x';
        }
        
    }
    
    public static void moveCrane(char[][] containers, char target){
        int n = containers.length, m = containers[0].length;
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(containers[i][j] == target){
                    containers[i][j] = 'x';
                }
            }
        }
    }
    

}