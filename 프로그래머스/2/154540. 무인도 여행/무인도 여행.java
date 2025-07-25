import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    static class Point {
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    static String SEA = "X";
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    public int[] solution(String[] maps) {
        int[] answer = {};
        int n = maps.length, m = maps[0].length();
        
        boolean[][] visited = new boolean[n][m];
        String[][] strMaps = new String[n][m];
        
        for(int i=0;i<n;i++){
            strMaps[i] = maps[i].split("");
        }
        
        List<Integer> list = new ArrayList<>();
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j] && !strMaps[i][j].equals(SEA)){
                    list.add(bfs(i,j,visited,strMaps));
                }
            }
        }
        if(list.size() == 0) return new int[]{-1};
        
        Collections.sort(list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private static int bfs(int x, int y, boolean[][] visited, String[][] strMaps){
        int n = strMaps.length, m = strMaps[0].length;
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x,y));
        visited[x][y] = true;
        int count = Integer.parseInt(strMaps[x][y]);
        
        while(!q.isEmpty()){
            Point now = q.poll();
            
            for(int i=0;i<4;i++){
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(!visited[nx][ny] && !strMaps[nx][ny].equals(SEA)){
                    count += Integer.parseInt(strMaps[nx][ny]);
                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny));
                }
            }
        }
        return count;
    }
}